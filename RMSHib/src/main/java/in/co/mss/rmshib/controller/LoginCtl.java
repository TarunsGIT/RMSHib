package in.co.mss.rmshib.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.RoleDTO;
import in.co.mss.rmshib.dto.UserDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.RoleModelInt;
import in.co.mss.rmshib.model.UserModelInt;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;
import in.co.mss.rmshib.controller.ORSView;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/ctl/LoginCtl" })
public class LoginCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");

		boolean pass = true;

		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");
		System.out.println(login + request.getParameter("password"));
		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", ""));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}

		log.debug("LoginCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("LoginCtl Method populatebean Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setLogin(DataUtility.getString(request.getParameter("login")));
		dto.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("LoginCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * Display Login form
	 * 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug(" Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// System.out.println("In loginCtl"+op);
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		RoleModelInt role = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			UserDTO UserDTO;
			try {
				UserDTO = model.findByPK(id);
				ServletUtility.setDto(UserDTO, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_LOG_OUT.equalsIgnoreCase(op)) {

			// System.err.println(session.getAttribute("user")+"session checking");
			session = request.getSession();
			session.invalidate();
			/*
			 * UserDTO user = (UserDTO) session.getAttribute("user");
			 * System.out.println(user.getFirstName());
			 */
			ServletUtility.setErrorMessage(" YOU HAVE LOGGED OUT !!!!", request);
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
			/* ServletUtility.redirect(ORSView.LOGIN_CTL, request, response); */

			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl Method doGet Ended");

	}

	/**
	 * Submitting or login action performing
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug(" Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		RoleModelInt role = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserDTO dto = (UserDTO) populateDTO(request);

			try {

				dto = model.authenticate(dto.getLogin(), dto.getPassword());

				if (dto != null) {
					session.setAttribute("user", dto);
					long rollId = dto.getRoleId();

					RoleDTO roleDto = role.findByPK(rollId);

					if (roleDto != null) {
						session.setAttribute("role", roleDto.getName());
					}

					ServletUtility.forward(ORSView.WELCOME_VIEW, request,
							response);
					return;
				} else {
					dto = (UserDTO) populateDTO(request);
					ServletUtility.setDto(dto, request);
					ServletUtility.setErrorMessage(
							"Invalid LoginId And Password", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_LOG_OUT.equals(op)) {

			session = request.getSession();

			session.invalidate();

			// System.err.println(session.getAttribute("user")+"session checking");
			ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

			return;

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,
					response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}