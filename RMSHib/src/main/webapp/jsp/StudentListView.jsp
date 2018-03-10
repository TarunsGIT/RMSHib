<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.StudentListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.mss.rmshib.dto.StudentDTO"%>
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
<h2 class="container  text-center text-primary" style="margin-top: -4;">
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Student
		List</b>
</h2>

<br>
<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.STUDENT_LIST_CTL%>">
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.StudentDTO"
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
		&emsp;&emsp;

		<button type="submit" class="btn btn-primary" name="operation"
			value="<%=StudentListCtl.OP_SEARCH%>">
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
			<th class="col-md-1"><input type="checkbox"
				onclick="checkAll(this)" />&emsp;<span
				class="glyphicon glyphicon-check"></span></th>
			<th><span class="glyphicon glyphicon-list-alt"></span> S.No.</th>

			<th><span class="glyphicon glyphicon-education"></span> College</th>
			<th><span class="glyphicon glyphicon-user"></span> First Name</th>
			<th><span class="glyphicon glyphicon-user"></span> Last Name</th>
			<th><span class="glyphicon glyphicon-calendar"></span> DOB</th>
			<th><span class="glyphicon glyphicon-phone"></span> Mobile No</th>
			<th><span class="glyphicon glyphicon-envelope"></span> Email ID</th>
			<th><span class="glyphicon glyphicon-edit"></span> Edit</th>

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
				Iterator<StudentDTO> it = list.iterator();
				while (it.hasNext()) {
					StudentDTO stdto = it.next();
			%>

			<%
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			%>


			<td><input type="checkbox" name="ids" value="<%=stdto.getId()%>"></td>
			<td><%=index++%></td>

			<td><%=stdto.getCollegeName()%></td>
			<td><%=stdto.getFirstName()%></td>
			<td><%=stdto.getLastName()%></td>
			<td><%=sdf.format(stdto.getDob())%></td>
			<td><%=stdto.getMobileNo()%></td>
			<td><%=stdto.getEmail()%></td>
			<td><a
				href="StudentCtl.do?id=<%=stdto.getId()%>&firstName=<%=request.getParameter("firstName")%>&"><span
					class="glyphicon glyphicon-pencil"></span></a></td>

		</tr>
		<%
			}
		%>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
	<button type="submit"
		class="btn btn-primary pull-left <%=pageNo == 1 ? "hidden" : ""%>"
		name="operation" value="<%=StudentListCtl.OP_PREVIOUS%>">
		<span class="glyphicon glyphicon-backward"></span> Previous
	</button>


	<button type="submit" class="btn btn-primary text-center"
		name="operation" value="<%=StudentListCtl.OP_NEW%>">
		<span class="glyphicon glyphicon-plus"></span> New
	</button>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

	<button type="submit" class="btn btn-primary text-center "
		name="operation" value="<%=StudentListCtl.OP_DELETE%>">
		<span class="glyphicon glyphicon-trash"></span> Delete
	</button>

	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=StudentListCtl.OP_NEXT%>">
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
				href="StudentListCtl.do?pageNo=<%=j%>&firstName=<%=ServletUtility.getParameter("firstName", request)%>&lastName=<%=ServletUtility.getParameter("lastName", request)%>"><%=j%></a></li>
			<%
				}
			%>
		</ul>
	</div>
</form>

<%@ include file="Footer.jsp"%>