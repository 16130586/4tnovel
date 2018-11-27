package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.DAOService.ExistedDisplayedNameCheckingService;
import t4novel.azurewebsites.net.DAOService.ExistedPasswordCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.ChangeDisplayedNameForm;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class ChangeDisplayedName
 */
@WebServlet("/changeNickname")
public class ChangeDisplayedNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeDisplayedNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cnn =(Connection) request.getAttribute("connection");
		DAOService existedPasswordChecker = new ExistedPasswordCheckingService(cnn);
		DAOService existedDisplayedName = new ExistedDisplayedNameCheckingService(cnn);
		AbstractMappingForm changeDisplayedNameForm = new ChangeDisplayedNameForm(request, existedPasswordChecker, existedDisplayedName);
		if(!changeDisplayedNameForm.isOnError()) {
			String newDisplayedName = (String)changeDisplayedNameForm.getMappingData();
			AccountDAO accountDAO = new AccountDAO(cnn);
			Account account = (Account) request.getSession().getAttribute("account");
			account.setDisplayedName(newDisplayedName);
			accountDAO.updateAccount(account);
			request.getSession().setAttribute("account", account);
			request.setAttribute("sucessed", "Changed displayed name successfully!");
			response.sendRedirect("manage");
		}
		//mapping loi toi user interface
		else {
			changeDisplayedNameForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher("/jsps/pages/account-manage-display-name.jsp").forward(request, response);
		}
	}

}