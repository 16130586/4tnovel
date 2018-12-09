package t4novel.azurewebsites.net.servlets.ajax;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import t4novel.azurewebsites.net.DAO.InboxDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Message;

/**
 */

@WebServlet("/ajax-notification")
public class NotificationAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NotificationAjaxServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("getting ajax");
		try {

			int requestingPage = Integer.parseInt(request.getParameter("page-number"));
			Account hostAcc = (Account) request.getSession().getAttribute("account");
			Connection cnn = (Connection) request.getAttribute("connection");
			InboxDAO inboxDao = new InboxDAO(cnn);
			List<Message> msges = inboxDao.getMessagesInInBox(requestingPage, 5, hostAcc.getId());
			Gson gson = new Gson();
			Type type = new TypeToken<List<Message>>() {
			}.getType();
			String json = gson.toJson(msges, type);
			response.setStatus(200);
			PrintWriter netOut = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"), true);
			netOut.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
		return;
	}
}
