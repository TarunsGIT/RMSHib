<%@page import="in.co.mss.rmshib.dto.RoleDTO"%>
<%@page import="in.co.mss.rmshib.controller.LoginCtl"%>
<%@page import="in.co.mss.rmshib.dto.UserDTO"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html lang="en">
<head>
<title>Project3</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">




<link rel="stylesheet" href="../css/bootstrap.min.css">

<link rel="stylesheet" href="css/pickmeup.min.css">

<link rel="stylesheet" href="css/datepicker.css">
<link rel="stylesheet" href="../css/bootstrap-datepicker.min.css">




<!-- jQuery library -->

<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="../js/bootstrap-datepicker.min.js"></script>
<!-- <link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script> -->
</head>


<body>
	<%
		UserDTO userDTO = (UserDTO) session.getAttribute("user");

		boolean userLoggedIn = userDTO != null;

		String welcomeMsg = "User";
		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");

			welcomeMsg = userDTO.getFirstName() + " [" + role + "]";
		} else {
			//			welcomeMsg += "Guest";
		}
	%>

	<!-- <div class="container-fluid"> -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="/bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"><img alt="Brand"
					title="SunilOS Open Source Technolgies Center"
					src="../image/logo.png" width="140" height="30"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->


			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<%=ORSView.WELCOME_CTL%>"> <span
							class="glyphicon glyphicon-home"></span> &nbsp;Home
					</a></li>

					<%
						if (userLoggedIn) {
							if (userDTO.getRoleId() == RoleDTO.ADMIN) {
					%>



					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">User <span
							class="glyphicon glyphicon-menu-down"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=ORSView.USER_CTL%>"><span
									class="glyphicon glyphicon-plus"></span>&emsp;Add User</a></li>
							<li class="divider" style="background-color: Grey;"></li>
							<li><a href="<%=ORSView.USER_LIST_CTL%>"><span
									class="glyphicon glyphicon-th-list"></span>&emsp;User List</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Role <span
							class="glyphicon glyphicon-menu-down"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=ORSView.ROLE_CTL%>"><span
									class="glyphicon glyphicon-plus"></span>&emsp;Add Role </a></li>
							<li class="divider" style="background-color: Grey;"></li>
							<li><a href="<%=ORSView.ROLE_LIST_CTL%>"><span
									class="glyphicon glyphicon-th-list"></span>&emsp;Role List</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Student <span
							class="glyphicon glyphicon-menu-down"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=ORSView.STUDENT_CTL%>"><span
									class="glyphicon glyphicon-plus"></span>&emsp;Add Student</a></li>
							<li class="divider" style="background-color: Grey;"></li>
							<li><a href="<%=ORSView.STUDENT_LIST_CTL%>"><span
									class="glyphicon glyphicon-th-list"></span>&emsp;Student List</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">College <span
							class="glyphicon glyphicon-menu-down"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=ORSView.COLLEGE_CTL%>"><span
									class="glyphicon glyphicon-plus"></span>&emsp;Add College </a></li>
							<li class="divider" style="background-color: Grey;"></li>
							<li><a href="<%=ORSView.COLLEGE_LIST_CTL%>"><span
									class="glyphicon glyphicon-th-list"></span>&emsp;College List</a></li>
						</ul></li>
					<%
						}
					%>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Marksheet <span
							class="glyphicon glyphicon-menu-down"></span></a>
						<ul class="dropdown-menu">
							<li
								class="<%=RoleDTO.ADMIN == userDTO.getRoleId() ? " " : "hidden"%>"><a
								href="<%=ORSView.MARKSHEET_CTL%>"><span
									class="glyphicon glyphicon-plus"></span>&emsp;Add Marksheet </a></li>
							<li class="divider" style="background-color: Grey;"></li>
							<%
								if (userDTO.getRoleId() != RoleDTO.KIOSK) {
							%>
							<li
								class="<%=!(RoleDTO.STUDENT == userDTO.getRoleId()) ? " "
							: "hidden"%>"><a
								href="<%=ORSView.MARKSHEET_LIST_CTL%>"><span
									class="glyphicon glyphicon-th-list"></span>&emsp;Marksheet List</a></li>
							<li class="divider" style="background-color: Grey;"></li>
							<li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><span
									class="glyphicon glyphicon-th-list"></span>&emsp;Marksheet
									Merit List</a></li></li>
					<li class="divider" style="background-color: Grey;"></li>
					<%
						}
					%>
					<li><a href="<%=ORSView.GET_MARKSHEET_CTL%>"><span
							class="glyphicon glyphicon-search"></span>&emsp;Get Marksheet</a></li>
				</ul></li>
				<%
					if (userDTO.getRoleId() != RoleDTO.KIOSK) {
				%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Course <span
						class="glyphicon glyphicon-menu-down"></span></a>
					<ul class="dropdown-menu">
						<li
							class="<%=RoleDTO.ADMIN == userDTO.getRoleId() ? " "
							: "hidden"%>"><a
							href="<%=ORSView.COURSE_CTL%>"><span
								class="glyphicon glyphicon-plus"></span>&emsp;Add Course </a></li>
						<li class="divider" style="background-color: Grey;"></li>
						<li><a href="<%=ORSView.COURSE_LIST_CTL%>"><span
								class="glyphicon glyphicon-th-list"></span>&emsp;Course List </a></li>
					</ul></li>
				<li
					class="dropdown <%=!(RoleDTO.STUDENT == userDTO.getRoleId()) ? " "
							: "hidden"%>"><a
					href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">Faculty <span
						class="glyphicon glyphicon-menu-down"></span></a>
					<ul class="dropdown-menu">
							<li
								class="<%=RoleDTO.ADMIN == userDTO.getRoleId() ? " "
							: "hidden"%>"><a
								href="<%=ORSView.FACULTY_CTL%>"><span
								class="glyphicon glyphicon-plus"></span>&emsp;Add Faculty</a></li>
								<li class="divider" style="background-color: Grey;"></li>
							<li><a href="<%=ORSView.FACULTY_LIST_CTL%>"><span
								class="glyphicon glyphicon-th-list"></span>&emsp;Faculty List
									</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">TimeTable <span
						class="glyphicon glyphicon-menu-down"></span></a>
					<ul class="dropdown-menu">
						<li
								class="<%=RoleDTO.ADMIN == userDTO.getRoleId() ? " "
							: "hidden"%>"><a
								href="<%=ORSView.TIMETABLE_CTL%>"><span
								class="glyphicon glyphicon-plus"></span>&emsp;Add Time Table</a></li>
								<li class="divider" style="background-color: Grey;"></li>
							<li><a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><span
								class="glyphicon glyphicon-th-list"></span>&emsp;Time Table
									List </a></li>
					</ul></li>

				</ul>

				<%
					}
					}
				%>

				<%-- <li class="active <%=!userLoggedIn ? "hidden" : " "%>"><a href="<%=ORSView.JAVA_DOC_VIEW%>"> JavaDoc
					</a></li> --%>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="/" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span class="glyphicon glyphicon-user"></span>&nbsp;<%=welcomeMsg%>&nbsp;
							<span class="glyphicon glyphicon-th-large"></span>&emsp;</a>

						<ul class="dropdown-menu">
							<%
								if (userLoggedIn) {
							%>
							<li><a href="<%=ORSView.MY_PROFILE_CTL%>"><span
									class="glyphicon glyphicon-user"></span> &nbsp;My Profile </a></li>
							<li
									class="<%=RoleDTO.ADMIN == userDTO.getRoleId() ? " " : "hidden"%>"">
									<a href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank"><span
										class="glyphicon glyphicon-book"></span> &nbsp;Javadoc </a></li>
							<li><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><span
									class="glyphicon glyphicon-cog"></span> &nbsp;Change Password</a></li>
							<li class="<%=userLoggedIn ? "hidden" : " "%>"><a
								href="<%=ORSView.FORGET_PASSWORD_CTL%>"><span
									class="glyphicon glyphicon-send"></span> &nbsp;Forget Password</a>
							<li class="divider" style="background-color: Grey;"></li>
							<li><a
								href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><span
									class="glyphicon glyphicon-log-out"></span> &nbsp;<b>Logout</b></a></li>
							<%
								}
							%>

							<li class="<%=userLoggedIn ? "hidden" : " "%>"><a
								href="<%=ORSView.USER_REGISTRATION_CTL%>"><span
									class="glyphicon glyphicon-user"></span> &nbsp;User
									Registration</a></li>
							<!-- <li class="divider" style="background-color: Grey;"></li> -->
							<li class="<%=userLoggedIn ? "hidden" : " "%>"><a
								href="<%=ORSView.LOGIN_CTL%>"><span
									class="glyphicon glyphicon-user"></span> &nbsp;Login</a></li>


						</ul>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- </div> -->
</body>
