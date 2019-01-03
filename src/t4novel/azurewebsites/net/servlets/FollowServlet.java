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

import t4novel.azurewebsites.net.DAO.FollowDAO;
import t4novel.azurewebsites.net.models.Account;

//stand for follow and unfollow with url : follow?action=x?targetId=y?hostId=z?stream=[novel|thread]
@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FollowServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			response.sendError(404);
			return;
		}
		String stream = request.getParameter("stream");
		int targetId = -1;
		try {
			targetId = Integer.parseInt(request.getParameter("targetId"));
		}catch (Exception e) {
			e.printStackTrace();
			response.sendError(404);
			return;
		}
		Account hostAcc = (Account) request.getSession().getAttribute("account");
		Connection cnn = (Connection) request.getAttribute("connection");
		FollowDAO followDao = new FollowDAO(cnn);
		try {
		if ("subcribe".equals(action) && "novel".equals(stream)) {
			followDao.subcribe(stream , targetId , hostAcc.getId());
			hostAcc.followNovel(targetId);
		}
		if ("subcribe".equals(action) && "thread".equals(stream)) {
			followDao.subcribe(stream , targetId , hostAcc.getId());
			hostAcc.unFollowThread(targetId);
		}
		if ("unSubcribe".equals(action) && "novel".equals(stream)) {
			followDao.unSubcribe(stream , targetId , hostAcc.getId());
			hostAcc.unFollowNovel(targetId);
		}
		if ("unSubcribe".equals(action) && "thread".equals(stream)) {
			followDao.unSubcribe(stream , targetId , hostAcc.getId());
			hostAcc.unFollowThread(targetId);
		}
		}catch (Exception e) {
			response.sendError(500);
			e.printStackTrace();
			return;
		}
		String url = "";
		if("novel".equals(stream)) {
			url = "detail?id=" + targetId;
		}
		if("unSubcribe".equals(stream)) {
			url="thread?id=" + targetId;
		}
		response.sendRedirect(url);
	}

}
