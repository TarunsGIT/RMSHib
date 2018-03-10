<%@page import="in.co.mss.rmshib.dto.RoleDTO"%>
<%@page import="in.co.mss.rmshib.dto.UserDTO"%>
<%@page import="in.co.mss.rmshib.controller.CourseListCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.StudentListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@page import="in.co.mss.rmshib.dto.CourseDTO"%>
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
<h2 class="container  text-center text-primary" style="margin-top: -4;">
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Course
		List</b>
</h2>

<br>

<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.COURSE_LIST_CTL%>">
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.CourseDTO"
		scope="request"></jsp:useBean>
	<div style="margin-top: -10;">

		<div class="form-group">
			<label for="inputEmail3">Course Name :</label> <input type="text"
				value="<%=ServletUtility.getParameter("name", request)%>"
				class="form-control " id="inputEmail3" placeholder="Course Name"
				name="name">
		</div>
		&emsp;&emsp;
		<div class="form-group">
			<label class="" for="inputEmail3">Duration : </label> <input
				type="text"
				value="<%=ServletUtility.getParameter("duration", request)%>"
				class="form-control" id="inputEmail3" placeholder="Duration"
				name="duration">
		</div>
		&emsp;&emsp;

		<button type="submit" class="btn btn-primary" name="operation"
			value="<%=CourseListCtl.OP_SEARCH%>">
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
			<th class="col-md-1"><input type="checkbox"
				onclick="checkAll(this)" />&emsp;<span
				class="glyphicon glyphicon-check"></span></th>
			<%
				}
			%>
			<th><span class="glyphicon glyphicon-list-alt"></span> S.No.</th>

			<th><span class="glyphicon glyphicon-education"></span> Course</th>
			<th><span class="glyphicon glyphicon-book"></span> Description</th>
			<th><span class="glyphicon glyphicon-align-center"></span>
				Duration</th>
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
				Iterator<CourseDTO> it = list.iterator();
				while (it.hasNext()) {
					CourseDTO stdto = it.next();
			%>


			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<td><input type="checkbox" name="ids" value="<%=stdto.getId()%>"></td>
			<%
				}
			%>
			<td><%=index++%></td>

			<td><%=stdto.getName()%></td>
			<td><%=stdto.getDescription()%></td>
			<td><%=stdto.getDuration()%></td>
			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<td><a href="CourseCtl.do?id=<%=stdto.getId()%>"><span
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
		name="operation" value="<%=CourseListCtl.OP_PREVIOUS%>">
		<span class="glyphicon glyphicon-backward"></span> Previous
	</button>

	<%
		if (userdto.getRoleId() == RoleDTO.ADMIN) {
	%>
	<button type="submit" class="btn btn-primary text-center"
		name="operation" value="<%=CourseListCtl.OP_NEW%>">
		<span class="glyphicon glyphicon-plus"></span> New
	</button>
	<%
		}
	%>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	<%
		if (userdto.getRoleId() == RoleDTO.ADMIN) {
	%>
	<button type="submit" class="btn btn-primary text-center "
		name="operation" value="<%=CourseListCtl.OP_DELETE%>">
		<span class="glyphicon glyphicon-trash"></span> Delete
	</button>
	<%
		}
	%>

	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=CourseListCtl.OP_NEXT%>">
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
				href="CourseListCtl.do?pageNo=<%=j%>&name=<%=ServletUtility.getParameter("name", request)%>&duration=<%=ServletUtility.getParameter("duration", request)%>"><%=j%></a></li>
			<%
				}
			%>
		</ul>
	</div>
</form>
<%@ include file="Footer.jsp"%>