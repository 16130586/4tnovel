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
import t4novel.azurewebsites.net.DAOService.EmailCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.ChangeEmailForm;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.utils.MailUtils;

/**
 * Servlet implementation class ChangeEmail
 */
@WebServlet("/changeEmail")
public class ChangeEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ChangeEmailServlet() {
		super();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/jsps/pages/account-manage-mail.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Account account = (Account) request.getSession().getAttribute("account");
		
		Connection cnn = (Connection) request.getAttribute("connection");
		DAOService existedEmailChecker = new EmailCheckingService(cnn);
		
		// setting on request attribute for using in changeEmailForm validation
		String verifyCodeOnServer = MailUtils.get(account.getId());
		AbstractMappingForm changeEmailForm = new ChangeEmailForm(request, existedEmailChecker , verifyCodeOnServer);
		if (!changeEmailForm.isOnError()) {
			String newEmail = (String) changeEmailForm.getMappingData();
			AccountDAO accountDAO = new AccountDAO(cnn);
			
			account.setGmail(newEmail);
			// write to dadabse
			try {
				accountDAO.updateAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// write to ram
			MailUtils.remove(account.getId());
			request.getSession().setAttribute("account", account);
			request.setAttribute("sucessed", "Changed email successfully!");
			
			response.sendRedirect("manage");
			
			
		}
		// mapping loi toi user interface
		else {
			changeEmailForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher("/jsps/pages/account-manage-mail.jsp").forward(request, response);
		}
	}
}
