package t4novel.azurewebsites.net.servlets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.utils.MailUtils;
import t4novel.azurewebsites.net.utils.StringUtil;
import t4novel.azurewebsites.net.utils.TokenGenrator;

@WebServlet("/send-mail-code")
public class SendingMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendingMailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		Account hostAccount = (Account) request.getSession().getAttribute("account");
		
		ServletContext context = getServletContext();

		String from = context.getInitParameter("autoMailDomain");
		String password = context.getInitParameter("autoMailPassword");
		String port = context.getInitParameter("autoMailPort");
		String oldMail = hostAccount.getGmail();

		MailUtils mailUtil = new MailUtils();
		mailUtil.setDomain(from);
		mailUtil.setPassword(password);

		boolean onError = false;
		try {
			String verifyCode = TokenGenrator.genrateVerifyCode(6);
			mailUtil.sendingVerifyCode(oldMail,StringUtil.toAsterisk(hostAccount.getUserName()) ,Integer.parseInt(port), verifyCode);
			MailUtils.remember(hostAccount.getId(), verifyCode);
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
	}

}
