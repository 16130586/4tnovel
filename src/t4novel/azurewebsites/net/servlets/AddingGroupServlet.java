package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;
import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.DAOService.ExisteddNameCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingGroupForm;

/**
 * Servlet implementation class AddingGroupServlet
 */
@WebServlet("/add-group")
public class AddingGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingGroupServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/jsps/pages/add-group.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		Connection databaseConnection = (Connection) request.getAttribute("connection");
		DAOService existedGroupNameChecker = new ExisteddNameCheckingService(databaseConnection);
		AbstractMappingForm submitedForm = new AddingGroupForm(request, existedGroupNameChecker);
		if (!submitedForm.isOnError()) {
			// open transaction
			try {
				// dtb
				databaseConnection.setAutoCommit(false);
				Account account = (Account) request.getSession().getAttribute("account");
				Group group = (Group) submitedForm.getMappingData();
				GroupDAO groupDAO = new GroupDAO(databaseConnection);

				try {
					groupDAO.insertGroup(group);
					group.setId(groupDAO.getNextID() - 1);
					groupDAO.insertMemberToGroup(account, group);
				} catch (Exception e) {
					e.printStackTrace();
				}
				account.addJoinGroup(group);
				// ram
				request.setAttribute("account", account);

				databaseConnection.commit();
				databaseConnection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					databaseConnection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// try catch => xay ra loi trong chuoi hanh dong => roll back lai het
			request.setAttribute("sucessed", "Thêm nhóm thành công!");
		} else {
			System.out.println("on error");
			submitedForm.applyErrorsToUI(request);
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-group.jsp").forward(request, response);
	}
}
