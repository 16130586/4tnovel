package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingMemberForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;

/**
 * Servlet implementation class AddingMemberServlet
 */
@WebServlet("/add-member")
public class AddingMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingMemberServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		blindOnwerGroups(request);
		getServletContext().getRequestDispatcher("/jsps/pages/add-member.jsp").forward(request, response);

	}

	private void blindOnwerGroups(HttpServletRequest request) {
		Account hostAccount = (Account) request.getSession().getAttribute("account");
		request.setAttribute("ownerGroups", hostAccount.getOwnerGroups());
		System.out.println("forward and reset owner groups");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//using for when searchAccount got account but it's on post method wants to forward to doGet, this's trick not is good practice!
		if(request.getAttribute("forwardToDoGet") != null) {
			if((Boolean) (request.getAttribute("forwardToDoGet"))) {
				blindOnwerGroups(request); // on forward from another servle
				getServletContext().getRequestDispatcher("/jsps/pages/add-member.jsp").forward(request, response);
				return;
			}
			
		}
		AbstractMappingForm form = new AddingMemberForm(request);
		if (!form.isOnError()) {
			Connection cnn = (Connection) request.getAttribute("connection");
			GroupDAO groupDao = new GroupDAO(cnn);
			Map<String, Integer> data = (Map<String, Integer>) form.getMappingData();
			int targetAccountId = data.get("needToAddAccountId"), rootGroupId = data.get("addToGroupId");
			
			Account targetAccount = new Account(targetAccountId),
					hostAccount = (Account) request.getSession().getAttribute("account");
			Group targetGroup = hostAccount.getGroup(rootGroupId);
			System.out.println(rootGroupId +  " "  + hostAccount.getUserName() + " target group "  +  targetGroup.getName());
			// write to db
			groupDao.insertMemberToGroup(targetAccount, targetGroup);
			// write to ram
			targetGroup.addMember(targetAccount);
			request.setAttribute("sucessed", "Adding a new member to a group is done!");

		} else {
			form.applyErrorsToUI(request);
		}
		
		blindOnwerGroups(request);
		getServletContext().getRequestDispatcher("/jsps/pages/add-member.jsp").forward(request, response);
	}

}
