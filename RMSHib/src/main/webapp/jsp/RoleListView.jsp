
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.RoleListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@page import="in.co.mss.rmshib.dto.RoleDTO"%>
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
<h2 class="container  text-center text-primary" style="margin-top: -10;">
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Role List</b>
</h2>


<br>


<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.ROLE_LIST_CTL%>">

<div style="margin-top: -10;">
	<div class="form-group ">
		<label for="inputEmail3">Name:</label> <input type="text"
			class="form-control " id="inputEmail3"
			value="<%=ServletUtility.getParameter("name", request)%>"
			placeholder="Name" name="name">
	</div>
	&emsp;&emsp;
	<div class="form-group">
		<label class="" for="inputEmail3">Description:</label> <input
			type="text" class="form-control" id="inputEmail3"
			value="<%=ServletUtility.getParameter("description", request)%>"
			placeholder="Description" name="description">
	</div>
	&emsp;

	<button type="submit" class="btn btn-primary" name="operation"
		value="<%=RoleListCtl.OP_SEARCH%>">
		<span class="glyphicon glyphicon-search"></span> Search
	</button>
</div>
	<br>

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
			<th><span class="glyphicon glyphicon-user"></span> Name</th>
			<th><span class="glyphicon glyphicon-book"></span> Description</th>
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
				Iterator<RoleDTO> it = list.iterator();
				while (it.hasNext()) {
					RoleDTO dto = it.next();
			%>



			<td><input type="checkbox" name="ids" value="<%=dto.getId()%>"></td>
			<td><%=index++%></td>

			<td><%=dto.getName()%></td>
			<td><%=dto.getDescription()%></td>
			<td><a href="RoleCtl.do?id=<%=dto.getId()%>"><span
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
		name="operation" value="<%=RoleListCtl.OP_PREVIOUS%>">
		<span class="glyphicon glyphicon-backward"></span> Previous
	</button>

	<button type="submit" class="btn btn-primary text-center"
		name="operation" value="<%=RoleListCtl.OP_NEW%>">
		<span class="glyphicon glyphicon-plus"></span> New
	</button>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

	<button type="submit" class="btn btn-primary text-center "
		name="operation" value="<%=RoleListCtl.OP_DELETE%>">
		<span class="glyphicon glyphicon-trash"></span> Delete
	</button>
	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() <= pageSize ? "hidden" : ""%>"
		name="operation" value="<%=RoleListCtl.OP_NEXT%>">
		<span class="glyphicon glyphicon-forward"></span> Next
	</button>

	<br>
	<%-- 
	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=RoleListCtl.OP_NEXT%>">Next</button> --%>
	<div class="container">
		<ul class="pagination pagination-lg">
			<%
				double i = (Integer) request.getAttribute("listSize");
				i = Math.ceil(i * 1.0 / 5.0);
				int jo = (int) i;
				for (int j = 1; j <= jo; j++) {
			%>
			<li <%if (j == pageNo) {%> class="active" <%}%>><a
				href="RoleListCtl.do?pageNo=<%=j%>&name=<%=ServletUtility.getParameter("name", request)%>&description=<%=ServletUtility.getParameter("description", request)%>"><%=j%></a></li>
			<%
				}
			%>
		</ul>
	</div>
</form>

<br>
<br>
<br>
<br>
<%@ include file="Footer.jsp"%>
