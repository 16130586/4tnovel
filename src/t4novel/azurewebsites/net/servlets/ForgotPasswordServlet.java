package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.ChangeEmailForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.utils.MailUtils;
import t4novel.azurewebsites.net.utils.StringUtil;
import t4novel.azurewebsites.net.utils.TokenGenrator;

@WebServlet("/forgot")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForgotPasswordServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html, charset=UTF-8");
		String username = request.getParameter("username");
		if (username != null) {
			Connection cnn = (Connection) request.getAttribute("connection");
			AccountDAO accountDao = new AccountDAO(cnn);
			Account account = null;
			try {
				account = accountDao.getAccountByUsername(username);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (account != null) {
				ServletContext context = getServletContext();

				String from = context.getInitParameter("autoMailDomain");
				String password = context.getInitParameter("autoMailPassword");
				String port = context.getInitParameter("autoMailPort");
				String oldMail = account.getGmail();

				MailUtils mailUtil = new MailUtils();
				mailUtil.setDomain(from);
				mailUtil.setPassword(password);

				boolean onError = false;
				try {
					String verifyCode = TokenGenrator.genrateVerifyCode(6);
					mailUtil.sendingVerifyCode(oldMail,StringUtil.toAsterisk(account.getUserName()) ,Integer.parseInt(port), verifyCode);
					MailUtils.remember(account.getId(), verifyCode);
					request.setAttribute("accountId", account.getId());
					request.setAttribute("sentMail", true);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.out.println("chua cau hinh init param cho sendingMail domain trong xml");
					onError = true;
				} catch (MessagingException e) {
					e.printStackTrace();
					response.sendError(500, "Error on server, please contact Page's Admin!");
					onError = true;
				}
				if (!onError) {
					response.setStatus(200);
				}
			} else
				request.setAttribute("errUsername", "Không tìm thấy tài khoản");
		}
		
		
		getServletContext().getRequestDispatcher("/jsps/pages/forgot-password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			// setting on request attribute for using in changeEmailForm validation
			String verifyCodeOnServer = MailUtils.get(id);
			String reVerifyCode = request.getParameter("otp");
			if (verifyCodeOnServer.equals(reVerifyCode)) {
				String password = request.getParameter("new-password");
				if (password.length() > 8) {
					AccountDAO accountDAO = new AccountDAO(cnn);
					Account account = accountDAO.getAccountByID(id);
					account.setPassword(password);
					// write to dadabse
					try {
						accountDAO.updateAccount(account);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// write to ram
					MailUtils.remove(account.getId());
					request.getSession().setAttribute("account", account);
					response.sendRedirect("index");
				} else {
					request.setAttribute("errPassword", "Vui lòng nhập mật khẩu dài hơn 8 kí tự");
					request.setAttribute("accountId", id);
					request.setAttribute("sentMail", true);
					getServletContext().getRequestDispatcher("/jsps/pages/forgot-password.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errOTP", "Sai mã OTP");
				request.setAttribute("accountId", id);
				request.setAttribute("sentMail", true);
				getServletContext().getRequestDispatcher("/jsps/pages/forgot-password.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
