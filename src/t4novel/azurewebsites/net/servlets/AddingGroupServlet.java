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
		
		System.out.println("post adding group");
		Connection databaseConnection = (Connection) request.getAttribute("connection");
		DAOService existedGroupNameChecker = new ExisteddNameCheckingService(databaseConnection);
		AbstractMappingForm submitedForm = new AddingGroupForm(request, existedGroupNameChecker);
		if (!submitedForm.isOnError()) {
			// open transaction
			try {
				databaseConnection.setAutoCommit(false);
				Group group = (Group) submitedForm.getMappingData();
				GroupDAO groupDAO = new GroupDAO(databaseConnection);
				group.setId(groupDAO.getNextID());
				groupDAO.insertGroup(group);
				groupDAO.insertMemberToGroup((Account) request.getSession().getAttribute("account"), group);
				databaseConnection.commit();
				databaseConnection.setAutoCommit(true);
				System.out.println("NEW GROUP DONE!");
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					databaseConnection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// try catch => xay ra loi trong chuoi hanh dong => roll back lai het
			System.out.println("ok not error then set success");
			request.setAttribute("sucessed", "Adding new group successfully!");
		} else {
			System.out.println("on error");
			submitedForm.applyErrorsToUI(request);
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-group.jsp").forward(request, response);
	}

}
