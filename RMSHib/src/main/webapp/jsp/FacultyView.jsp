
<%@page import="in.co.mss.rmshib.controller.FacultyListCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rmshib.controller.FacultyCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="../js/calendar.js"></script>
<jsp:include page="Home.jsp"></jsp:include>
<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.FacultyDTO"
	scope="request"></jsp:useBean>
<html>
<body>
	<script type="text/javascript" src="../js/calendar.js"></script>
	<div class="container text-center"
		style="margin-top: -30; margin-left: 120;">
		<h1 class="text-primary">
			<b> <%
 	long id = DataUtility.getLong(request.getParameter("id"));
 	if (id > 0) {
 %> Edit Faculty <%
 	} else {
 %> Add Faculty <%
 	}
 %>
			</b>
		</h1>


		<%
			List l = (List) request.getAttribute("collegeList");
		%>

		<%
			List l1 = (List) request.getAttribute("courseList");
		%>


		<%
			HashMap map = new HashMap();
			map.put("Physics", "Physics");
			map.put("Chemistry", "Chemistry");
			map.put("Maths", "Maths");

			String htmlList = HTMLUtility.getList("primarySubject",
					dto.getPrimarySubject(), map);
		%>

		<%
			HashMap map1 = new HashMap();
			map1.put("Physics", "Physics");
			map1.put("Chemistry", "Chemistry");
			map1.put("Maths", "Maths");

			String htmlList1 = HTMLUtility.getList("secondarySubject",
					dto.getSecondarySubject(), map1);
		%>


		<div class="alert alert-success"
			<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
			<%=ServletUtility.getSuccessMessage(request)%></div>
		<div class="alert alert-danger"
			<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>
	</div>


	<div class="col-sm-offset-4" style="margin-bottom: 8;">

		<form method="post" class="form-horizontal"
			action="<%=ORSView.FACULTY_CTL%>" id="sandbox-container" align="left">

			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
			<input type="hidden" name="createdDatetime"
				value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
				name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

			<div class="form-group pull center">

				<div class="form-group">
					<label for="collegeId" class="col-sm-3 control-label">
						<div class="text-left">College:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -98"><%=HTMLUtility.getList("collegeId",
					String.valueOf(dto.getCollegeId()), l)%></div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("College", request)%></label>
				</div>

				<div class="form-group" style="margin-top: -8;">
					<label for="courseId" class="col-sm-3 control-label">
						<div class="text-left">Course:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -98"><%=HTMLUtility.getList("courseId",
					String.valueOf(dto.getCourseId()), l1)%></div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("Course", request)%></label>
				</div>

				<div class="form-group" style="margin-top: -8;">
					<label for="primarySubject" class="col-sm-3 control-label">
						<div class="text-left">Primary Subject:</div>
					</label>

					<div class="col-sm-4" style="margin-left: -98"><%=htmlList%></div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("primarySubject", request)%></label>
				</div>

				<div class="form-group" style="margin-top: -8;">
					<label for="secondarySubject" class="col-sm-3 control-label">
						<div class="text-left">Secondary Subject:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -98"><%=htmlList1%></div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("secondarySubject",
					request)%></label>
				</div>

				<div class="form-group" style="margin-top: -8;">
					<label for="inputPassword3" class="col-sm-3 control-label"><div
							class="text-left">First Name:</div></label>
					<div class="col-sm-4" style="margin-left: -100">
						<input type="text" class="form-control" id="inputEmail3"
							name="firstName"
							value="<%=DataUtility.getStringData(dto.getFirstName())%>"
							placeholder="First Name">
					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("firstName", request)%></label>
				</div>


				<div class="form-group" style="margin-top: -8;">
					<label for="inputPassword3" class="col-sm-3 control-label">
						<div class="text-left">Last Name:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">
						<input type="text" class="form-control" id="inputEmail3"
							name="lastName"
							value="<%=DataUtility.getStringData(dto.getLastName())%>"
							placeholder="Last Name">
					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("lastName", request)%></label>
				</div>

				<div class="form-group" style="margin-top: -8;">
					<label for="inputPassword3" class="col-sm-3 control-label">
						<div class="text-left">Qualification:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">
						<input type="text" class="form-control" id="inputEmail3"
							name="qualification"
							value="<%=DataUtility.getStringData(dto.getQualification())%>"
							placeholder="Highest Qualification">
					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("qualification", request)%></label>
				</div>

				<div class="form-group" style="margin-top: -8;">
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

				<div class="form-group" style="margin-top: -8;">
					<label for="inputPassword3" class="col-sm-3 control-label">
						<div class="text-left">Mobile No:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">
						<input type="text" class="form-control" id="inputEmail3"
							name="mobileNo"
							value="<%=DataUtility.getStringData(dto.getMobileNo())%>"
							placeholder="Mobile No">
					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
				</div>


				<div class="form-group" style="margin-top: -8;">
					<label for="inputPassword3" class="col-sm-3 control-label">
						<div class="text-left">Email Id:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">
						<input type="text" class="form-control" id="inputEmail3"
							name="email"
							value="<%=DataUtility.getStringData(dto.getEmail())%>"
							<%=(id > 0) ? "readonly" : ""%> placeholder="Email ID">
					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("email", request)%></label>
				</div>


				<%-- 		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				Address :</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputEmail3"
					name="address" value="<%=DataUtility.getStringData(dto.)%>"
					placeholder="Address">
			</div>
			<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("email", request)%></label>
		</div>
 --%>
				<div class="col-sm-offset-2 col-sm-10" style="margin-top: -12;">
					<button type="submit" class="btn btn-primary text-center"
						style="margin-left: -28;" name="operation"
						value="<%=FacultyCtl.OP_SAVE%>">
						<span class="glyphicon glyphicon-floppy-save"></span> Save
					</button>

					&emsp;&emsp;&emsp;
					<button type="submit" class="btn btn-primary" name="operation"
						value="<%=FacultyCtl.OP_CANCEL%>">
						<span class="glyphicon glyphicon-remove"></span> Cancel
					</button>
					<%-- <%
				if (dto.getId() > 0l) {
			%> --%>
					<%-- 	<button type="submit" class="btn btn-primary" name="operation"
				value="<%=UserCtl.OP_DELETE%>">Delete</button> --%>
					<%-- <%
				}
			%> --%>

				</div>
			</div>

		</form>
	</div>
	<br>
	<br>
</body>
</html>

<!-- <div style="position: relative; text-align: center"></div> -->
<br>
<div>
	<%@ include file="Footer.jsp"%>
</div>