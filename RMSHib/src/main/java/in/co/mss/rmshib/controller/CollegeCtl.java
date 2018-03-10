package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.CollegeDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.CollegeModelInt;
import in.co.mss.rmshib.model.ModelFactory;
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

import org.apache.log4j.Logger;

/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "CollegeCtl", urlPatterns = { "/ctl/CollegeCtl.do" })
public class CollegeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CollegeCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CollegeCtl Method validate Started");

		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("name"))) {

			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.alpha", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {

			request.setAttribute("state",
					PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		/*
		 * else if(DataValidator.isNotAlpha(request.getParameter("state"))){
		 * request.setAttribute("state", PropertyReader.getValue("error.alpha",
		 * "State")); pass = false;
		 * 
		 * }
		 */
		/*
		 * else if (DataValidator.isInvalidState(request.getParameter("state"))
		 * ){ request.setAttribute("state",
		 * PropertyReader.getValue("error.alpha", "State")); pass = false; }
		 */

		if (DataValidator.isNull(request.getParameter("city"))) {

			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.alpha", "City"));
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
		log.debug("CollegeCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("CollegeCtl Method populatebean Started");

		CollegeDTO dto = new CollegeDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		dto.setAddress(DataUtility.getCapital(request.getParameter("address")));

		/*
		 * dto.setAddress(DataUtility.getString(request.getParameter("address")))
		 * ;
		 */

		dto.setState(DataUtility.getCapital(request.getParameter("state")));

		/* dto.setState(DataUtility.getString(request.getParameter("state"))); */

		dto.setCity(DataUtility.getCapital(request.getParameter("city")));

		/* dto.setCity(DataUtility.getString(request.getParameter("city"))); */

		dto.setPhoneNo(DataUtility.getString(request.getParameter("mobileNo")));

		populateBean(dto, request);
		// populateBean(dto, request);

		// populateDTO(bean, request);

		log.debug("CollegeCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * Contains display logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			CollegeDTO dto;
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
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("CollegeCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			CollegeDTO dto = (CollegeDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"College Edited Successfully", request);

				} else {
					long pk = model.add(dto);
					dto.setId(pk);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage(
							"College Added Sucessfully", request);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("College name already exists",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			CollegeDTO dto = (CollegeDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.setSuccessMessage(
						"Data is successfully deleted",

						request);

				ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,
						response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
				return;
			}

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("CollegeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {

		return ORSView.COLLEGE_VIEW;
	}

}
