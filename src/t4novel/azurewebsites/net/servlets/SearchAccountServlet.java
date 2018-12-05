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
		AccountDAO accountDAO = new AccountDAO((Connection) request.getAttribute("connection"));
		Account account = null;
		String type = request.getParameter("type");
		String searchParam = request.getParameter("input");

		switch (type) {
		case "user-name":
			// get data from database
			try {
				account = accountDAO.getAccountByUsername(searchParam);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "display-name":
			// get data from database
			try {
				account = accountDAO.getAccountByNickname(searchParam);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "email":
			// get data from database
			try {
				account = accountDAO.getAccountByEmail(searchParam);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		if (account == null) {
			request.setAttribute("searchResultError", "Không tìm thấy tài khoản!");
		}
		request.setAttribute("searchResultAccount", account);
		request.setAttribute("forwardToDoGet", true);
		getServletContext().getRequestDispatcher("/add-member").forward(request, response);
	}
}
