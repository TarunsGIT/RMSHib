package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.UserDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.UserModelInt;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "MyProfileCtl", urlPatterns = { "/ctl/MyProfileCtl.do" })
public class MyProfileCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {
			return pass;
		}
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
		} else if (DataValidator.isNotAlpha(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.alpha", "Last Name"));
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("gender"))
				|| (request.getParameter("gender")).isEmpty()) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		} else if ((request.getParameter("mobileNo")).length() != 10) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.length", "Mobile No."));
			pass = false;

		} else if (DataValidator
				.isNotMobileNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.nmob", ""));
			pass = false;

		} else if (DataValidator.isAlpha(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.phone", "Mobile No."));
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
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
		log.debug("MyProfileCtl Method validate Ended");
		return pass;

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		// log.debug("MyProfileCtl Method populateDTO Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setLogin(DataUtility.getString(request.getParameter("login")));

		dto.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));

		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		// populateDTO(dto, request);
		// populateBean(dto, request);
		return dto;
	}

	/**
	 * Display Concept for viewing profile page view
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug("MyprofileCtl Method doGet Started");

		UserDTO dto = (UserDTO) session.getAttribute("user");
		long id = dto.getId();
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		UserModelInt model = ModelFactory.getInstance().getUserModel();
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			UserDTO userDto;
			try {
				userDto = model.findByPK(id);
				ServletUtility.setDto(userDto, request);
				System.out.println(userDto.getLastName());
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl Method doGet Ended");
	}

	/**
	 * Submit Concept
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug("MyprofileCtl Method doPost Started");

		UserDTO dto = (UserDTO) session.getAttribute("user");
		long id = dto.getId();
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserDTO userdto = (UserDTO) populateDTO(request);
			try {
				if (id > 0) {
					dto.setFirstName(userdto.getFirstName());
					dto.setLastName(userdto.getLastName());
					dto.setGender(userdto.getGender());
					dto.setMobileNo(userdto.getMobileNo());
					dto.setDob(userdto.getDob());
					model.update(dto);

				}
				ServletUtility.setDto(userdto, request);
				ServletUtility.setSuccessMessage(
						"Profile has been updated Successfully. ", request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(userdto, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
					response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

}
