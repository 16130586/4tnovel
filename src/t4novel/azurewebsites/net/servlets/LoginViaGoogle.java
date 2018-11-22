package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPBinding;

import t4novel.azurewebsites.net.acessviagoogle.common.GooglePojo;
import t4novel.azurewebsites.net.acessviagoogle.common.GoogleUtils;

/**
 * Servlet implementation class LoginViaGoogle
 */
@WebServlet("/login-google")
public class LoginViaGoogle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginViaGoogle() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String code = request.getParameter("code");
	    String url = "";
	    System.out.println("pass here");
		if (code == null || code.isEmpty()) {
	        url = "/jsps/pages/login.jsp";
	      } else {
	        String accessToken = GoogleUtils.getToken(code);
	        GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
	        request.setAttribute("id", googlePojo.getId());
	        request.setAttribute("name", googlePojo.getName());
	        request.setAttribute("email", googlePojo.getEmail());
	        url="/index";
	      }
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
