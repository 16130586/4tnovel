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
import t4novel.azurewebsites.net.DAOService.LoginCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.LoginForm;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsps/pages/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		DAOService loginCheckingService = new LoginCheckingService(cnn);
		AccountDAO accountDAO = new AccountDAO(cnn);
		AbstractMappingForm loginForm = new LoginForm(request, loginCheckingService);
		
		if(!loginForm.isOnError()) {
			Account account = (Account) loginForm.getMappingData();
			account = accountDAO.getAccount(account.getUserName());
			request.getSession().setAttribute("account", account);
			response.sendRedirect("index");
			System.out.println("suceess!");
		}
		else {
			loginForm.applyErrorsToUI(request);
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html, charset=UTF-8");
			getServletContext().getRequestDispatcher("/jsps/pages/login.jsp").forward(request, response);
			System.out.println("eror!");
		}
		
	}

}
