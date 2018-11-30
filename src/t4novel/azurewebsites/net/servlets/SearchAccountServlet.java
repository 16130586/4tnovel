package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class SearchPeopleServlet
 */
@WebServlet("/search-account")
public class SearchAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchAccountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/add-member").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
		}
		AccountDAO accountDAO = new AccountDAO((Connection) request.getAttribute("connection"));
		Account account = null;
		String type = request.getParameter("type");
		String searchParam = request.getParameter("input");
		switch (type) {
		case "user-name":
			account = accountDAO.getAccountByUsername(searchParam);
			break;
		case "display-name":
			account = accountDAO.getAccountByNickname(searchParam);
			break;
		case "email":
			account = accountDAO.getAccountByEmail(searchParam);
			break;
		default:
			break;
		}
		request.setAttribute("searchResultAccount", account);
		request.setAttribute("forwardToDoGet", true);
		getServletContext().getRequestDispatcher("/add-member").forward(request, response);
	}

}