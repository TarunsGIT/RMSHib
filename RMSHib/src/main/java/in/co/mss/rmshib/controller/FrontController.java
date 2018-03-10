package in.co.mss.rmshib.controller;

import in.co.mss.rmshib.util.ServletUtility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */

@WebFilter(filterName = "FrontCtl", urlPatterns = { "*.do" })
public class FrontController implements Filter {

	public void init(FilterConfig conf) throws ServletException {

	}
	
   public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(true);
		if (session.getAttribute("user") == null) {

			ServletUtility
					.setErrorMessage(
							" Your session has been expired. Please re-Login.",
							request);
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		} else {
			chain.doFilter(req, resp);
		}
	}

	public void destroy() {

	}

}
