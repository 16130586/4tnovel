package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;

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

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String url = "/jsps/pages/account-manage.jsp";
		if ("password".equals(type))
			url = "/jsps/pages/account-manage-password.jsp";
		if ("display-name".equals(type))
			url = "/jsps/pages/account-manage-display-name.jsp";
		if ("mail".equals(type))
			url = "/jsps/pages/account-manage-mail.jsp";
		Connection cnn = (Connection) request.getAttribute("connection");
		Account account = (Account) request.getSession().getAttribute("account");
		if (account.getJoinGroup() == null) {
			GroupDAO groupDAO = new GroupDAO(cnn);
			List<Group> joinGroups = null;
			// get data from database
			try {
				joinGroups = groupDAO.getJoinGroups(account.getId());
				for (Group gr : joinGroups) {
					gr.setAccounts(groupDAO.getAllMemberFromGroup(gr.getId()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			account.setJoinGroup(joinGroups);
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
