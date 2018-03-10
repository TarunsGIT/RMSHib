package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.StudentDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.CollegeModelInt;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.StudentModelInt;
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
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "StudentCtl", urlPatterns = { "/ctl/StudentCtl.do" })
public class StudentCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("StudentCtl Method validate Started");

		boolean pass = true;
		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String college = request.getParameter("collegeId");
		System.out.println("COLLEGE ID=" + college);
		/*
		 * if
		 * (DataValidator.isNull(request.getParameter("firstName"))||DataValidator
		 * .isNotAlpha(request.getParameter("firstName"))) {
		 * request.setAttribute("firstName",
		 * PropertyReader.getValue("error.get", "First Name")); pass = false; }
		 */
		if (DataValidator.isNull(college)) {
			request.setAttribute("College",
					PropertyReader.getValue("error.require", "College Name"));
			pass = false;

		}

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
		} else if (!DataValidator.isAlphabetsOnly(request
				.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.last", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.phone", "Mobile No."));
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

		}

		if (DataValidator.isNull(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", "Email "));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.email", ""));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId",
					PropertyReader.getValue("error.require", "College Name"));
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

		log.debug("StudentCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("StudentCtl Method populateDTO Started");

		StudentDTO dto = new StudentDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

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

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		dto.setEmail(DataUtility.getString(request.getParameter("email")));

		dto.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

		populateBean(dto, request);

		// populateDTO(dto, request);
		// populateBean(dto, request);
		log.debug("StudentCtl Method populateDTO Ended");

		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("StudentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		if (id > 0 || op != null) {
			StudentDTO dto;
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
		log.debug("StudentCtl Method doGett Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("StudentCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			StudentDTO dto = (StudentDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"Student Edited Successfully", request);

				} else {
					long pk = model.add(dto);
					dto.setId(pk);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"Student Added Successfully", request);

				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage(
						"Student Email Id already exists", request);
			}

		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			StudentDTO bean = (StudentDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.setSuccessMessage(
						"Data is successfully deleted", request);

				ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,
						response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			if (id > 0) {
				ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
				return;
			}

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("StudentCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}

}
