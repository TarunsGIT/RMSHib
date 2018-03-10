package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.CourseDTO;
import in.co.mss.rmshib.dto.StudentDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.model.CourseModelInt;
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
 * Course List functionality Controller. Performs operation for list, search and
 * delete operations of Course
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "CourseListCtl", urlPatterns = { "/ctl/CourseListCtl.do" })
public class CourseListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CourseListCtl.class);

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		CourseDTO dto = new CourseDTO();

		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setDuration(DataUtility.getInt(request.getParameter("duration")));

		System.out.println("in search list "
				+ request.getParameter("firstName"));
		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseListCtl doGet Start");
		List list = null;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CourseDTO dto = (CourseDTO) populateDTO(request);
		System.out.println(request.getParameter("searchName")
				+ " search parameter");
		String op = DataUtility.getString(request.getParameter("operation"));

		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		/*try {
			int listSize = model.list().size();
			request.setAttribute("listSize", listSize);
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}*/

		try {
			list = model.search(dto, pageNo, pageSize);
			int listSize=model.search(dto).size();
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
		log.debug("CourseListCtl doGet End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseListCtl doPost Start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		CourseDTO dto = (CourseDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
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
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					CourseDTO deletedto = new CourseDTO();
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
				int listSize = model.search(dto).size();// CHANGE
				request.setAttribute("listSize", listSize);
                ServletUtility.setDto(dto, request);
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
		log.debug("CourseListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_LIST_VIEW;
	}

}
