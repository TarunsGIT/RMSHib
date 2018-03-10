package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.RoleDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.RoleModelInt;
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
 * Role functionality Controller. Performs operation for add, update and get
 * Role
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "RoleCtl", urlPatterns = { "/ctl/RoleCtl.do" })
public class RoleCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(RoleCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RoleCtl Method validate Started");

		boolean pass = true;
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
		log.debug("RoleCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("RoleCtl Method populatebean Started");

		RoleDTO dto = new RoleDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setName(DataUtility.getCapital(request.getParameter("name")));
		/* dto.setName(DataUtility.getString(request.getParameter("name"))); */
		dto.setDescription(DataUtility.getCapital(request
				.getParameter("description")));

		populateBean(dto, request);
		/*
		 * dto.setDescription(DataUtility.getString(request
		 * .getParameter("description")));
		 */

		// populateDTO(bean, request);
		// populateBean(dto, request);
		log.debug("RoleCtl Method populatebean Ended");

		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String operation = DataUtility.getString(request
				.getParameter("operation"));

		// get model
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			RoleDTO dto;
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
		log.debug("RoleCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			RoleDTO dto = (RoleDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage(
							"Role Edited Successfully", request);
					ServletUtility.setDto(dto, request);
				} else {
					long pk = model.add(dto);
					dto.setId(pk);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Role Added Successfully",
							request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Role Already Exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			RoleDTO bean = (RoleDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.setSuccessMessage("Record Deleted", request);

				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
						response);
				return;
			} else {
				ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
				return;
			}

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("RoleCtl Method doPOst Ended");
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

}
