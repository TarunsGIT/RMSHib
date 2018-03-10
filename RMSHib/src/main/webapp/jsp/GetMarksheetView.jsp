<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.GetMarksheetCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project3</title>
</head>
<body>
	<%@ include file="Home.jsp"%>
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.MarksheetDTO"
		scope="request"></jsp:useBean>

	<div class="container text-center" style="margin-right: 4%">
		<h1 class="text-primary">
			<b>Get Marksheet</b>
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
	<br>

	<div class="col-sm-offset-4">
		<form class="form-horizontal" method="post"
			action="<%=ORSView.GET_MARKSHEET_CTL%>">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">
					<b> Roll No:</b></label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="inputEmail3"
						name="rollNo"
						value="<%=ServletUtility.getParameter("rollNo", request)%>"
						placeholder="Enter Roll No">
				</div>
				<div>

					<button type="submit" class="btn btn-primary" name="operation"
						value=<%=GetMarksheetCtl.OP_GO%>>GO</button>

				</div>
				<div>
					<label class="control-label col-sm-5 text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("rollNo", request)%></label>

				</div>


			</div>

		</form>
		<%
			if (dto.getRollNo() != null && dto.getRollNo().trim().length() > 0) {
		%>

		<br>
		<div class="col-md-4 col-md-offset-2" style="margin-left: 15%">
			<table class="table table-bordered">
				<tr class="success">
					<td><span class="glyphicon glyphicon-tasks"></span> Roll No</td>
					<td><%=DataUtility.getStringData(dto.getRollNo())%></td>
				</tr>
				<tr class="danger">
					<td><span class="glyphicon glyphicon-user"></span> Name</td>
					<td><%=DataUtility.getStringData(dto.getName())%></td>
				</tr>
				<tr class="success">
					<td><span class="glyphicon glyphicon-align-center"></span> Physics</td>
					<td><%=DataUtility.getStringData(dto.getPhysics())%></td>
				</tr>
				<tr class="danger">
					<td><span class="glyphicon glyphicon-align-center"></span> Chemistry</td>
					<td><%=DataUtility.getStringData(dto.getChemistry())%></td>
				</tr>
				<tr class="success">
					<td><span class="glyphicon glyphicon-align-center"></span> Maths</td>
					<td><%=DataUtility.getStringData(dto.getMaths())%></td>

				</tr>
				<tr class="danger">
					<td><span class="glyphicon glyphicon-align-center"></span> Total</td>
					<td><%=DataUtility.getStringData(dto.getMaths()
						+ dto.getPhysics() + dto.getChemistry())%></td>

				</tr>
				<tr>

				</tr>
			</table>
		</div>
		<%
			}
		%>
	</div>



	<br>
	<br>
	<div style="position: relative; text-align: center"></div>
	<%@ include file="Footer.jsp"%>

</body>
</html>