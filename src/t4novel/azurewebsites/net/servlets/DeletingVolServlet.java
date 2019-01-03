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
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class DeletingVolServlet
 */
@WebServlet("/delete-vol")
public class DeletingVolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletingVolServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		ChapDAO chapDAO = new CensoredChapDAO(cnn);
		VolDAO volDAO = new VolDAO(cnn);
		String isAdmin = request.getParameter("admin");
		try {
			int volID = Integer.parseInt(request.getParameter("id-vol"));
			volDAO.deleteVolByID(volID, chapDAO);
			if (!"1".equals(isAdmin)) {
				Account account = (Account) request.getSession().getAttribute("account");
				account.deleteVol(volID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"1".equals(isAdmin))
			response.sendRedirect("manage/account/dashboard-vols");
		else
			response.sendRedirect("manage/admin/dashboard-vols");
	}
}
