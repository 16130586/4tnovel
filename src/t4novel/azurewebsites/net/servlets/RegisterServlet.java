package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAOSUtils.DAOUtils;
import t4novel.azurewebsites.net.DAOService.EmailCheckingService;
import t4novel.azurewebsites.net.DAOService.UserNameCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.RegisterForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.utils.Genrator;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/jsps/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOUtils daoUtil = DAOUtils.getInstance();
		Connection cnn = daoUtil.getConnection();
		Genrator genrator = Genrator.getInstance();
		AbstractMappingForm userSubmittedForm = new RegisterForm(request, new EmailCheckingService(cnn),
				new UserNameCheckingService(cnn), genrator);

		String url = "";
		if (!userSubmittedForm.isOnError()) {
			url = "/index";
			// TODO write account to DTB
			
			//
			Account account = (Account) userSubmittedForm.getMappingData();
			request.getSession().setAttribute("account", account);
			response.sendRedirect("index");
		} else {
			url = "/jsps/pages/register.jsp";
			//mapping error to jsp!
			//error == (errorType , errorMessage)
			for (Entry<String, String> error : userSubmittedForm.getErrors().entrySet()) {
				String errorName = error.getKey() + "Error";
				request.setAttribute(errorName, error.getValue());
			}
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}

		daoUtil.close(cnn);
		
	}
}
