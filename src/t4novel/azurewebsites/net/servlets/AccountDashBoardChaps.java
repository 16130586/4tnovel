package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manage/account/dashboard-chaps")
public class AccountDashBoardChaps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountDashBoardChaps() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsps/pages/account-new.all-chap.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + "  " + Arrays.toString(entry.getValue()));
		}
		doGet(request, response);
	}

}
