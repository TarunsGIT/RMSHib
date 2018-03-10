package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.MarksheetDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.MarksheetModelInt;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.StudentModelInt;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.DataValidator;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Marksheet functionality Controller. Performs operation for add, update and get
 * Marksheet
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/MarksheetCtl.do" })
public class MarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		try {
			List list = model.list();
			request.setAttribute("studentList", list);
		} catch (ApplicationException e) {

			e.printStackTrace();
		}

	}

	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl Method validate Started");

		boolean pass = true;
		String name = request.getParameter("studentId");

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo",
					PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		} else if (!DataValidator
				.isAlphaNumeric(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo",
					PropertyReader.getValue("error.alphanum", "Roll Number"));
			pass = false;
		}
		/*
		 * else if (DataValidator.isSpecial(request.getParameter("rollNo"))) {
		 * request.setAttribute("rollNo", PropertyReader.getValue("error.spc",
		 * "Roll Number")); pass = false; }
		 */
		if (DataValidator.isNull(name)) {
			request.setAttribute("Name",
					PropertyReader.getValue("error.require", "Name"));

		}
		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics",
					PropertyReader.getValue("error.require", "Physics"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("physics"))) {
			request.setAttribute("physics",
					PropertyReader.getValue("error.int", ""));
			pass = false;

		} else if (DataUtility.getInt(request.getParameter("physics")) > 100) {
			request.setAttribute("physics", "Marks Should be Out Of 100");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry",
					PropertyReader.getValue("error.require", "Chemistry"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry",
					PropertyReader.getValue("error.int", ""));
			pass = false;

		} else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", "Marks Should be Out Of 100");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths",
					PropertyReader.getValue("error.require", "Maths"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("maths"))) {
			request.setAttribute("maths",
					PropertyReader.getValue("error.int", ""));
			pass = false;

		} else if (DataUtility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", "Marks Should be Out Of 100");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId",
					PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}

		log.debug("MarksheetCtl Method validate Ended");

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MarksheetCtl Method populatebean Started");

		MarksheetDTO dto = new MarksheetDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));

		dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

		dto.setMaths(DataUtility.getInt(request.getParameter("maths")));

		dto.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		
		populateBean(dto, request);
		// populateBean(dto, request);
		log.debug("MarksheetCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CollegeCtl Method doGet Started");

		log.debug("MarksheetCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		MarksheetModelInt model = ModelFactory.getInstance()
				.getMarksheetModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			MarksheetDTO dto;
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
		log.debug("MarksheetCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("MarksheetCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		MarksheetModelInt model = ModelFactory.getInstance()
				.getMarksheetModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			MarksheetDTO bean = (MarksheetDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setSuccessMessage(
							"Marksheet Edited Sucessfully", request);
					ServletUtility.setDto(bean, request);
				} else {
					long pk = model.add(bean);
					bean.setId(pk);
					ServletUtility.setDto(bean, request);
					ServletUtility.setSuccessMessage(
							"Marksheet Added Sucessfully", request);

				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(bean, request);
				ServletUtility.setErrorMessage("Roll no already exists",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
			System.out.println("in try");
			try {
				model.delete(dto);
				ServletUtility.setSuccessMessage(
						"Data is successfully deleted", request);

				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
						response);
				System.out.println("in try");
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.MARKSHEET_CTL, request,
						response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
