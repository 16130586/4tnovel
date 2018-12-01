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
import t4novel.azurewebsites.net.DAOService.ExistedPasswordCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.ChangeEmailForm;

import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class ChangeEmail
 */
@WebServlet("/changeEmail")
public class ChangeEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeEmailServlet() {
        super();
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		Connection cnn =(Connection) request.getAttribute("connection");
		DAOService existedPasswordChecker = new ExistedPasswordCheckingService(cnn);
		AbstractMappingForm changeEmailForm = new ChangeEmailForm(request, existedPasswordChecker);
		if(!changeEmailForm.isOnError()) {
			String newEmail = (String)changeEmailForm.getMappingData();
			// TODO write newPassword to database
			AccountDAO accountDAO = new AccountDAO(cnn);
			Account account = (Account) request.getSession().getAttribute("account");
			account.setGmail(newEmail);
			accountDAO.updateAccount(account);
			request.getSession().setAttribute("account", account);
			System.out.println("changing ");
			// TODO print success message to user interface
			request.setAttribute("sucessed", "Changed email successfully!");
			response.sendRedirect("manage");
		}
		//mapping loi toi user interface
		else {
			changeEmailForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher("/jsps/pages/account-manage-mail.jsp").forward(request, response);
		}
	}

}
