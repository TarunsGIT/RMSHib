
<%@page import="in.co.mss.rmshib.dto.RoleDTO"%>
<%@page import="in.co.mss.rmshib.dto.UserDTO"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.FacultyListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@page import="in.co.mss.rmshib.dto.FacultyDTO"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<head>
<script type="text/javascript">
	function checkAll(master) {
		var checked = master.checked;
		var col = document.getElementsByTagName("INPUT");
		for ( var i = 0; i < col.length; i++) {
			col[i].checked = checked;
		}
	}
</script>
</head>
<jsp:include page="Home.jsp"></jsp:include>
<%
	UserDTO userdto = (UserDTO) session.getAttribute("user");
%>
<%
	String role = (String) session.getAttribute("role");
%>

<h2 class="container  text-center text-primary" style="margin-top: -4;">
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Faculty
		List</b>
</h2>

<br>

<%
	List l = (List) request.getAttribute("courseList");
%>

<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.FACULTY_LIST_CTL%>">
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.FacultyDTO"
		scope="request"></jsp:useBean>

	<div style="margin-top: -10;">
		<div class="form-group">
			<label for="inputEmail3">First Name :</label> <input type="text"
				value="<%=ServletUtility.getParameter("firstName", request)%>"
				class="form-control " id="inputEmail3" placeholder="First Name"
				name="firstName">
		</div>
		&emsp;&emsp;
		<div class="form-group">
			<label class="" for="inputEmail3">Last Name : </label> <input
				type="text"
				value="<%=ServletUtility.getParameter("lastName", request)%>"
				class="form-control" id="inputEmail3" placeholder="Last Name"
				name="lastName">
		</div>


		<div class="form-group">
			<label for="courseId" class="col-sm-3 control-label"
				style="margin-top: 7; margin-right: -24;"><b>Course: </b> </label>
			<div class="col-sm-4"><%=HTMLUtility.getList("courseId",
					String.valueOf(dto.getCourseId()), l)%></div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("courseId", request)%></label>
		</div>
		&emsp;

		<button type="submit" class="btn btn-primary" name="operation"
			value="<%=FacultyListCtl.OP_SEARCH%>">
			<span class="glyphicon glyphicon-search"></span> Search
		</button>

		<br> <br>
	</div>
	<div class="container  text-center">
		<div class="alert alert-success" role="alert"
			<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
			<%=ServletUtility.getSuccessMessage(request)%></div>
		<div class="alert alert-danger" role="alert"
			<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>
	</div>



	<table class="table table-inverse table-striped ">
		<tr>
			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<th><input type="checkbox" onclick="checkAll(this)" />&emsp;<span
				class="glyphicon glyphicon-check"></span></th>
			<%
				}
			%>
			<th><span class="glyphicon glyphicon-list-alt"></span> S.No.</th>
			<th><span class="glyphicon glyphicon-education"></span> College</th>
			<th><span class="glyphicon glyphicon-book"></span> Course</th>
			<th><span class="glyphicon glyphicon-book"></span> Primary</th>
			<th><span class="glyphicon glyphicon-book"></span> Secondary</th>
			<th><span class="glyphicon glyphicon-user"></span> First Name</th>
			<th><span class="glyphicon glyphicon-user"></span> Last Name</th>
			<!-- 	<th><span class="glyphicon glyphicon-user"></span> Qualification</th> -->
			<!-- <th><span class="glyphicon glyphicon-calendar"></span> DOB</th> -->
			<!-- <th><span class="glyphicon glyphicon-phone"></span> Mobile No</th> -->
			<th><span class="glyphicon glyphicon-envelope"></span> Email ID</th>
			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<th><span class="glyphicon glyphicon-edit"></span> Edit</th>
			<%
				}
			%>

		</tr>
		<tr>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = 0;
				if (pageNo == 0) {
					pageNo = 1;
					index = ((pageNo - 1) * pageSize) + 1;
				} else {
					index = ((pageNo - 1) * pageSize) + 1;
				}
				List list = (List) ServletUtility.getList(request);
				Iterator<FacultyDTO> it = list.iterator();
				while (it.hasNext()) {
					FacultyDTO stdto = it.next();
			%>


			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<td><input type="checkbox" name="ids" value="<%=stdto.getId()%>"></td>
			<%
				}
			%>
			<td><%=index++%></td>

			<td><%=stdto.getCollegeName()%></td>
			<td><%=stdto.getCourseName()%></td>
			<td><%=stdto.getPrimarySubject()%></td>
			<td><%=stdto.getSecondarySubject()%></td>
			<td><%=stdto.getFirstName()%></td>
			<td><%=stdto.getLastName()%></td>
			<%-- <td><%=stdto.getQualification()%></td> --%>
			<%-- <td><%=DataUtility.getDateString(stdto.getDob())%></td> --%>
			<%-- <td><%=stdto.getMobileNo()%></td> --%>
			<td><%=stdto.getEmail()%></td>
			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<td><a href="FacultyCtl.do?id=<%=stdto.getId()%>"><span
					class="glyphicon glyphicon-pencil"></span></a></td>
			<%
				}
			%>

		</tr>
		<%
			}
		%>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
	<button type="submit"
		class="btn btn-primary pull-left <%=pageNo == 1 ? "hidden" : ""%>"
		name="operation" value="<%=FacultyListCtl.OP_PREVIOUS%>">
		<span class="glyphicon glyphicon-backward"></span> Previous
	</button>

	<button type="submit"
		class="btn btn-primary text-center <%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"
		name="operation" value="<%=FacultyListCtl.OP_NEW%>">
		<span class="glyphicon glyphicon-plus"></span> New
	</button>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

	<button type="submit"
		class="btn btn-primary text-center <%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"
		name="operation" value="<%=FacultyListCtl.OP_DELETE%>">
		<span class="glyphicon glyphicon-trash"></span> Delete
	</button>

	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=FacultyListCtl.OP_NEXT%>">
		<span class="glyphicon glyphicon-forward"></span> Next
	</button>

	<div class="container" style="margin-top: 10;">
		<ul class="pagination pagination-lg">
			<%
				double i = (Integer) request.getAttribute("listSize");
				i = Math.ceil(i * 1.0 / 5.0);
				int jo = (int) i;
				for (int j = 1; j <= jo; j++) {
			%>
			<li <%if (j == pageNo) {%> class="active" <%}%>><a
				href="FacultyListCtl.do?pageNo=<%=j%>&firstName=<%=ServletUtility.getParameter("firstName", request)%>&lastName=<%=ServletUtility.getParameter("lastName", request)%>&courseId=<%=ServletUtility.getParameter("courseId", request)%>"><%=j%></a></li>
			<%
				}
			%>
		</ul>
	</div>
</form>
<%@ include file="Footer.jsp"%>