<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.CollegeListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@page import="in.co.mss.rmshib.dto.CollegeDTO"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
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
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;College
		List</b>
</h2>

<br>




<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.COLLEGE_LIST_CTL%>">
	<div style="margin-top: -10;">

		<div class="form-group ">
			<label for="inputEmail3">Name:</label> <input type="text"
				value="<%=ServletUtility.getParameter("name", request)%>"
				class="form-control " id="inputEmail3" placeholder=" Name"
				name="name">
		</div>
		&emsp;&emsp;
		<div class="form-group">
			<label class="" for="inputEmail3">City:</label> <input type="text"
				value="<%=ServletUtility.getParameter("city", request)%>"
				class="form-control" id="inputEmail3" placeholder="City" name="city">
		</div>
		&emsp;

		<button type="submit" class="btn btn-primary" name="operation"
			value="<%=CollegeListCtl.OP_SEARCH%>">
			<span class="glyphicon glyphicon-search"></span> Search
		</button>



		<br> <br>
	</div>
	<div class="container text-center">
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
			<th><span class="glyphicon glyphicon-list-alt"></span> S.No.</th>
			<th><span class="glyphicon glyphicon-education"></span> Name</th>
			<th><span class="glyphicon glyphicon-envelope"></span> Address</th>
			<th><span class="glyphicon glyphicon-map-marker"></span> State</th>
			<th><span class="glyphicon glyphicon-map-marker"></span> City</th>
			<th><span class="glyphicon glyphicon-phone"></span> Mobile No.</th>
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
				Iterator<CollegeDTO> it = list.iterator();
				while (it.hasNext()) {
					CollegeDTO dto = it.next();
			%>



			<td><input type="checkbox" name="ids" value="<%=dto.getId()%>"></td>
			<td><%=index++%></td>

			<td><%=dto.getName()%></td>
			<td><%=dto.getAddress()%></td>
			<td><%=dto.getState()%></td>
			<td><%=dto.getCity()%></td>
			<td><%=dto.getPhoneNo()%></td>

			<td><a href="CollegeCtl.do?id=<%=dto.getId()%>"><span
					class="glyphicon glyphicon-pencil"></span></a></td>

		</tr>
		<%
			}
		%>
		</thead>
	</table>

	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
	<button type="submit"
		class="btn btn-primary pull-left <%=pageNo == 1 ? "hidden" : ""%>"
		name="operation" value="<%=CollegeListCtl.OP_PREVIOUS%>">
		<span class="glyphicon glyphicon-backward"></span> Previous
	</button>

	<button type="submit" class="btn btn-primary text-center "
		name="operation" value="<%=CollegeListCtl.OP_NEW%>">
		<span class="glyphicon glyphicon-plus"></span> New
	</button>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;


	<button type="submit" class="btn btn-primary text-center "
		name="operation" value="<%=CollegeListCtl.OP_DELETE%>">
		<span class="glyphicon glyphicon-trash"></span> Delete
	</button>
	<button type="submit"
		class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
		name="operation" value="<%=CollegeListCtl.OP_NEXT%>">
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
				href="CollegeListCtl.do?pageNo=<%=j%>&name=<%=ServletUtility.getParameter("name", request)%>&city=<%=ServletUtility.getParameter("city", request)%>"><%=j%></a></li>


			<%
				}
			%>
		</ul>
	</div>



</form>



<%@ include file="Footer.jsp"%>