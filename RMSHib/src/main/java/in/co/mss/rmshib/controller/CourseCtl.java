package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.CourseDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.CourseModelInt;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Course functionality Controller. Performs operation for add, update and get
 * Course
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl.do" })
public class CourseCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CourseCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseCtl Method validate Started");

		boolean pass = true;
		String duration = request.getParameter("duration");
		/*
		 * if
		 * (DataValidator.isNull(request.getParameter("name"))||DataValidator.
		 * isNotAlpha(request.getParameter("name"))) {
		 * request.setAttribute("name", PropertyReader.getValue("error.require",
		 * "Name")); pass = false; } if
		 * (DataValidator.isNull(request.getParameter
		 * ("description"))||DataValidator
		 * .isNotAlpha(request.getParameter("description"))) {
		 * request.setAttribute("description",
		 * PropertyReader.getValue("error.require", "Description")); pass =
		 * false; }
		 */
		if (DataValidator.isNull(request.getParameter("name"))) {

			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.alpha", "Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {

			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		} else if (DataValidator
				.isNotAlpha(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.alpha", "Description"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration",
					PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("duration"))) {
			request.setAttribute("duration",
					PropertyReader.getValue("error.duration", ""));
			pass = false;

		} else if (DataValidator.isInvalidDuration(request
				.getParameter("duration"))) {
			request.setAttribute("duration",
					PropertyReader.getValue("error.induration", ""));
			pass = false;
		}
		log.debug("CourseCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("CourseCtl Method populatebean Started");

		CourseDTO dto = new CourseDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		dto.setDescription(DataUtility.getCapital(request
				.getParameter("description")));
		dto.setDuration(DataUtility.getInt(request.getParameter("duration")));
		
		populateBean(dto, request);

		log.debug("CourseCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");

		System.out.println("In do get");

		String operation = DataUtility.getString(request
				.getParameter("operation"));

		// get model
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id > 0) {
			CourseDTO dto;
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
		log.debug("CourseCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			CourseDTO dto = (CourseDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"Course Edited Successfully", request);

				} else {
					long pk = model.add(dto);
					dto.setId(pk);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"Course Added Successfully", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility
						.setErrorMessage("Course Already Exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			CourseDTO bean = (CourseDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.setSuccessMessage("Record Deleted", request);

				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return;
			}

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("CourseCtl Method doPOst Ended");
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
