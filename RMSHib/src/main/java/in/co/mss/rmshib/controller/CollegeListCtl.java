package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.CollegeDTO;
import in.co.mss.rmshib.dto.StudentDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.model.CollegeModelInt;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.StudentModelInt;
import in.co.mss.rmshib.util.DataUtility;
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
 * College List functionality Controller. Performs operation for list, search
 * and delete operations of College
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "CollegeListCtl", urlPatterns = { "/ctl/CollegeListCtl.do" })
public class CollegeListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CollegeListCtl.class);

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		CollegeDTO dto = new CollegeDTO();

		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setCity(DataUtility.getString(request.getParameter("city")));

		return dto;
	}

	/**
	 * Contains display logic
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("CollegeListCtl doGet Start");
		List list = null;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CollegeDTO dto = (CollegeDTO) populateDTO(request);
		System.out.println(request.getParameter("searchName")
				+ " search parameter");
		String op = DataUtility.getString(request.getParameter("operation"));

		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		/*
		 * try { int listSize = model.list().size();
		 * request.setAttribute("listSize", listSize); } catch
		 * (ApplicationException e1) { e1.printStackTrace(); }
		 */
		try {
			list = model.search(dto, pageNo, pageSize);
			int listSize = model.search(dto).size();
			request.setAttribute("listSize", listSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			System.out.println(dto);
			ServletUtility.setDto(dto, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("StudentListCtl doGet End");

	}

	/**
	 * Contains submit logic
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("CollegeListCtl doPost Start");

		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		CollegeDTO dto = (CollegeDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		/*try {
			int listSize = model.list().size();
			request.setAttribute("listSize", listSize);
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}*/
		String[] ids = request.getParameterValues("ids");
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					CollegeDTO deletedto = new CollegeDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setSuccessMessage("Record Deleted",
								request);
					}
				} else {
					ServletUtility.setErrorMessage(
							"Select at least one record", request);
				}
			}

			list = model.search(dto, pageNo, pageSize);
			try {
				int listSize = model.search(dto).size();
				request.setAttribute("listSize", listSize);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("CollegeListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_LIST_VIEW;
	}

}
