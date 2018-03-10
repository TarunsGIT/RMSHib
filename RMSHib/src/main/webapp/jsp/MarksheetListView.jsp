<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.MarksheetListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@page import="in.co.mss.rmshib.dto.MarksheetDTO"%>
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
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Marksheet
		List</b>
</h2>
<%
	String role = (String) session.getAttribute("role");
%>

<br>

<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.MARKSHEET_LIST_CTL%>">

	<div style="margin-top: -10;">
		<div class="form-group">
			<label for="inputEmail3">Name:</label> <input type="text"
				value="<%=ServletUtility.getParameter("name", request)%>"
				class="form-control " id="inputEmail3" placeholder="Name"
				name="name">
		</div>
		&emsp;&emsp;
		<div class="form-group">
			<label class="inputEmail3" for="inputEmail3">Roll No :</label> <input
				value="<%=ServletUtility.getParameter("rollNo", request)%>"
				type="text" class="form-control" id="inputEmail3"
				placeholder="Roll No" name="rollNo">
		</div>
		&emsp; &emsp;

		<button type="submit" class="btn btn-primary" name="operation"
			value="<%=MarksheetListCtl.OP_SEARCH%>">
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

	<table class="table  table-inverse table-striped">
		<tr>
			<th
				class="col-md-1 <%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"><input
				type="checkbox" onclick="checkAll(this)" />&emsp;<span
				class="glyphicon glyphicon-check"></span></th>
			<th><span class="glyphicon glyphicon-list-alt"></span> S.No.</th>
			<th><span class="glyphicon glyphicon-tasks"></span> Roll No.</th>
			<th><span class="glyphicon glyphicon-user"></span> Name</th>
			<th><span class="glyphicon glyphicon-align-center"></span>
				Physics</th>
			<th><span class="glyphicon glyphicon-align-center"></span>
				Chemistry</th>
			<th><span class="glyphicon glyphicon-align-center"></span> Maths</th>
			<th
				class="col-md-1 <%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"><span
				class="glyphicon glyphicon-edit"></span> Edit</th>


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
				Iterator<MarksheetDTO> it = list.iterator();
				while (it.hasNext()) {
					MarksheetDTO dto = it.next();
			%>



			<td class="<%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"><input
				type="checkbox" name="ids" value="<%=dto.getId()%>"></td>
			<td><%=index++%></td>

			<td><%=dto.getRollNo()%></td>
			<td><%=dto.getName()%></td>
			<td><%=dto.getPhysics()%></td>
			<td><%=dto.getChemistry()%></td>
			<td><%=dto.getMaths()%></td>
			<td class="<%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"><a
				href="MarksheetCtl.do?id=<%=dto.getId()%>"><span
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
		name="operation" value="<%=MarksheetListCtl.OP_PREVIOUS%>">
		<span class="glyphicon glyphicon-backward"></span> Previous
	</button>


	<button type="submit"
		class="btn btn-primary text-center <%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"
		name="operation" value="<%=MarksheetListCtl.OP_NEW%>">
		<span class="glyphicon glyphicon-plus"></span> New
	</button>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	<button type="submit"
		class="btn btn-primary text-center <%=role.equalsIgnoreCase("Admin") ? " " : "hidden"%>"
		name="operation" value="<%=MarksheetListCtl.OP_DELETE%>">
		<span class="glyphicon glyphicon-trash"></span> Delete
	</button>
	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=MarksheetListCtl.OP_NEXT%>">
		<span class="glyphicon glyphicon-forward"></span> Next
	</button>





	<%-- <button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=MarksheetListCtl.OP_NEXT%>">Next</button> --%>
	<div class="container">
		<ul class="pagination pagination-lg">
			<%
				double i = (Integer) request.getAttribute("listSize");
				i = Math.ceil(i * 1.0 / 5.0);
				int jo = (int) i;
				for (int j = 1; j <= jo; j++) {
			%>
			<li <%if (j == pageNo) {%> class="active" <%}%>><a
				href="MarksheetListCtl.do?pageNo=<%=j%>&name=<%=ServletUtility.getParameter("name", request)%>&rollNo=<%=ServletUtility.getParameter("rollNo", request)%>"><%=j%></a></li>


			<%
				}
			%>
		</ul>
	</div>
</form>

<%@ include file="Footer.jsp"%>

