package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.TimeTableDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.CourseModelInt;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.TimeTableModelInt;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Time Table functionality Controller. Performs operation for add, update and
 * get Time Table
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl.do" })
public class TimeTableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(TimeTableCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		try {
			List l = model.list();
			request.setAttribute("courseList", l);

		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("TimeTableCtl Method validate Started");
		String course = request.getParameter("courseId");
		String ExaminationDate = request.getParameter("ExaminationDate");

		boolean pass = true;

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date d;
		TimeTableDTO dto = new TimeTableDTO();
		try {
			d = format.parse(ExaminationDate);
			DateFormat format1 = new SimpleDateFormat("EEEE");
			String day = format1.format(d);
			dto.setDay(day);
			request.setAttribute("day", day);
			System.out.println(day);

		} catch (ParseException e) {
			e.printStackTrace();

		}

		if (DataValidator.isNull(course)) {
			request.setAttribute("Course",
					PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Subject"))) {
			request.setAttribute("Subject",
					PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}

		if (DataValidator.isNull(ExaminationDate)) {
			request.setAttribute("ExaminationDate", PropertyReader.getValue(
					"error.require", "Examination Date"));
			pass = false;
		} else if (!DataValidator.isPastDate(ExaminationDate)) {
			request.setAttribute("ExaminationDate",
					PropertyReader.getValue("error.past", " Examination Date"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("Time"))) {
			request.setAttribute("Time",
					PropertyReader.getValue("error.require", "Time"));
			pass = false;
		}
		/*
		 * if (DataValidator.isNull(request.getParameter("Day"))) {
		 * request.setAttribute("Day", PropertyReader.getValue("error.require",
		 * "Day")); pass = false; }
		 */

		log.debug("TimeTableCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("TimeTableCtl Method populatebean Started");
		TimeTableDTO dto = new TimeTableDTO();
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		dto.setCourse(DataUtility.getStringData(request.getParameter("Course")));
		dto.setSubject(DataUtility.getStringData(request
				.getParameter("Subject")));
		dto.setExaminationDate(DataUtility.getDate(request
				.getParameter("ExaminationDate")));
		dto.setTime(DataUtility.getString(request.getParameter("Time")));
		String day = (String) request.getAttribute("day");
		dto.setDay(day);

		/* bean.setDay(DataUtility.getString(request.getParameter("Day"))); */

		System.out.println("Population done");

		populateBean(dto, request);
		log.debug("TimeTableCtl Method populatebean Ended");
		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("TimeTableCtl Method doGet Started");

		System.out.println("In do get");

		String operation = DataUtility.getString(request
				.getParameter("operation"));

		// get model
		TimeTableModelInt model = ModelFactory.getInstance()
				.getTimeTableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			TimeTableDTO dto;
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
		log.debug("TimeTableCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("TimeTableCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		TimeTableModelInt model = ModelFactory.getInstance()
				.getTimeTableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			TimeTableDTO dto = (TimeTableDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"Time Table Edited Successfully", request);

				} else {
					long pk = model.add(dto);
					dto.setId(pk);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"Time Table Added Successfully", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility
						.setErrorMessage(
								"Exam is already been scheduled for this subject in this course",
								request);
			} catch (DatabaseException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage(
						"This date is already been occupied for this course",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimeTableDTO bean = (TimeTableDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.setSuccessMessage("Record Deleted", request);

				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.TIMETABLE_CTL, request,
						response);
				return;
			}

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("TimeTableCtl Method doPOst Ended");
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}
