package t4novel.azurewebsites.net.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;

public class ReadFilter implements Filter {

    public ReadFilter() {
    }

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		if(servletResponse.getStatus() == 200) {
			Connection cnn = (Connection) request.getAttribute("connection");
			Chap chap = (Chap) request.getAttribute("chap");
			Novel novel = chap.getNovelOwner();
			NovelDAO novelDao = new NovelDAO(cnn);
			try {
				novelDao.increaseView(novel);
			} catch (SQLException e) {
				System.out.println(e.getMessage() + " at ReadFilter");
				System.out.println("canot increase view of " + novel.getId() + " " + novel.getName());
			}
		}
	}



	public void init(FilterConfig fConfig) throws ServletException {
	}

}
