package in.co.mss.rmshib.controller;

/**
 * Contains ORS View and Controller URI
 * 
 * @author Session Facade
 * @version 1.0
 *
 */

public interface ORSView {

	public String APP_CONTEXT = "/rmshib";

	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = APP_CONTEXT + "/jsp/ErrorView.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER
			+ "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/WelcomeView.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER
			+ "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER
			+ "/ForgetPasswordView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER
			+ "/MarksheetMeritListView.jsp";

	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimeTableView.jsp";
	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimeTableListView.jsp";

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl.do";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl.do";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl.do";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl.do";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl.do";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl.do";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl.do";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl.do";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl.do";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl.do";
	public String USER_REGISTRATION_CTL = APP_CONTEXT
			+ "/ctl/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/ctl/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/ctl/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/ctl/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl.do";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT
			+ "/ctl/ChangePasswordCtl.do";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl.do";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ctl/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT
			+ "/ctl/MarksheetMeritListCtl.do";

	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl.do";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl.do";
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl.do";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl.do";
	public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimeTableCtl.do";
	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimeTableListCtl.do";
}
