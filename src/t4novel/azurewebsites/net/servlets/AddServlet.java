package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String url = "";
		if ("add-novel".equals(type)) {
			url = "/add-novel";
		} else if ("add-vol".equals(type)) {
			url = "/add-vol";
		} else if ("add-chapter".equals(type)) {
			url = "/add-chapter";
		} else if ("add-group".equals(type)) {
			url = "/add-group";
		} else if ("add-member".equals(type)) {
			url = "/add-member";
		} else {
			response.sendError(404);
			return;
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
		}
		String type = request.getParameter("type");
		String url = "";
		if ("add-novel".equals(type)) {
			url = "/add-novel";
		}
		else if ("add-vol".equals(type)) {
			url = "/add-vol";
		}
		else	if ("add-chapter".equals(type)) {
			url = "/add-chapter";
		}
		else if ("add-group".equals(type)) {
			url = "/add-group";
		}
		else	if ("add-member".equals(type)) {
			url = "/add-member";
		}
		else {
			response.sendError(404);
			return;
		}
		System.out.println("forward!!! on add servlet");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
