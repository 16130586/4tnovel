package t4novel.azurewebsites.net.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.SercureURLEngine;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class GlobalFilter
 */
@WebFilter(urlPatterns = "/*", dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
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
		boolean isNeedLoginUrl = SercureURLEngine.isNeedLoginUrl(servletRequest.getServletPath(),
				servletRequest.getMethod());
		boolean isNeedDbConnection = SercureURLEngine.isNeedDbConnection(servletRequest.getServletPath(),
				servletRequest.getMethod());
		Account account = (Account) servletRequest.getAttribute("account");
		if (isNeedLoginUrl && account == null) {
			System.out.println("redirec on bad request");
			servletResponse.sendRedirect("login");
			return;
		}
		boolean isAllowedToAccess = false;
		if (account != null) {
			isAllowedToAccess = SercureURLEngine.isOnAllowedUrl(account.getRole(), servletRequest.getServletPath());
		}
		if (isNeedLoginUrl && account != null && !isAllowedToAccess) {
			System.out.println("sedding on bad request! " + servletRequest.getServletPath());
			servletResponse.sendError(404);
			return;
		}

		DataSource ds = null;
		Connection cnn = null;

		if (isNeedDbConnection) {
			try {
				ds = (DataSource) request.getServletContext().getAttribute("datasource");
				cnn = ds.getConnection();
				request.setAttribute("connection", cnn);
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
			if (cnn != null) {
				try {
					System.out.println(
							"closing connection at : " + System.currentTimeMillis() + " " + cnn.hashCode());
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
