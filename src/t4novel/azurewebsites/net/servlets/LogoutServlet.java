package t4novel.azurewebsites.net.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.session.OnlineAccounts;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Enumeration<String> attributeInSessionNames = session.getAttributeNames();
//		while(attributeInSessionNames.hasMoreElements()) {
//			String attName = attributeInSessionNames.nextElement();
//			session.setAttribute(attName, null);
//		}
//		session.invalidate();
		HttpSession ss = request.getSession(false);
		OnlineAccounts.onLogoutAccount((Account)ss.getAttribute("account"));
		ss.setMaxInactiveInterval(1);
		request.getSession(false).invalidate();
		response.sendRedirect("index");
	}

}
