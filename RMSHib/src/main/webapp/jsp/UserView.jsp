
<%@page import="in.co.mss.rmshib.controller.UserListCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rmshib.controller.UserCtl"%>
<%@page import="in.co.mss.rmshib.util.DataUtility"%>
<%@page import="in.co.mss.rmshib.util.ServletUtility"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rmshib.util.HTMLUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<body>
	<script type="text/javascript" src="../js/calendar.js"></script>
	<jsp:include page="Home.jsp"></jsp:include>
	<jsp:useBean id="dto" class="in.co.mss.rmshib.dto.UserDTO"
		scope="request"></jsp:useBean>
	<script type="text/javascript" src="../js/calendar.js"></script>
	<div class="container text-center" style="margin-top: -30;">
		<h1 class="text-primary">
			<b> <%
 	long id = DataUtility.getLong(request.getParameter("id"));
 	if (id > 0) {
 %> Edit User <%
 	} else {
 %> Add User <%
 	}
 %>
			</b>
		</h1>


		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<%
			HashMap map = new HashMap();
			map.put("Male", "Male");
			map.put("Female", "Female");

			String htmlList = HTMLUtility.getList("gender", dto.getGender(),
					map);
		%>

		
			<div class="alert alert-success" role="alert"
				<%=ServletUtility.getSuccessMessage(request).equals("") ? "hidden"
					: ""%>>
				<%=ServletUtility.getSuccessMessage(request)%></div>
			<div class="alert alert-danger" role="alert"
				<%=ServletUtility.getErrorMessage(request).equals("") ? "hidden"
					: ""%>><%=ServletUtility.getErrorMessage(request)%></div>


		</div>


		<div class="col-sm-offset-4" style="margin-top: 9;">

			<form class="form-horizontal" action="<%=ORSView.USER_CTL%>"
				method="post" id="sandbox-container" align="left">

				<input type="hidden" name="createdBy"
					value="<%=dto.getCreatedBy()%>"> <input type="hidden"
					name="id" value="<%=dto.getId()%>"> <input type="hidden"
					name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
					type="hidden" name="createdDatetime"
					value="<%=dto.getCreatedDatetime()%>"> <input type="hidden"
					name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">

				<div class="form-group">
					<label for="text" class="col-sm-3 control-label">
						<div class="text-left">First Name:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">
						<input type="text" class="form-control" id="inputEmail"
							placeholder="First Name" name="firstName"
							value="<%=DataUtility.getStringData(dto.getFirstName())%>">
					</DIV>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("firstName", request)%></label>
				</DIV>

				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label"><div
							class="text-left">Last Name:</div></label>
					<div class="col-sm-4" style="margin-left: -100">
						<input type="text" class="form-control" id="inputEmail3"
							name="lastName"
							value="<%=DataUtility.getStringData(dto.getLastName())%>"
							placeholder="Last Name">
					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("lastName", request)%></label>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">
						<div class="text-left">Login:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">
						<%
							long id2 = DataUtility.getLong(request.getParameter("id"));
						%>

						<input type="text" class="form-control" id="inputEmail3"
							name="login"
							value="<%=DataUtility.getStringData(dto.getLogin())%>"
							<%=(id2 > 0) ? "readonly" : ""%> placeholder="Email ID">
					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("login", request)%></label>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">
						<div class="text-left">Password:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">
						<%
							long id1 = DataUtility.getLong(request.getParameter("id"));
						%>

						<%-- <input type="password" class="form-control" id="inputPassword3"
					name="password"
					value="<%=DataUtility.getStringData(dto.getPassword())%>"
					placeholder="Password">
				 --%>
						<input type="password" class="form-control" id="inputPassword3"
							name="password"
							value="<%=DataUtility.getStringData(dto.getPassword())%>"
							<%=(id1 > 0) ? "readonly" : ""%> placeholder="Password">


					</div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("password", request)%></label>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">
						<div class="text-left">Confirm Password:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100">

						<%-- 	<input type="password" class="form-control" id="inputPassword3"
					name="confirmPassword"
					value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>"
					placeholder="Confirm Password">
			
			 --%>

						<input type="password" class="form-control" id="inputPassword3"
							name="confirmPassword" <%if (id1 > 0) {%>
							value="<%=DataUtility.getStringData(dto.getPassword())%>"
							<%} else {%>
							value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>"
							<%}%> <%=(id1 > 0) ? "readonly" : ""%>
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
						<div class="text-left">Gender:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100"><%=htmlList%></div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("gender", request)%></label>
				</div>

				<div class="form-group">
					<label for="roleId" class="col-sm-3 control-label">
						<div class="text-left">Role:</div>
					</label>
					<div class="col-sm-4" style="margin-left: -100"><%=HTMLUtility.getList("roleId",
					String.valueOf(dto.getRoleId()), l)%></div>
					<label class="control-label text-danger" for="inputError1"><%=ServletUtility.getErrorMessage("roleId", request)%></label>
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
				

				<div class="col-sm-offset-2 col-sm-10" style="margin-top: -8;">
					<button type="submit" class="btn btn-primary text-center"
						style="margin-left: -28" name="operation"
						value="<%=UserCtl.OP_SAVE%>">
						<span class="glyphicon glyphicon-floppy-save"></span> Save
					</button>
					<%-- <button type="submit" class="btn btn-primary" name="operation"
				value="<%=UserCtl.OP_SAVE%>">Save</button> --%>
					&emsp;&emsp;&emsp;
					<button type="submit" class="btn btn-primary" name="operation"
						value="<%=UserCtl.OP_CANCEL%>">
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


			</form>
		</div>
		<br> <br>
</body>
</html>
<!-- <div style="position: relative; text-align: center"></div> -->
<%@ include file="Footer.jsp"%>