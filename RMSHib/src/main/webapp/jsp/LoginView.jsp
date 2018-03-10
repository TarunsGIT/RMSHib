<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="in.co.mss.rmshib.controller.LoginCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<jsp:include page="Home.jsp"></jsp:include>

<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.UserDTO"
	scope="request"></jsp:useBean>

<link rel="stylesheet" href="../css/register.css">


<body>
	<div class=" col-sm-offset-4 col-sm-7">
		<!-- <h1 class="col-sm-offset-2 text-primary">
			<b>Login</b>
		</h1> -->
		<div class="col-sm-offset-2" style="margin-left: 150;">
			<img alt="Brand" src="../image/login2.png" width="100" height="100">
		</div>
	</div>

	<br>

		<div class="container text-center">

<fieldset class="account-info">
			<div class="alert alert-success" role="alert"
				style="margin-top: -10;"
				<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
				<span class="glyphicon glyphicon-saved"></span><%=ServletUtility.getSuccessMessage(request)%></div>
			<div class="alert alert-danger" role="alert"
				<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>>
				&emsp;<span class="glyphicon glyphicon-exclamation-sign"></span><%=ServletUtility.getErrorMessage(request)%></div>
</fieldset>
		</div>

	<center>
		<form action="<%=ORSView.LOGIN_CTL%>" method="post">

			<fieldset class="account-info">

				<div class="form-group ">


					<div class="input-group">

						<label><span class="glyphicon glyphicon-user"></span> <font
							color="#1464F4"><b>LOGIN ID</b></font> <input type="text"
							id="inputEmail3" name="login" placeholder="name@domain.com"
							value="<%=DataUtility.getStringData(dto.getLogin())%>"> </label>
					</div>
					<label class="control-label text-danger" for="inputError1"><font
						color="maroon"> <%=ServletUtility.getErrorMessage("login", request)%></font></label>
				</div>
				<div class="form-group ">


					<div class="input-group">
						<label><span class="glyphicon glyphicon-lock"></span> <font
							color="#1464F4">PASSWORD</font> <input type="password"
							name="password" id="inputPassword3" placeholder="Password"
							value="<%=DataUtility.getStringData(dto.getPassword())%>">
						</label>

					</div>

					<label class="control-label text-danger" for="inputError1"><font
						color="maroon"> <%=ServletUtility.getErrorMessage("password", request)%></font></label>

				</div>
			</fieldset>
			<fieldset class="account-action">
				<input type="submit" class="btn" name="operation"
					value="<%=LoginCtl.OP_SIGN_IN%>">

			</fieldset>


		</form>
		<span class="pull-center" style="margin-left: 145;"><a
			class="text-danger" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forgot
					password?&nbsp;</b><span class="glyphicon glyphicon-forward"></span></a></span><br>
		<span class="pull-center" style="margin-left: 145;"><a
			class="primary" href="<%=ORSView.USER_REGISTRATION_CTL%>"><b>
					Create an account?&nbsp;</b><span class="glyphicon glyphicon-forward"></span>
		</a></span>

	</center>

	<%@ include file="Footer.jsp"%>
</body>
</html>