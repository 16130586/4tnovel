package t4novel.azurewebsites.net.servlets.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.LikeDAO;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class LikingAjaxServlet
 */
@WebServlet("/ajax-like-novel")
public class LikingAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikingAjaxServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer novelId = Integer.parseInt(request.getParameter("id"));
			String action = request.getParameter("action");
			if (action == null) {
				response.sendError(400);
				return;
			}
			Connection cnn = (Connection) request.getAttribute("connection");
			Account ac = (Account) request.getSession().getAttribute("account");
			LikeDAO likeDao = new LikeDAO(cnn);
			int newestLike = -1;
			try {
				if ("like".equals(action)) {
					newestLike = likeDao.like(ac, novelId, "novel");
				}
				if ("unlike".equals(action)) {
					newestLike = likeDao.unlike(ac, novelId, "novel");
				}
				if (newestLike != -1) {
					response.setStatus(200);
					PrintWriter netOut = new PrintWriter(response.getOutputStream(), true);
					netOut.println(newestLike + "");
					netOut.flush();
					netOut.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				response.sendError(500);
				return;
			}

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			response.sendError(400);
			return;
		}
	}

}
