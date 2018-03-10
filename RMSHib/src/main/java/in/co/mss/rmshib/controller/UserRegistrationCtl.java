package in.co.mss.rmshib.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.RoleDTO;
import in.co.mss.rmshib.dto.UserDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.RoleModelInt;
import in.co.mss.rmshib.model.UserModelInt;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;

/**
 * User registration functionality Controller. Performs operation for User
 * Registration
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/ctl/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {

	public static final String OP_SIGN_UP = "SignUp";

	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {

			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.alpha", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {

			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isAlphabetsOnly(request
				.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.last", "Last Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.alpha", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.login", ""));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} else if ((request.getParameter("password")).length() < 6
				|| (request.getParameter("password")).length() > 11) {
			request.setAttribute("password",
					PropertyReader.getValue("error.pass", "Password"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isValidDate(new Date(dob))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		} else if (DataValidator.isUnderAge(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.underage", ""));
			pass = false;

		} else if (DataValidator.isOverAge(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.overage", ""));
			pass = false;

		}

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword",
					PropertyReader.getValue("error.cpass", ""));

			/*
			 * ServletUtility.setErrorMessage(
			 * "Password and Confirm Password does not match!!!!.", request);
			 */
			pass = false;
		}

		log.debug("UserRegistrationCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method populatebean Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRoleId(RoleDTO.STUDENT);

		/*dto.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));*/
		
		dto.setFirstName(DataUtility.getCapital(request
				.getParameter("firstName")));

		/*dto.setLastName(DataUtility.getString(request.getParameter("lastName")));*/
		
		dto.setLastName(DataUtility.getCapital(request.getParameter("lastName")));

		dto.setLogin(DataUtility.getString(request.getParameter("login")));

		dto.setPassword(DataUtility.getString(request.getParameter("password")));

		dto.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateBean(dto, request);

		// populateDTO(dto, request);
		// populateBean(dto, request);
		log.debug("UserRegistrationCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * Display concept of user registration
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserRegistrationCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);
		log.debug("UserRegistrationCtl Method doGet Ended");

	}

	/**
	 * Submit concept of user registration
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get method");
		log.debug("UserRegistrationCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request);
			try {
				long pk = model.registerUser(dto);
				dto.setId(pk);
				request.getSession().setAttribute("UserDTO", dto);
				ServletUtility
						.setSuccessMessage(
								" You have sucessfully registered with ORS!!!! Please Login",
								request);
				ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
                ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
				ServletUtility.forward(getView(), request, response);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,
					response);
		}
		log.debug("UserRegistrationCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}