package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.ChangePasswordForm;
import t4novel.azurewebsites.net.models.Account;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePasswordServlet() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/jsps/pages/account-manage-password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AbstractMappingForm changePasswordForm = new ChangePasswordForm(request);
		if (!changePasswordForm.isOnError()) {
			String newPassword = (String) changePasswordForm.getMappingData();
			AccountDAO accountDAO = new AccountDAO(cnn);
			Account account = (Account) request.getSession().getAttribute("account");
			account.setPassword(newPassword);
			// write to database
			try {
				accountDAO.updateAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// write to ram
			request.getSession().setAttribute("account", account);
			request.setAttribute("sucessed", "Đổi mật khẩu thành công!");
			response.sendRedirect("manage");
		}
		// mapping loi toi user interface
		else {
			changePasswordForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher("/jsps/pages/account-manage-password.jsp").forward(request,
					response);
		}
	}

}
