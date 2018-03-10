package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
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

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * * User functionality Controller. Performs operation for add, update and get
 * User
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl.do" })
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		try {
			List l = model.list();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;
		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		String role = request.getParameter("roleId");

		if (DataValidator.isNull(request.getParameter("firstName"))) {

			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.alpha", "First Name"));

		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {

			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.alpha", "Last Name"));
			pass = false;
		} else if (!DataValidator.isAlphabetsOnly(request
				.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.last", "Last Name"));
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
		if (DataValidator.isNull(role)) {
			request.setAttribute("roleId",
					PropertyReader.getValue("error.require", "Role"));
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

			pass = false;
		}

		/*
		 * long id1 = DataUtility.getLong(request.getParameter("id"));
		 * 
		 * if (id1 == 0) {
		 * 
		 * if (!request.getParameter("password").equals(
		 * request.getParameter("confirmPassword")) &&
		 * !"".equals(request.getParameter("confirmPassword"))) {
		 * request.setAttribute("confirmPassword",
		 * PropertyReader.getValue("error.cpass", ""));
		 * 
		 * pass = false; } } else {
		 * 
		 * String password = request.getParameter("password");
		 * 
		 * UserBean bean = new UserBean(); bean.setPassword(password);
		 * request.setAttribute("pass", password);
		 * System.out.println("*****************" + password);
		 * bean.setPassword(password);
		 * 
		 * pass = true; }
		 */log.debug("UserCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("UserCtl Method populatebean Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		dto.setFirstName(DataUtility.getCapital(request
				.getParameter("firstName")));

		/*
		 * dto.setFirstName(DataUtility.getString(request
		 * .getParameter("firstName")));
		 */

		dto.setLastName(DataUtility.getCapital(request.getParameter("lastName")));

		/*
		 * dto.setLastName(DataUtility.getString(request.getParameter("lastName")
		 * ));
		 */

		dto.setLogin(DataUtility.getString(request.getParameter("login")));

		dto.setPassword(DataUtility.getString(request.getParameter("password")));

		dto.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateBean(dto, request);

		log.debug("UserCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * Contains DIsplay logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			UserDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("UserCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"User edited successfully", request);

				} else {
					long pk = model.add(dto);
					dto.setId(pk);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("User added successfully",
							request);

					/*
					 * long pk = model.registerUser(dto); dto.setId(pk);
					 * ServletUtility.setSuccessMessage(
					 * "Data is successfully saved", request);
					 */
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			UserDTO dto = (UserDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.setSuccessMessage(
						"Data is successfully deleted", request);
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
						response);

				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			}

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

}
