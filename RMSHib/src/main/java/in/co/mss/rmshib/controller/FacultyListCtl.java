package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.FacultyDTO;
import in.co.mss.rmshib.dto.StudentDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.model.CourseModelInt;
import in.co.mss.rmshib.model.FacultyModelInt;
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
 * Faculty List functionality Controller. Performs operation for list, search
 * and delete operations of Faculty
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "FacultyListCtl", urlPatterns = { "/ctl/FacultyListCtl.do" })
public class FacultyListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(FacultyListCtl.class);

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
	protected BaseDTO populateDTO(HttpServletRequest request) {

		FacultyDTO dto = new FacultyDTO();

		dto.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));
		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		dto.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		dto.setCourseName(DataUtility.getString(request
				.getParameter("courseName")));
		dto.setEmail(DataUtility.getString(request.getParameter("email")));

		System.out.println("in search list "
				+ request.getParameter("firstName"));
		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("FacultyListCtl doGet Start");
		List list = null;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		FacultyDTO dto = (FacultyDTO) populateDTO(request);
		System.out.println(request.getParameter("searchName")
				+ " search parameter");
		String op = DataUtility.getString(request.getParameter("operation"));

		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
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
		log.debug("FacultyListCtl doGet End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("FacultyListCtl doPost Start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		FacultyDTO dto = (FacultyDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
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
				ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					FacultyDTO deletedto = new FacultyDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setSuccessMessage(
								"Record Deleted Successfully", request);
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
				System.out.println(dto.getFirstName() + "first name"
						+ request.getParameter("firstName"));
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
		log.debug("FacultyListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_LIST_VIEW;
	}

}
