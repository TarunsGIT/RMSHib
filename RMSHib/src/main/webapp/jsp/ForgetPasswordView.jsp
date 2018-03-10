 <%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ForgetPasswordCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project3</title>
</head>
<body>
	<%@ include file="Home.jsp"%>
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.UserDTO"
		scope="request"></jsp:useBean>

	<div class="container text-center">
       
		<h1 class="text-primary"><b>Forgot Your Password??</b></h1>
		
		<br>

		<div class="alert alert-success" role="alert"
			<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
			<%=ServletUtility.getSuccessMessage(request)%></div>
		<div class="alert alert-danger" role="alert"
			<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>
				
		<h3>Submit your email address and we'll send you password.</h3>

	</div>
	<br>
	<form class="form-horizontal col-sm-offset-4"
		action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">
		<div class="form-group ">
			<table>
				<label for="inputEmail3" class="col-sm-2 control-label">
					<b>Email Id:</b></label>
				<div class="col-sm-4">
					<input type="email" class="form-control" id="inputEmail3"
						name="login" placeholder="Enter ID Here"
						value="<%=ServletUtility.getParameter("login", request)%>">
						
				</div>
				


				<button type="submit" class="btn btn-primary" name="operation"
					value="<%=ForgetPasswordCtl.OP_GO%>">GO</button>
				<div>
					<label class="col-sm-4 control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("login", request)%></label>
				</div>

			</table>
			</div>
	</form>



</body>

</html>
<br>
<br>
<jsp:include page="Footer.jsp"></jsp:include>
<!-- <div class="col-sm-10">
	<H3>
		Copyrights<span class="glyphicon glyphicon-copyright-mark"></span>
		RAYS Technologies
	</H3>
</div>
 -->