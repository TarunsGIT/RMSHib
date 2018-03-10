
<%@page import="in.co.mss.rmshib.controller.CourseCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Home.jsp"></jsp:include>
<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.CourseDTO"
	scope="request"></jsp:useBean>

<div class="container text-center" style="margin-left: 80;">
	<h1 class="text-primary">
		<b> <%
 	long id = DataUtility.getLong(request.getParameter("id"));
 	if (id > 0) {
 %> Edit Course <%
 	} else {
 %> Add Course <%
 	}
 %>
		</b>
	</h1>
</div>
<br>

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

	<form class="form-horizontal" action="<%=ORSView.COURSE_CTL%>"
		method="post">

		<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
			type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
		<input type="hidden" name="createdDatetime"
			value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
			name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

		<div class="form-group">
			<label for="text" class="col-sm-3 control-label"><div
					class="text-left">Name:</div> </label>
			<div class="col-sm-4" style="margin-left: -145;">
				<input type="text" class="form-control" id="inputEmail3"
					placeholder="Course Name" name="name"
					value="<%=DataUtility.getStringData(dto.getName())%>">
			</DIV>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("name", request)%></label>
		</DIV>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Description:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -145;">
				<textarea class="form-control"
					style="width: 285px; border-width: 2px" rows="2"
					placeholder="Description" name="description"><%=DataUtility.getStringData(dto.getDescription())%></textarea>

			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("description", request)%></label>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">
				<div class="text-left">Duration:</div>
			</label>
			<div class="col-sm-4" style="margin-left: -145;">
				<input type="text" class="form-control" id="inputEmail3"
					name="duration"
					value="<%=DataUtility.getStringData(dto.getDuration()).equals("0") ? ""
					: DataUtility.getStringData(dto.getDuration())%>"
					placeholder="Duration in Years">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("duration", request)%></label>
		</div>


		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary text-center"
				style="margin-left: -72;" name="operation"
				value="<%=CourseCtl.OP_SAVE%>">
				<span class="glyphicon glyphicon-floppy-save"></span>Save
			</button>
			&emsp;&emsp;&emsp;
			<button type="submit" class="btn btn-primary text-center"
				name="operation" value="<%=CourseCtl.OP_CANCEL%>">
				<span class="glyphicon glyphicon-remove"></span>Cancel
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
