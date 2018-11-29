package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;
import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.DAOService.ExistedDisplayedNameCheckingService;
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
		// TODO adding catching error on jsp form
		// TODO adding catching success on jsp form
		Connection databaseConnection = (Connection) request.getAttribute("connection");
		DAOService existedGroupNameChecker = new ExistedDisplayedNameCheckingService(databaseConnection);
		AbstractMappingForm submitedForm = new AddingGroupForm(request, existedGroupNameChecker);
		if (!submitedForm.isOnError()) {
			// open transaction
			Group group = (Group) submitedForm.getMappingData();
			GroupDAO groupDAO = new GroupDAO(databaseConnection);
			group.setId(groupDAO.getNextID());
			groupDAO.insertGroup(group);
			groupDAO.insertMemberToAGroup((Account) request.getSession().getAttribute("account"), group);
			// close transaction
			// try catch => xay ra loi trong chuoi hanh dong => roll back lai het
			System.out.println("ok not error then set success");
			request.setAttribute("sucessed", "Adding new group successfully!");
		} else {
			System.out.println("on error");
			submitedForm.applyErrorsToUI(request);
			// forward nguoc tro lai ve form
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-group.jsp").forward(request, response);
	}

}
