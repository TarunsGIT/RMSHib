package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Error Controller of the project
 * 
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

@WebServlet(name = "ErrorCtl", urlPatterns = { "/ctl/ErrorCtl" })
public class ErrorCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ErrorCtl.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.redirect(ORSView.ERROR_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}
