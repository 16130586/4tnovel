package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/refact")
public class RefactingNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefactingNovelServlet() {
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
		System.out.println("cnn = ? " + (cnn == null));
		String type = request.getParameter("action");
		String url = "";
		switch (type) {
		case "fix-novel":
			url = "";
			break;
		case "fix-vol":
			url = "";
		case "fix-chap":
			url = "";
		case "del-novel":
			url = "/delete-novel";
			break;
		case "del-vol":
			url = "/delete-vol";
			break;
		case "del-chap":
			url = "/delete-chap";
			break;
		default:
			break;
		}
		System.out.println("action" + url);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
