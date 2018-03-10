package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.dto.BaseDTO;
import in.co.mss.rmshib.dto.RoleDTO;
import in.co.mss.rmshib.dto.StudentDTO;
import in.co.mss.rmshib.dto.UserDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.model.ModelFactory;
import in.co.mss.rmshib.model.RoleModelInt;
import in.co.mss.rmshib.model.StudentModelInt;
import in.co.mss.rmshib.model.UserModelInt;
import in.co.mss.rmshib.util.DataUtility;
import in.co.mss.rmshib.util.PropertyReader;
import in.co.mss.rmshib.util.ServletUtility;
import in.co.mss.rmshib.util.DataValidator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * User List functionality Controller. Performs operation for list, search and
 * delete operations of User
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl.do" })
public class UserListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		try {
			List l = model.list();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);

		}

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		UserDTO dto = new UserDTO();

		dto.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));

		dto.setLogin(DataUtility.getString(request.getParameter("login")));
		dto.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		dto.setRoleName(DataUtility.getString(request.getParameter("roleName")));
		return dto;
	}

	/**
	 * Contains Display logics
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserListCtl doGet Start");
		List list = null;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		UserDTO dto = (UserDTO) populateDTO(request);
		System.out.println(request.getParameter("searchName")
				+ " search parameter");
		String op = DataUtility.getString(request.getParameter("operation"));

		UserModelInt model = ModelFactory.getInstance().getUserModel();
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		/*try {
			int listSize = model.list().size();
			request.setAttribute("listSize", listSize);
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}*/

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
		log.debug("UserListCtl doGet End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserListCtl doPost Start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		UserDTO dto = (UserDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		/*try {
			int listSize = model.search(dto).size();
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
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					UserDTO deletedto = new UserDTO();
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
		log.debug("UserListCtl doPost End");
	}

	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}

}
