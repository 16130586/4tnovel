package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.DAO.CensoringDAO;
import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.DAOService.EmailCheckingService;
import t4novel.azurewebsites.net.DAOService.UserNameCheckingService;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.RegisterForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;

@WebServlet("/manage/admin/dashboard-accounts")
public class AdminDashBoardAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDashBoardAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accDao = new AccountDAO(cnn);
		List<Account> accounts = null;
		try {
			accounts = accDao.getBasicInformationAccounts();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("accounts", accounts);
		getServletContext().getRequestDispatcher("/jsps/pages/admin-new.all-account.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accountDao = new AccountDAO(cnn);

		String action = request.getParameter("action");
		if ("delete".equals(action)) {
			int accountID = Integer.parseInt(request.getParameter("id"));
			try {
				accountDao.deleteAccount(accountDao.getAccountByID(accountID), new NovelDAO(cnn), new GroupDAO(cnn), new CensoringDAO(cnn));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("create".equals(action)) {
			DAOService checkingEmailService = new EmailCheckingService(cnn);
			DAOService usernameService = new UserNameCheckingService(cnn);
			AbstractMappingForm form = new RegisterForm(request, checkingEmailService, usernameService);
			if (!form.isOnError()) {
				Account account = (Account) form.getMappingData();
				String pin = request.getParameter("pin");
				String autoPassPublishment = request.getParameter("autoPassPublishment");
				String ban = request.getParameter("ban");
				int role = Integer.parseInt(request.getParameter("role"));

				account.setPin(Boolean.parseBoolean(pin));
				account.setAutoPassPushlishment(Boolean.parseBoolean(autoPassPublishment));
				account.setBan(Boolean.parseBoolean(ban));
				account.setRole(Role.getRole(role));

				try {
					accountDao.insertAccount(account);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		}
		if ("edit".equals(action)) {
			String pin = request.getParameter("pin");
			String autoPassPublishment = request.getParameter("autoPassPublishment");
			String ban = request.getParameter("ban");
			int role = Integer.parseInt(request.getParameter("role"));
			int accountID = Integer.parseInt(request.getParameter("id"));
			
			Account account;
			try {
				account = accountDao.getAccountByID(accountID);
				account.setPin(Boolean.parseBoolean(pin));
				account.setAutoPassPushlishment(Boolean.parseBoolean(autoPassPublishment));
				account.setBan(Boolean.parseBoolean(ban));
				account.setRole(Role.getRole(role));
				accountDao.updateAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
