package t4novel.azurewebsites.net.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import t4novel.azurewebsites.net.DAO.ViewDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;
import t4novel.azurewebsites.net.sercurities.SercureURLEngine;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class GlobalFilter
 */
public class GlobalFilter implements Filter {
	static int requested = 0;

	/**
	 * Default constructor.
	 */
	public GlobalFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// khi vao 1 url -> co quyen truy cap khong da ?
		// quyen truy cap lay tu dau ? tu account

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		String path = servletRequest.getServletPath();
		boolean isNeedLoginUrl = SercureURLEngine.isNeedLoginUrl(path, servletRequest.getMethod());
		boolean isNeedDbConnection = SercureURLEngine.isNeedDbConnection(path, servletRequest.getMethod());
		Account account = (Account) servletRequest.getSession().getAttribute("account");
		if (isNeedLoginUrl && account == null) {
			servletResponse.sendRedirect(request.getServletContext().getContextPath() + "/login");
			return;
		}
		// di dc toi day gom 2 th, 1 la url k can login va account == null, 2 la k can
		// login va account != null
		boolean isAllowedToAccess = false;
		// default for GUESS
		if (account == null) {
			isAllowedToAccess = SercureURLEngine.isOnAllowedUrl(Role.GUESS, path);
		}
		// other type of user
		else {
			isAllowedToAccess = SercureURLEngine.isOnAllowedUrl(account.getRole(), path);
		}
		if (isNeedLoginUrl && !isAllowedToAccess) {
			System.out.println("sedding on bad request! " + path);
			servletResponse.sendError(404);
			return;
		}

		DataSource ds = null;
		Connection cnn = (Connection) servletRequest.getAttribute("connection");
		if (isNeedDbConnection && cnn == null) {
			try {
				ds = (DataSource) request.getServletContext().getAttribute("datasource");
				cnn = ds.getConnection();
				servletRequest.setAttribute("connection", cnn);
			} catch (NoSuchElementException e) {
				System.out.println(servletRequest.getRequestURI() + "waiting too long!");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Some error then send code 500 " + e.getMessage());
				e.printStackTrace();
				((HttpServletResponse) response).sendError(408);
				return;
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);

		// increasing the view
		if (path != null && !path.startsWith("/resources"))
			if (cnn == null) {
				ds = (DataSource) request.getServletContext().getAttribute("datasource");
				try {
					cnn = ds.getConnection();
					System.out.println("try to get connection ? at url : " + path);
					ViewDAO viewDao = new ViewDAO(cnn);
					int accid = account == null ? -1 : account.getId();

					viewDao.inserNewView(accid, "global", -1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		// end increasing the view
		if (cnn != null && isNeedDbConnection) {
			try {
				cnn.close();
				cnn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
