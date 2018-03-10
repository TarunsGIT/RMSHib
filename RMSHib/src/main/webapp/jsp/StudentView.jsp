<%@page import="in.co.mss.rmshib.controller.StudentCtl"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Home.jsp"></jsp:include>
<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.StudentDTO"
	scope="request"></jsp:useBean>
<script type="text/javascript" src="../js/calendar.js"></script>

<div class="container text-center" style="margin-top: -10;">
	<h1 class="text-primary">
		<b> <%
 	long id = DataUtility.getLong(request.getParameter("id"));
 	if (id > 0) {
 %> Edit Student <%
 	} else {
 %> Add Student <%
 	}
 %>
		</b>
	</h1>

</div>


<div class="text-center container">
	<br>

	<div class="alert alert-success" role="alert"
		<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
		<%=ServletUtility.getSuccessMessage(request)%></div>
	<div class="alert alert-danger" role="alert"
		<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>

</div>


<%
	List l = (List) request.getAttribute("collegeList");
%>
<div class="col-sm-offset-4">
	<form class="form-horizontal" action="<%=ORSView.STUDENT_CTL%>"
		method="post" id="sandbox-container" align="left">

		<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
			type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
		<input type="hidden" name="createdDatetime"
			value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
			name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

		<div class="form-group">
			<label for="text" class="col-sm-3 control-label">
				<div class="text-left">College:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -135;">
				<%=HTMLUtility.getList("collegeId",
					String.valueOf(dto.getCollegeId()), l)%>
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("collegeId", request)%></label>
		</DIV>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">First Name :</div>
			</label>
			<div class="col-sm-4" style="margin-left: -135;">
				<input type="text" class="form-control" id="inputEmail3"
					name="firstName"
					value="<%=DataUtility.getStringData(dto.getFirstName())%>"
					placeholder="First Name">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("firstName", request)%></label>
		</div>

		<%-- 
          <div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
			studentId:</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputEmail3"
					name="subject"
					value="<%=DataUtility.getStringData(dto.getStudentId())%>"
					placeholder="Name">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("subject", request)%></label>
		</div>
		 --%>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Last Name:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -135;">
				<input type="text" class="form-control" id="inputEmail3"
					name="lastName"
					value="<%=DataUtility.getStringData(dto.getLastName())%>"
					placeholder="Last Name">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("lastName", request)%></label>
		</div>
		<%-- <div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				Date Of Birth:</label>
			<div class="col-sm-4">
				<input type="text" name="dob" readonly="readonly"
					class="form-control"
					value="<%=DataUtility.getDateString(dto.getDob())%>">
			</div>
			<a href="javascript:getCalendar(document.forms[0].dob);"> <img
				src="../img/cal.jpg" width="16" height="15" border="0"
				alt="Calender">
			</a> <label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("dob", request)%></label>
		</div> --%>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Date Of Birth:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -135;">
				<div class="input-group date">
					<input type="text" name="dob" readonly="readonly"
						class="form-control" placeholder="Month/Date/Year"
						value="<%=DataUtility.getDateString(dto.getDob())%>"><span
						class="input-group-addon" id="basic-addon2"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>

			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("dob", request)%></label>
		</div>


		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Mobile No:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -135;">
				<input type="text" class="form-control" id="inputEmail3"
					name="mobileNo"
					value="<%=DataUtility.getStringData(dto.getMobileNo())%>"
					placeholder="Mobile No">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Email ID:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -135;">
				<input type="text" class="form-control" id="inputEmail3"
					name="email" value="<%=DataUtility.getStringData(dto.getEmail())%>"
					<%=(id > 0) ? "readonly" : ""%> placeholder="Email ID">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("email", request)%></label>
		</div>
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary"
				style="margin-left: -62;" name="operation"
				value="<%=StudentCtl.OP_SAVE%>">
				<span class="glyphicon glyphicon-floppy-save"></span> Save
			</button>
			&emsp;&emsp;
			<button type="submit" class="btn btn-primary" name="operation"
				value="<%=StudentCtl.OP_CANCEL%>">
				<span class="glyphicon glyphicon-remove"></span> Cancel
			</button>
			<%-- 			<%
				if (dto.getId() > 0l) {
			%>
			<button type="submit" class="btn btn-primary" name="operation"
				value="<%=StudentCtl.OP_DELETE%>">Delete</button>
			<%
				}
			%>
 --%>
		</div>


	</form>
</div>
<%@ include file="Footer.jsp"%>