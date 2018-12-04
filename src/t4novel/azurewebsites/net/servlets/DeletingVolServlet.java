package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ChapDAO chapDAO = new ChapDAO(cnn);
		VolDAO volDAO = new VolDAO(cnn);
		Account account = (Account) request.getSession().getAttribute("account");
		try {
			int volID = Integer.parseInt(request.getParameter("id-vol"));
			System.out.println("vol ID: " + volID);
			account.deleteVol(volID);
			volDAO.deleteVolByID(volID, chapDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("myNovel");
	}
}
