package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String url = "";
		if ("delete".equals(type)) url = "/jsps/pages/admin-delete-account.jsp";
		if ("ban".equals(type)) url = "/jsps/pages/admin-ban-account.jsp";
		if ("grant-right".equals(type)) url = "/jsps/pages/admin-grant-the-right-to-pin.jsp";
		if ("censor".equals(type)) url = "/jsps/pages/admin-censor.jsp";
		if ("report".equals(type)) url = "/jsps/pages/admin-report-mail.jsp";
		if ("notify".equals(type)) url = "/jsps/pages/admin-notify.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
