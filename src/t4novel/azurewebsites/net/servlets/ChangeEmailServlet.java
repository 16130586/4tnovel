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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/jsps/pages/account-manage-mail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		DAOService existedEmailChecker = new EmailCheckingService(cnn);
		AbstractMappingForm changeEmailForm = new ChangeEmailForm(request, existedEmailChecker);
		if (!changeEmailForm.isOnError()) {
			String newEmail = (String) changeEmailForm.getMappingData();
			// TODO write newPassword to database
			AccountDAO accountDAO = new AccountDAO(cnn);
			Account account = (Account) request.getSession().getAttribute("account");
			account.setGmail(newEmail);
			// write to dadabse
			try {
				accountDAO.updateAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// write to ram
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
