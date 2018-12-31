package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.models.Account;

@WebServlet("/manage/admin/dashboard-accounts")
public class AdminDashBoardAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDashBoardAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accDao = new AccountDAO(cnn);
		List<Account> accounts = null;
		try {
			accounts = accDao.getBasicInformationAccounts();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("accounts", accounts);
		getServletContext().getRequestDispatcher("/jsps/pages/admin-new.all-account.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		for (java.util.Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + "  " + Arrays.toString(entry.getValue()));
		}
		if ("delete".equals(action)) {
			System.out.println("Need to delete : " + request.getParameter("id"));
		}
		if ("create".equals(action)) {
			System.out.println("Need to create :");
		}
		if ("edit".equals(action)) {
			System.out.println("Need to edit : " + request.getParameter("id"));
		}
		doGet(request, response);
	}

}
