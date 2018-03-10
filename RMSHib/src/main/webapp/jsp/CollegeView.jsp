
<%@page import="in.co.mss.rmshib.controller.CollegeCtl"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.mss.rmshib.controller.BaseCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>


<html>
<body>

	<%@ include file="Home.jsp"%>

	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.CollegeDTO"
		scope="request"></jsp:useBean>
	<div class="container text-center" style="margin-left: 5%;">
		<h1 class="text-primary">
			<b> <%
 	long id = DataUtility.getLong(request.getParameter("id"));
 	if (id > 0) {
 %> Edit College <%
 	} else {
 %> Add College <%
 	}
 %>
			</b>
		</h1>
<br>
		<div class="alert alert-success" role="alert"
			<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
			<%=ServletUtility.getSuccessMessage(request)%></div>
		<div class="alert alert-danger " role="alert"
			<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>
	</div>

	<div class="col-sm-offset-4">

		<form class="form-horizontal" action="<%=ORSView.COLLEGE_CTL%>"
			method="post">

			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
			<input type="hidden" name="createdDatetime"
				value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
				name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">
				

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Name:</div></label>
				<div class="col-sm-4" style="margin-left: -16%;">
					<input type="text" class="form-control" id="inputEmail3"
						name="name" value="<%=DataUtility.getStringData(dto.getName())%>"
						placeholder="Name">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("name", request)%></label>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Address:</div></label>
				<div class="col-sm-4" style="margin-left: -16%;">
					<input type="text" class="form-control" id="inputEmail3"
						name="address"
						value="<%=DataUtility.getStringData(dto.getAddress())%>"
						placeholder="Address">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("address", request)%></label>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">State:</div></label>
				<div class="col-sm-4" style="margin-left: -16%;">
					<input type="text" class="form-control" id="inputEmail3"
						name="state"
						value="<%=DataUtility.getStringData(dto.getState())%>"
						placeholder="State">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("state", request)%></label>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">City:</div></label>
				<div class="col-sm-4" style="margin-left: -16%;">
					<input type="text" class="form-control" id="inputEmail3"
						name="city" value="<%=DataUtility.getStringData(dto.getCity())%>"
						placeholder="City">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("city", request)%></label>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">
					<div class="text-left">Mobile No :</div></label>
				
				<div class="col-sm-4" style="margin-left: -16%;">
					<input type="text" class="form-control" id="inputEmail3"
						name="mobileNo"
						value="<%=DataUtility.getStringData(dto.getPhoneNo())%>"
						placeholder="Mobile No">
				</div>
				<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
			</div>

			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary" name="operation" style="margin-left: -11%;"
					value="<%=CollegeCtl.OP_SAVE%>">
					<span class="glyphicon glyphicon-floppy-save"></span> Save
				</button>
				&emsp;&emsp;
				<button type="submit" class="btn btn-primary" name="operation"
					value="<%=CollegeCtl.OP_CANCEL%>"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
				<%-- <%
					if (dto.getId() > 0l) {
				%>
				<button type="submit" class="btn btn-primary" name="operation"
					value="<%=CollegeCtl.OP_DELETE%>">Delete</button>
				<%
					}
				%> --%>

			</div>



		</form>
	</div>
</body>
</html>
<%@ include file="Footer.jsp"%>
