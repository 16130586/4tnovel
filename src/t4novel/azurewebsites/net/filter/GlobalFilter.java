package t4novel.azurewebsites.net.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@WebFilter("/*")
public class GlobalFilter implements Filter {

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
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		System.out.println("path info : " + servletRequest.getRequestURI());
		// TODO xac dinh xem uri co can toi connection k, neu can thi cho
		DataSource ds = (DataSource) request.getServletContext().getAttribute("datasource");
		Connection cnn = null;
		try {
			System.out.println("get connection at: " + System.currentTimeMillis());
			System.out.println("accessing and get dts then get connect tion " + ds.getConnection());
			cnn = ds.getConnection();
			request.setAttribute("connection", cnn);
			throw new SQLException();
		} catch (SQLTimeoutException  e) {
			// TODO send Error take time too long bescause out of waiting time (10s)
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO send error with 500
			((HttpServletResponse) response).sendError(500);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
		if (cnn != null) {
			try {
				
				System.out.println("closing connection at : " + System.currentTimeMillis() + " " + cnn.getClass().hashCode());
				cnn.close();
				cnn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Init global filter");
	}

}
