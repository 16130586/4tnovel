package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.DAOService.EmailCheckingService;
import t4novel.azurewebsites.net.DAOService.UserNameCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.RegisterForm;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		//
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/jsps/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AbstractMappingForm userSubmittedForm = new RegisterForm(request, new EmailCheckingService(cnn),
				new UserNameCheckingService(cnn));

		String url = "";
		if (!userSubmittedForm.isOnError()) {
			AccountDAO accDAO = new AccountDAO(cnn);
			url = "/index";
			Account account = (Account) userSubmittedForm.getMappingData();
			account.setId(accDAO.getNextID());

			try {
				accDAO.insertAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.getSession().setAttribute("account", account);
			response.sendRedirect("index");
		} else {
			url = "/jsps/pages/register.jsp";
			// mapping error to jsp!
			// error == (errorType , errorMessage)
			userSubmittedForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}

	}
}
