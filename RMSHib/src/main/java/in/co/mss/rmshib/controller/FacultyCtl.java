package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.FacultyDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.CollegeModelInt;
import in.co.mss.rmshib.model.CourseModelInt;
import in.co.mss.rmshib.model.FacultyModelInt;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
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
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl.do" })
public class FacultyCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

		System.out.println("In facultyCtl Preload ");

		try {
			List l = model.list();

			request.setAttribute("collegeList", l);

		} catch (ApplicationException e) {
			log.error(e);
		}

		CourseModelInt course = ModelFactory.getInstance().getCourseModel();
		try {

			List l = course.list();
			request.setAttribute("courseList", l);
		} catch (ApplicationException e) {

			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FacultyCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String college = request.getParameter("collegeId");
		String course = request.getParameter("courseId");
		String primarySubject = request.getParameter("primarySubject");
		String secondarySubject = request.getParameter("secondarySubject");

		if (DataValidator.isNull(college)) {
			request.setAttribute("College",
					PropertyReader.getValue("error.require", "College Name"));
			pass = false;

		}
		if (DataValidator.isNull(course)) {
			request.setAttribute("Course",
					PropertyReader.getValue("error.require", "Course Name"));
			pass = false;

		}
		if (DataValidator.isNull(primarySubject)) {
			request.setAttribute("primarySubject", PropertyReader.getValue(
					"error.require", " Primary Subject"));
			pass = false;
		}
		if (DataValidator.isNull(secondarySubject)) {
			request.setAttribute("secondarySubject", PropertyReader.getValue(
					"error.require", " Secondary Subject"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("firstName"))) {

			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}

		else if (DataValidator.isNotAlpha(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.alpha", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {

			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}

		else if (DataValidator.isNotAlpha(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.alpha", "Last Name"));
			pass = false;
		}

		else if (!DataValidator.isAlphabetsOnly(request
				.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.last", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification",
					PropertyReader.getValue("error.require", "Qualification"));
			pass = false;

		}

		else if (DataValidator
				.isNotAlpha(request.getParameter("qualification"))) {
			request.setAttribute("qualification",
					PropertyReader.getValue("error.alpha", "Qualification"));
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}

		else if (!DataValidator.isValidDate(new Date(dob))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}

		else if (DataValidator.isUnderAge(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.underage", ""));
			pass = false;

		}

		else if (DataValidator.isOverAge(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.overage", ""));
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		}

		else if (DataValidator.isAlpha(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.phone", "Mobile No."));
			pass = false;

		}

		else if (DataValidator.isNotMobileNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.nmob", ""));
			pass = false;
		}

		else if ((request.getParameter("mobileNo")).length() != 10) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.length", "Mobile No."));
			pass = false;

		}

		if (DataValidator.isNull(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", "Email "));
			pass = false;
		}

		else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.email", ""));
			pass = false;
		}

		/*
		 * if (DataValidator.isNull(request.getParameter("address"))) {
		 * request.setAttribute("address",
		 * PropertyReader.getValue("error.require", "Address")); pass = false; }
		 */
		log.debug("FacultyCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("FacultyCtl Method populatebean Started");

		FacultyDTO bean = new FacultyDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		bean.setPrimarySubject(DataUtility.getString(request
				.getParameter("primarySubject")));
		bean.setSecondarySubject(DataUtility.getString(request
				.getParameter("secondarySubject")));

		bean.setFirstName(DataUtility.getCapital(request
				.getParameter("firstName")));

		/*
		 * bean.setFirstName(DataUtility.getString(request
		 * .getParameter("firstName")));
		 */

		bean.setLastName(DataUtility.getCapital(request
				.getParameter("lastName")));
		/*
		 * bean.setLastName(DataUtility.getString(request.getParameter("lastName"
		 * )));
		 */

		bean.setQualification(DataUtility.getString(request
				.getParameter("qualification")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		
		populateBean(bean, request);

		log.debug("FacultyCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("FacultyCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

		if (id > 0 || op != null) {
			FacultyDTO bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setDto(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("FacultyCtl Method doGett Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("FacultyCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("ID in FacultyView:::" + id);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			FacultyDTO bean = (FacultyDTO) populateDTO(request);
			System.out.println("ID in FacultyView:::" + id);
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setDto(bean, request);
					ServletUtility.setSuccessMessage(
							"Faculty Edited Successfully", request);
				} else {
					long pk = model.add(bean);
					bean.setId(pk);
					ServletUtility.setDto(bean, request);
					ServletUtility.setSuccessMessage(
							"Faculty Added Successfully", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(bean, request);
				ServletUtility.setErrorMessage(
						"Faculty Email Id already exists", request);
			} catch (DatabaseException e) {
				ServletUtility.setDto(bean, request);
				ServletUtility
						.setErrorMessage(
								"Primary and Secondary subject cannot be same",
								request);

			}

		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyDTO bean = (FacultyDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
						response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			if (id > 0) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
				return;
			}

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("StudentCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
