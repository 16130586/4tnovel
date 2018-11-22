package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageAccountServlet
 */
@WebServlet("/manage")
public class ManageAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String url = "/jsps/pages/account-manage.jsp";
		if("password".equals(type)) url = "/jsps/pages/account-manage-password.jsp";
		if("display-name".equals(type)) url = "/jsps/pages/account-manage-display-name.jsp";
		if("mail".equals(type)) url = "/jsps/pages/account-manage-mail.jsp";
		if("my-novel".equals(type)) url = "/jsps/pages/account-manage-my-novel.jsp";
		if("admin".equals(type)) url = "/jsps/pages/account-manage-admin.jsp";
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
