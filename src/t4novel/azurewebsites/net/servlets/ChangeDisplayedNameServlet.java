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
import t4novel.azurewebsites.net.DAOService.ExisteddNameCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.ChangeDisplayedNameForm;
import t4novel.azurewebsites.net.models.Account;

/**
 * Servlet implementation class ChangeDisplayedName
 */
@WebServlet("/changeNickname")
public class ChangeDisplayedNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ChangeDisplayedNameServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		Connection cnn = (Connection) request.getAttribute("connection");
		DAOService existedDisplayedNameChecker = new ExisteddNameCheckingService(cnn);
		AbstractMappingForm changeDisplayedNameForm = new ChangeDisplayedNameForm(request, existedDisplayedNameChecker);
		if (!changeDisplayedNameForm.isOnError()) {
			String newDisplayedName = (String) changeDisplayedNameForm.getMappingData();
			AccountDAO accountDAO = new AccountDAO(cnn);

			Account account = (Account) request.getSession().getAttribute("account");

			account.setDisplayedName(newDisplayedName);
			// write to databse
			try {
				accountDAO.updateAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// write to caches
			request.getSession().setAttribute("account", account);
			request.setAttribute("sucessed", "Đổi tên thành công!");
			response.sendRedirect("manage");
		}
		// mapping loi toi user interface
		else {
			changeDisplayedNameForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher("/jsps/pages/account-manage-display-name.jsp").forward(request,
					response);
		}
	}

}
