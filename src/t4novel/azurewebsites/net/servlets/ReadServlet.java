package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.models.Chap;

/**
 * Servlet implementation class ReadServlet
 */
@WebServlet("/read")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int chapId = Integer.parseInt(request.getParameter("id"));
			Connection cnn = (Connection) request.getAttribute("connection");
			ChapDAO chapDao = new ChapDAO(cnn);
			Chap acquireChap = chapDao.getChapByID(chapId);
			request.setAttribute("chap", acquireChap);
		} catch (NumberFormatException e) {
			response.sendError(404);
			return;
		}catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		getServletContext().getRequestDispatcher("/jsps/pages/chap.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}

}
