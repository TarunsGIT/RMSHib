
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rmshib.controller.TimeTableCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="../js/calendar.js"></script>
<!--
	
//-->
</script>
<jsp:include page="Home.jsp"></jsp:include>
<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.TimeTableDTO"
	scope="request"></jsp:useBean>

<div class="container text-center" style="margin-left: 100;">
	<h1 class="text-primary">
		<b> <%
 	long id = DataUtility.getLong(request.getParameter("id"));
 	if (id > 0) {
 %> Edit Time Table <%
 	} else {
 %> Add Time Table <%
 	}
 %>
		</b>
	</h1>
</div>
<br>

<%
	List l = (List) request.getAttribute("courseList");
%>
<div class="text-center container">
	<div class="alert alert-success" role="alert"
		<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
		<%=ServletUtility.getSuccessMessage(request)%></div>
	<div class="alert alert-danger" role="alert"
		<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>

</div>


<div class="col-sm-offset-4">

	<form class="form-horizontal" action="<%=ORSView.TIMETABLE_CTL%>"
		method="post" id="sandbox-container" align="left">

		<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
			type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
		<input type="hidden" name="createdDatetime"
			value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
			name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

		<div class="form-group">
			<label for="Course" class="col-sm-3 control-label"><div
					class="text-left">Course:</div></label>
			<div class="col-sm-4" style="margin-left: -100;"><%=HTMLUtility.getList("courseId",
					String.valueOf(dto.getCourseId()), l)%></div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("Course", request)%></label>
		</div>

		<div class="form-group">
			<label for="Subject" class="col-sm-3 control-label"><div
					class="text-left">Subject:</div> </label>
			<%
				HashMap map = new HashMap();
				map.put("Physics", "Physics");
				map.put("Chemistry", "Chemistry");
				map.put("Maths", "Maths");

				String htmlList = HTMLUtility.getList("Subject", dto.getSubject(),
						map);
			%>

			<div class="col-sm-4" style="margin-left: -100;"><%=htmlList%></div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("Subject", request)%></label>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Examination Date:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -100;">
				<div class="input-group date">
					<input type="text" name="ExaminationDate" readonly="readonly"
						class="form-control" placeholder="Month/Date/Year"
						value="<%=DataUtility.getDateString(dto.getExaminationDate())%>"><span
						class="input-group-addon" id="basic-addon2"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>

			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("ExaminationDate", request)%></label>
		</div>

		<%-- 		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Examination Date:</div></label>
			<div class="col-sm-4" style="margin-left: -100;">
				<input type="text" name="ExaminationDate" readonly="readonly"
					class="form-control"
					value="<%=DataUtility.getDateString(dto.getExaminationDate())%>">
			</div>
			<a href="javascript:getCalendar(document.forms[0].ExaminationDate);">
				<img src="../image/cal.jpg" width="16" height="15" border="0"
				alt="Calender">
			</a> <label class="control-label text-danger" for="inputError1"><%=ServletUtility
					.getErrorMessage("ExaminationDate", request)%></label>
		</div>
 --%>
		<div class="form-group">
			<label for="Time" class="col-sm-3 control-label">
				<div class="text-left">Time Slot:</div>
			</label>
			<%
				HashMap map1 = new HashMap();
				map1.put("10:00 AM - 1:00 AM", "10:00 AM - 1:00 AM");
				map1.put("2:00 AM - 5:00 AM", "2:00 AM - 5:00 AM");

				String htmlList1 = HTMLUtility.getList("Time", dto.getTime(), map1);
			%>

			<div class="col-sm-4" style="margin-left: -100;"><%=htmlList1%></div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("Time", request)%></label>
		</div>



		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary text-center"
				style="margin-left: -30;" name="operation"
				value="<%=TimeTableCtl.OP_SAVE%>">
				<span class="glyphicon glyphicon-floppy-save"></span> Save
			</button>
			&emsp;&emsp;&emsp;
			<button type="submit" class="btn btn-primary text-center"
				name="operation" value="<%=TimeTableCtl.OP_CANCEL%>">
				<span class="glyphicon glyphicon-remove"></span> Cancel
			</button>


			<%-- <%
				if (dto.getId() > 0l) {
			%>
			<button type="submit" class="btn btn-primary" name="operation"
				value="<%=RoleCtl.OP_DELETE%>">Delete</button>
			<%
				}
			%> --%>

		</div>


	</form>
</div>







<div>
	<br> <br> <br> <br> <br>
	<%@ include file="Footer.jsp"%>
</div>
