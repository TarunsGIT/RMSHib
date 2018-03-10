H<%@page import="in.co.mss.rmshib.controller.MarksheetListCtl"%>
<%@page import="in.co.mss.rmshib.dto.MarksheetDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.mss.rmshib.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>

</head>
<jsp:include page="Home.jsp"></jsp:include>
<h2 class="container  text-center text-primary"
	>
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Marksheet
		Merit List</b>
</h2>

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



<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">



	<br>

	<table class="table table-inverse table-striped ">
		<tr>
			<th><span class="glyphicon glyphicon-list-alt"></span> S.No.</th>
			<th><span class="glyphicon glyphicon-tasks"></span> Roll No.</th>
			<th><span class="glyphicon glyphicon-user"></span> Name</th>
			<th><span class="glyphicon glyphicon-align-center"></span>
				Physics</th>
			<th><span class="glyphicon glyphicon-align-center"></span>
				Chemistry</th>
			<th><span class="glyphicon glyphicon-align-center"></span> Maths</th>
			<th><span class="glyphicon glyphicon-align-center"></span> Total</th>



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



			<td><%=index++%></td>

			<td><%=dto.getRollNo()%></td>
			<td><%=dto.getName()%></td>
			<td><%=dto.getPhysics()%></td>
			<td><%=dto.getChemistry()%></td>
			<td><%=dto.getMaths()%></td>
			<td><%=dto.getMaths() + dto.getPhysics()
						+ dto.getChemistry()%></td>

		</tr>
		<%
			}
		%>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">

	<button type="submit" class="btn btn-primary text-center "
		name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>">Back</button>


	<%-- 	<div class="container">
		<ul class="pagination pagination-lg">
			<%
				int i = (Integer) request.getAttribute("listSize");
				for (int j = 1; j <= i / 5 + 1; j++) {
			%>
			<li <%if (j == pageNo) {%> class="active" <%}%>><a
				href="MarksheetMeritListCtl.do?pageNo=<%=j%>"><%=j%></a></li>


			<%
				}
			%>
		</ul>
	</div> --%>
</form>


<%@ include file="Footer.jsp"%>
