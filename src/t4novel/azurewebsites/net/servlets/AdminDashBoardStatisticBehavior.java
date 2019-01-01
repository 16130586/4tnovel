package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import t4novel.azurewebsites.net.DAO.StatisticDAO;
import t4novel.azurewebsites.net.data.structure.Pair;
import t4novel.azurewebsites.net.data.structure.Tripple;
import t4novel.azurewebsites.net.utils.EnumAdapterFactory;

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
		Connection cnn = (Connection) request.getAttribute("connection");
		StatisticDAO statisticDao;

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new EnumAdapterFactory());
		Gson gson = builder.create();
		Type type;

		if (startDate == null || endDate == null) {
			LocalDate currenVnesDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
			startDate = currenVnesDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
			endDate = startDate;
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);

			statisticDao = new StatisticDAO(cnn);
			try {
				DateTimeFormatter fitToDBQueryFmter = DateTimeFormatter.ofPattern("MM-dd-YYYY");
				String fitStartDate = currenVnesDate.format(fitToDBQueryFmter);
				String fitEndDate = currenVnesDate.plusDays(1).format(fitToDBQueryFmter);
				List<Pair<Integer[], Integer>> overviewViewBehaviorData = statisticDao
						.statisticOverViewViewBehavior(fitStartDate, fitEndDate);

				type = new TypeToken<List<Pair<Integer[], Integer>>>() {
				}.getType();
				String jsonOverviewViewBehavior = gson.toJson(overviewViewBehaviorData, type);

				List<Tripple<Integer[], Integer, Integer>> detailViewBehaviorData = statisticDao
						.statisticDetailViewBehavior(fitStartDate, fitEndDate);
				type = new TypeToken<List<Tripple<Integer[], Integer, Integer>>>() {
				}.getType();
				String jsonDetailViewBehavior = gson.toJson(detailViewBehaviorData, type);

				List<Pair<Integer[], Integer>> detailOverLike = statisticDao.statisticDetailOverLike(fitStartDate,fitEndDate);
				type = new TypeToken<List<Pair<Integer[], Integer>>>() {
				}.getType();
				String jsonDetailOverLike = gson.toJson(detailOverLike, type);
				
				List<Pair<Integer[], Integer>> detailOverFollow = statisticDao.statisticDetailOverFollow(fitStartDate, fitEndDate);
				String jsonDetailOverFollow = gson.toJson(detailOverFollow, type);
				
				request.setAttribute("dataOverviewViewBehavior", jsonOverviewViewBehavior);
				request.setAttribute("dataDetailViewBehavior", jsonDetailViewBehavior);
				request.setAttribute("dataDetailOverLike", jsonDetailOverLike);
				request.setAttribute("dataDetailOverFollow", jsonDetailOverFollow);
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getServletContext().getRequestDispatcher("/jsps/pages/admin-new.statistic.user-behavior.jsp")
					.forward(request, response);
			return;
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
