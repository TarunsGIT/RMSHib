
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ChangePasswordCtl"%>
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
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.UserDTO"
		scope="request"></jsp:useBean>
	<div class="container text-center">
		<h1 class="text-primary">
			<b>Change Password</b>
		</h1>

		<div class="alert alert-success" role="alert"
			<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
			<%=ServletUtility.getSuccessMessage(request)%></div>
		<div class="alert alert-danger" role="alert"
			<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>


	</div>
	<div class="col-sm-offset-4">

		<br>
		<form class="form-horizontal" method="post"
			action="<%=ORSView.CHANGE_PASSWORD_CTL%>">


			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Old Password:</div></label>
				<div class="col-sm-4" style="margin-left: -11%">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="Old Password" name="oldPassword"
						value="<%=DataUtility
					.getString(request.getParameter("oldPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("oldPassword")))%>">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("oldPassword", request)%></label>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">New Password:</div></label>
				<div class="col-sm-4" style="margin-left: -11%">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="New Password" name="newPassword"
						value="<%=DataUtility
					.getString(request.getParameter("newPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("newPassword")))%>">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("newPassword", request)%></label>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Confirm Password:</div></label>

				<div class="col-sm-4" style="margin-left: -11%">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="Confirm Password" name="confirmPassword"
						value="<%=DataUtility.getString(request
					.getParameter("confirmPassword") == null ? "" : DataUtility
					.getString(request.getParameter("confirmPassword")))%>">
				</div>

				<label class="control-label text-danger" for="inputError1"><%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></label>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" style="margin-left: -3%" class="btn btn-primary" name="operation"
						value="<%=ChangePasswordCtl.OP_SAVE%>">
						<span class="glyphicon glyphicon-floppy-save"></span> <%=ChangePasswordCtl.OP_SAVE%></button>
					&emsp;&emsp;&emsp;
					<button type="submit" class="btn btn-primary" name="operation"
						value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>"><span
									class="glyphicon glyphicon-user"></span> <%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%></button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>
<%@ include file="Footer.jsp"%>