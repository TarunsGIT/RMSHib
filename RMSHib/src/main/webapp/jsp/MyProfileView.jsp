
<%@page import="in.co.mss.rmshib.controller.MyProfileCtl"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Home.jsp"%>
	<script type="text/javascript" src="../js/calendar.js"></script>
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.UserDTO"
		scope="request"></jsp:useBean>

	<div class="container text-center" style="margin-top: -15;">
		<h1 class="text-primary">
			<b>My Profile</b>
		</h1>
		<br>
		<div class="alert alert-success" role="alert"
			<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
			<%=ServletUtility.getSuccessMessage(request)%></div>
		<div class="alert alert-danger" role="alert"
			<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>


	</div>

	<div class="col-sm-offset-4">
		
		<form class="form-horizontal" action="<%=ORSView.MY_PROFILE_CTL%>"
			method="post" id="sandbox-container" align="left">

			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
			<input type="hidden" name="createdDatetime"
				value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
				name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Login:</div>
				</label>
				<div class="col-sm-4" style="margin-left: -14%">
					<input type="text" class="form-control" id="inputEmail3"
						name="login"
						value="<%=DataUtility.getStringData(dto.getLogin())%>"
						readonly="readonly" placeholder="Login">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("login", request)%></label>
			</div>


		<div class="form-group">
			<label for="text" class="col-sm-3 control-label">
				<div class="text-left">First Name:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -14%">
				<input type="text" class="form-control" id="inputEmail"
					placeholder="First Name" name="firstName"
					value="<%=DataUtility.getStringData(dto.getFirstName())%>">
			</DIV>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("firstName", request)%></label>
		</DIV>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Last Name:</div>
				</label>
				<div class="col-sm-4" style="margin-left: -14%">
					<input type="text" class="form-control" id="inputEmail3"
						name="lastName"
						value="<%=DataUtility.getStringData(dto.getLastName())%>"
						placeholder="Last Name">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("lastName", request)%></label>
			</div>

			<%
				HashMap map = new HashMap();
				map.put("Male", "Male");
				map.put("Female", "Female");

				String htmlList = HTMLUtility.getList("gender", dto.getGender(),
						map);
			%>

			<div class="form-group">
				<label for="gender" class="col-sm-3 control-label">
					<div class="text-left">Gender:</div>
				</label>
				<div class="col-sm-4" style="margin-left: -14%"><%=htmlList%></div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("gender", request)%></label>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Mobile No:</div>
				</label>
				<div class="col-sm-4" style="margin-left: -14%">
					<input type="text" class="form-control" id="inputEmail3"
						name="mobileNo"
						value="<%=DataUtility.getStringData(dto.getMobileNo())%>"
						placeholder="MobileNo">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Date Of Birth:</div>
				</label>
				<div class="col-sm-4" style="margin-left: -14%">
					<div class="input-group date">
						<input type="text" name="dob" readonly="readonly"
							class="form-control" placeholder="mm/DD/yyyy"
							value="<%=DataUtility.getDateString(dto.getDob())%>"><span
							class="input-group-addon" id="basic-addon2"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>

				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("dob", request)%></label>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" style="margin-left: -7%" class="btn btn-primary" name="operation"
						value="<%=MyProfileCtl.OP_SAVE%>">
						<span class="glyphicon glyphicon-floppy-save"></span>
						<%=MyProfileCtl.OP_SAVE%></button>
					&emsp;&emsp;&emsp;

					<button type="submit" class="btn btn-primary" name="operation"
						value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
						<span class="glyphicon glyphicon-cog"></span>
						<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%></button>

				</div>

			</div>

		</form>
	</div>
</body>

</html>
<%@ include file="Footer.jsp"%>