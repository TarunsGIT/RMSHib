package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.UserDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.RecordNotFoundException;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.UserModelInt;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Change Password functionality Controller. Performs operation for Change
 * Password
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "ChangePasswordCtl", urlPatterns = { "/ctl/ChangePasswordCtl.do" })
public class ChangePasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";

	private static Logger log = Logger.getLogger(ChangePasswordCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChangePasswordCtl Method validate Started");

		boolean pass = true;
		String op = request.getParameter("operation");
		if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			return pass;
		}
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword",
					PropertyReader.getValue("error.require", "Old Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword",
					PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		} else if ((request.getParameter("newPassword")).length() < 6
				|| (request.getParameter("newPassword")).length() > 11) {
			request.setAttribute("newPassword",
					PropertyReader.getValue("error.pass", "New Password"));
			pass = false;

		}

		else if (request.getParameter("oldPassword").equals(
				request.getParameter("newPassword"))) {
			ServletUtility.setErrorMessage(
					"New Password Cant be same as Old Password", request);
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		if (!request.getParameter("newPassword").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword",
					PropertyReader.getValue("error.npass", ""));
			/*
			 * ServletUtility.setErrorMessage(
			 * "New and confirm passwords not matched", request);
			 */
			pass = false;
		}

		log.debug("ChangePasswordCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("ChangePasswordCtl Method populateDTO Started");

		UserDTO dto = new UserDTO();

		dto.setPassword(DataUtility.getString(request
				.getParameter("oldPassword")));
		dto.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));
		log.debug("ChangePasswordCtl Method populateDTO Ended");

		return dto;
	}

	/**
	 * Display Logics inside this method
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		log.debug("ChangePasswordCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		UserDTO dto = (UserDTO) populateDTO(request);

		UserDTO userdto = (UserDTO) session.getAttribute("user");

		String newPassword = (String) request.getParameter("newPassword");

		long id = userdto.getId();

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				boolean flag = model.changePassword(id, dto.getPassword(),
						newPassword);
				if (flag == true) {
					dto = model.findByLogin(userdto.getLogin());
					session.setAttribute("user", dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"Password has been changed Successfully", request);
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;

			} catch (RecordNotFoundException e) {
				ServletUtility.setErrorMessage("Old PassWord is Invalid",
						request);
			}

		} else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
			return;

		}

		ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
		log.debug("ChangePasswordCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
