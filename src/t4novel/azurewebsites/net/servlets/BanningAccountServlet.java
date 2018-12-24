package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;

/**
 * Servlet implementation class BanningAccountServlet
 */
@WebServlet("/ban-account")
public class BanningAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BanningAccountServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/jsps/pages/admin-ban-account.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accountDAO = new AccountDAO(cnn);
		String action = request.getParameter("action");
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		try {
			if (action.equals("lock")) {
				accountDAO.banAccountByID(accountID);
			} else {
				accountDAO.unBanAccountByID(accountID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/jsps/pages/admin-ban-account.jsp").forward(request, response);
	}

}
