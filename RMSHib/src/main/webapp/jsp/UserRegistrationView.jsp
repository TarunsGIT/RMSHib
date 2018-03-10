
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rmshib.controller.UserRegistrationCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="../js/calendar.js"></script>
<jsp:include page="Home.jsp"></jsp:include>
<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.UserDTO"
	scope="request"></jsp:useBean>
	
	

<div class="col-sm-offset-4">
	<div class="col-sm-offset-2" style="margin-left: 60;">
		<h1 class="text-primary"><b>User Registration</b></h1>
	</div>
	<br>

	<%
		HashMap map = new HashMap();
		map.put("Male", "Male");
		map.put("Female", "Female");

		String htmlList = HTMLUtility.getList("gender", dto.getGender(),
				map);
	%>
	<br>

	<div class="alert alert-success" role="alert"
		<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
		<%=ServletUtility.getSuccessMessage(request)%></div>
	<div class="alert alert-danger" role="alert"
		<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>





	<form method="post" class="form-horizontal" id="sandbox-container"
		action="<%=ORSView.USER_REGISTRATION_CTL%>">

		<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
			type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
		<input type="hidden" name="createdDatetime"
			value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
			name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

		<div class="form-group">
			<label for="text" class="col-sm-3 control-label">
			<div class="text-left">First Name:</div></label>
			<div class="col-sm-4" style="margin-left: -100;">
				<input type="text" class="form-control" id="inputEmail3"
					placeholder="First Name" name="firstName"
					value="<%=DataUtility.getStringData(dto.getFirstName())%>">
			</DIV>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("firstName", request)%></label>
		</DIV>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Last Name:</div></label>
			<div class="col-sm-4" style="margin-left: -100;">
				<input type="text" class="form-control" id="inputEmail3"
					name="lastName"
					value="<%=DataUtility.getStringData(dto.getLastName())%>"
					placeholder="Last Name">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("lastName", request)%></label>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Login:</div></label>
			<div class="col-sm-4" style="margin-left: -100;">
				<input type="text" class="form-control" id="inputEmail3"
					name="login" value="<%=DataUtility.getStringData(dto.getLogin())%>"
					placeholder="Email ID">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("login", request)%></label>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Password:</div></label>
			<div class="col-sm-4" style="margin-left: -100;">
				<input type="password" class="form-control" id="inputPassword3"
					name="password"
					value="<%=DataUtility.getStringData(dto.getPassword())%>"
					placeholder="Password">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("password", request)%></label>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label" >
				<div class="text-left">Confirm Password:</div></label>
			<div class="col-sm-4" style="margin-left: -100;">
				<input type="password" class="form-control" id="inputPassword3"
					name="confirmPassword"
					value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>"
					placeholder="Confirm Password">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></label>
		</div>

		<%-- 		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
			GENDER:</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputEmail3"
					name="subject"
					value="<%=DataUtility.getStringData(dto.getGender())%>"
					placeholder="Gender">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("subject", request)%></label>
		</div>
 --%>
		<div class="form-group">
			<label for="gender" class="col-sm-3 control-label">
			<div class="text-left">Gender:</div></label>
			<div class="col-sm-4" style="margin-left: -100;"><%=htmlList%></div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("gender", request)%></label>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Date Of Birth:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -100;">
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
		
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary" style="margin-left: -30;" name="operation"
				value="<%=UserRegistrationCtl.OP_SIGN_UP%>"><span class="glyphicon glyphicon-check"></span> <%=UserRegistrationCtl.OP_SIGN_UP%></button>&emsp;&emsp;&emsp;
			<button type="submit" class="btn btn-primary" name="operation"
				value="<%=UserRegistrationCtl.OP_CANCEL%>"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

		</div>


	</form>
</div>
<%@ include file="Footer.jsp"%>