
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.mss.rmshib.util.DataValidator"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.UserListCtl"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.mss.rmshib.dto.UserDTO"%>

<%@page import="java.util.List"%>
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
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;User List</b>
</h2>

<br>


<%
	List l = (List) request.getAttribute("roleList");
%>

<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.USER_LIST_CTL%>">
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.UserDTO"
		scope="request"></jsp:useBean>

	<div style="margin-top: -10;">

		<div class="form-group">
			<label for="inputEmail3">First Name:</label> <input type="text"
				class="form-control" id="inputEmail3" placeholder="firstName"
				name="firstName"
				value="<%=ServletUtility.getParameter("firstName", request)%>" />
		</div>
		&emsp;

		<div class="form-group">
			<label class="" for="inputEmail3">Last Name:</label> <input
				type="text" width="" class="form-control" id="inputEmail3"
				placeholder="Last Name" name="lastName"
				value="<%=ServletUtility.getParameter("lastName", request)%>" />
		</div>
		&emsp;


		<div class="form-group">
			<label for="roleId" class="col-sm-3 control-label"
				style="margin-top: 7; margin-right: -34;"><b>Role:</b> </label>
			<div class="col-sm-4"><%=HTMLUtility.getList("roleId",
					String.valueOf(dto.getRoleId()), l)%></div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("roleId", request)%></label>
		</div>
		&emsp;

		<button type="submit" class="btn btn-primary" name="operation"
			value="<%=UserListCtl.OP_SEARCH%>">
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

	<table class="table table-inverse table-striped">
		<tr>
			<th class="col-md-1"><input type="checkbox"
				onclick="checkAll(this)" />&emsp;<span
				class="glyphicon glyphicon-check"></span></th>
			<th><span class="glyphicon glyphicon-list-alt"></span> S.No</th>
			<th><span class="glyphicon glyphicon-user"></span> Role Name</th>
			<th><span class="glyphicon glyphicon-user"></span> First Name</th>
			<th><span class="glyphicon glyphicon-user"></span> Last Name</th>
			<th><span class="glyphicon glyphicon-envelope"></span> Login Id</th>
			<th><span class="glyphicon glyphicon-user"></span> Gender</th>
			<th><span class="glyphicon glyphicon-calendar"></span> DOB</th>
			<th class="col-md-1"><span class="glyphicon glyphicon-edit"></span>
				Edit</th>


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
				Iterator<UserDTO> it = list.iterator();
				while (it.hasNext()) {
					dto = it.next();
			%>

			<%
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			%>

			<td><input type="checkbox" name="ids" value="<%=dto.getId()%>"></td>
			<td><%=index++%></td>
			<td><%=dto.getRoleName()%></td>
			<td><%=dto.getFirstName()%></td>
			<td><%=dto.getLastName()%></td>
			<td><%=dto.getLogin()%></td>
			<td><%=dto.getGender()%></td>
			<td><%=sdf.format(dto.getDob())%></td>
			<td><a href="UserCtl.do?id=<%=dto.getId()%>"><span
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
		name="operation" value="<%=UserListCtl.OP_PREVIOUS%>">
		<span class="glyphicon glyphicon-backward"></span> Previous
	</button>

	<button type="submit" class="btn btn-primary text-center"
		name="operation" value="<%=UserListCtl.OP_NEW%>">
		<span class="glyphicon glyphicon-plus"></span> New
	</button>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

	<button type="submit" class="btn btn-primary text-center "
		name="operation" value="<%=UserListCtl.OP_DELETE%>">
		<span class="glyphicon glyphicon-trash"></span> Delete
	</button>

	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=UserListCtl.OP_NEXT%>">
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
				href="UserListCtl.do?pageNo=<%=j%>&firstName=<%=ServletUtility.getParameter("firstName", request)%>&lastName=<%=ServletUtility.getParameter("lastName", request)%>&roleId=<%=ServletUtility.getParameter("roleId", request)%>"><%=j%></a></li>


			<%
				}
			%>
		</ul>
	</div>
</form>

<%@ include file="Footer.jsp"%>


