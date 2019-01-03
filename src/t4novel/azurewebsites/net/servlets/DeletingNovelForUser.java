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
import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class DeletingNovelForUser
 */
@WebServlet("/deletingNovelForUser")
public class DeletingNovelForUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletingNovelForUser() {
		super();
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
		NovelDAO novelDAO = new NovelDAO(cnn);
		VolDAO volDAO = new VolDAO(cnn);
		ChapDAO chapDAO = new CensoredChapDAO(cnn);
		GenreDAO genreDAO = new GenreDAO(cnn);

		try {
			int novelID = Integer.parseInt(request.getParameter("id-novel"));
			novelDAO.delNovelById(novelID, volDAO, chapDAO, genreDAO);
			Account account = (Account) request.getSession().getAttribute("account");
			account.deleteNovel(novelID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("manage/account/dashboard-novels");
	}

}
