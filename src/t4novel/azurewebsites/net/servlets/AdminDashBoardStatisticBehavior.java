package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manage/admin/statistics/behavior")
public class AdminDashBoardStatisticBehavior extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDashBoardStatisticBehavior() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if (startDate == null || endDate == null) {
			Date current = new Date();
			DateFormat gmtFormat = new SimpleDateFormat("YYYY-MM-dd");
			gmtFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

			startDate = gmtFormat.format(current);
			endDate = startDate;
			System.out.println(current + " , " + startDate + " ," + endDate);
		}
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.getServletContext().getRequestDispatcher("/jsps/pages/admin-new.statistic.user-behavior.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + "  " + Arrays.toString(entry.getValue()));
		}
		doGet(request, response);
	}

}
