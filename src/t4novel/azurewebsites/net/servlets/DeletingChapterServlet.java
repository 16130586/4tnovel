package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.CensoredChapDAO;
import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class DeletingChapterServlet
 */
@WebServlet("/delete-chap")
public class DeletingChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletingChapterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		ChapDAO chapDAO = new CensoredChapDAO(cnn);
		String isAdmin = request.getParameter("admin");
		try {
			int chapID = Integer.parseInt(request.getParameter("id-chap"));
			chapDAO.deleteChapByID(chapID);
			if (!"1".equals(isAdmin)) {
				Account account = (Account) request.getSession().getAttribute("account");
				account.deleteOwnerChap(chapID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"1".equals(isAdmin))
			response.sendRedirect("manage/account/dashboard-chaps");
		else
			response.sendRedirect("manage/admin/dashboard-chaps");
	}

}
