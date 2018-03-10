
<%@page import="in.co.mss.rmshib.dto.RoleDTO"%>
<%@page import="in.co.mss.rmshib.dto.UserDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.mss.rmshib.dto.TimeTableDTO"%>
<%@page import="in.co.mss.rmshib.controller.TimeTableListCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
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

<%
	if (userdto.getRoleId() == RoleDTO.ADMIN) {
%>
<h2 class="container  text-center text-primary" style="margin-top: -4;">
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Time
		Table List</b>
</h2>

<%
	} else {
%>
<h2 class="container  text-center text-primary" style="margin-top: -4;">
	<b><span class="glyphicon glyphicon-th-list"></span>&emsp;Exam
		Schedule</b>
</h2>

<%
	}
%>

<br>
<br>

<%
	List l = (List) request.getAttribute("courseList");
%>



<form class="form-inline container  text-center" method="post"
	action="<%=ORSView.TIMETABLE_LIST_CTL%>">
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.TimeTableDTO"
		scope="request"></jsp:useBean>
	<div style="margin-top: -20;">

		<div class="form-group">
			<label for="Course" class="col-sm-3 control-label"
				style="margin-top: 7; margin-right: -22;">Course:</label>
			<div class="col-sm-4"><%=HTMLUtility.getList("courseId",
					String.valueOf(dto.getCourseId()), l)%></div>
		</div>

		&emsp;&emsp;

		<button type="submit" class="btn btn-primary" name="operation"
			value="<%=TimeTableListCtl.OP_SEARCH%>">
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
			<th><span class="glyphicon glyphicon-book"></span> Subject</th>
			<th><span class="glyphicon glyphicon-calendar"></span>
				Examination Date</th>
			<th><span class="glyphicon glyphicon-time"></span> Time</th>
			<th><span class="glyphicon glyphicon-calendar"></span> Day</th>
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
				Iterator<TimeTableDTO> it = list.iterator();
				while (it.hasNext()) {
					TimeTableDTO tdto = it.next();
			%>

			<%
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			%>

			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<td><input type="checkbox" name="ids" value="<%=tdto.getId()%>"></td>
			<%
				}
			%>
			<td><%=index++%></td>

			<td><%=tdto.getCourse()%></td>
			<td><%=tdto.getSubject()%></td>
			<td><%=sdf.format(tdto.getExaminationDate())%></td>
			<td><%=tdto.getTime()%></td>
			<td><%=tdto.getDay()%></td>
			<%
				if (userdto.getRoleId() == RoleDTO.ADMIN) {
			%>
			<td><a href="TimeTableCtl.do?id=<%=tdto.getId()%>"><span
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
	<div style="margin-top: -5;">
		<button type="submit"
			class="btn btn-primary pull-left <%=pageNo == 1 ? "hidden" : ""%>"
			name="operation" value="<%=TimeTableListCtl.OP_PREVIOUS%>">
			<span class="glyphicon glyphicon-backward"></span> Previous
		</button>

		<%
			if (userdto.getRoleId() == RoleDTO.ADMIN) {
		%>
		<button type="submit" class="btn btn-primary text-center"
			name="operation" value="<%=TimeTableListCtl.OP_NEW%>">
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
			name="operation" value="<%=TimeTableListCtl.OP_DELETE%>">
			<span class="glyphicon glyphicon-trash"></span> Delete
		</button>
		<%
			}
		%>
		<button type="submit"
			class="btn btn-primary pull-right <%=list.size() < pageSize ? "hidden" : ""%>"
			name="operation" value="<%=TimeTableListCtl.OP_NEXT%>">
			<span class="glyphicon glyphicon-forward"></span> Next
		</button>
	</div>
	<div class="container" style="margin-top: 18;">

		<ul class="pagination pagination-lg">
			<%
				double i = (Integer) request.getAttribute("listSize");
				i = Math.ceil(i * 1.0 / 5.0);
				int jo = (int) i;
				for (int j = 1; j <= jo; j++) {
			%>
			<li <%if (j == pageNo) {%> class="active" <%}%>><a
				href="TimeTableListCtl.do?pageNo=<%=j%>&courseId=<%=ServletUtility.getParameter("courseId", request)%>"><%=j%></a></li>
			<%
				}
			%>
		</ul>
	</div>
</form>
<%@ include file="Footer.jsp"%>