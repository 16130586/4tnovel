package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.models.Group;

import t4novel.azurewebsites.net.DAOSUtils.DAOUtils;
import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.DAOService.ExistedDisplayedNameCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingGroupForm;
import t4novel.azurewebsites.net.utils.Genrator;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		Genrator idGenrator = Genrator.getInstance();
		Connection databaseConnection = DAOUtils.getInstance().getConnection();
		DAOService existedGroupNameChecker = new ExistedDisplayedNameCheckingService(databaseConnection);
		AbstractMappingForm submitedForm = new AddingGroupForm(request, idGenrator, existedGroupNameChecker);
		if (!submitedForm.isOnError()) {
			Group group = (Group) submitedForm.getMappingData();
			// TODO write to db both group and account owner group
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
