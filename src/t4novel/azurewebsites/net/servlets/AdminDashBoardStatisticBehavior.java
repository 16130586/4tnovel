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
		String rawStartDate = request.getParameter("startDate");
		String rawEndDate = request.getParameter("endDate");
		Connection cnn = (Connection) request.getAttribute("connection");
		StatisticDAO statisticDao;

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new EnumAdapterFactory());
		Gson gson = builder.create();
		Type type;

		LocalDate currenVnesDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
		
		LocalDate startDate = null , endDate = null;
		// start validation logic of start and end date
		if(rawStartDate == null || rawEndDate == null) {
			rawStartDate = rawEndDate = currenVnesDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
			startDate = endDate = currenVnesDate;
		}
		
		if(startDate == null || endDate == null) {
			startDate = extractDate(rawStartDate);
			endDate = extractDate(rawEndDate);
		}
		if(endDate.compareTo(currenVnesDate) > 0) {
			endDate = currenVnesDate;
			rawEndDate = endDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		}
		if(startDate.compareTo(endDate) > 0) {
			startDate = endDate;
			rawStartDate = startDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		}
		//end validation logic for start and end date
		
		statisticDao = new StatisticDAO(cnn);
		try {
			DateTimeFormatter fitToDBQueryFmter = DateTimeFormatter.ofPattern("MM-dd-YYYY");
			String fitStartDate = startDate.format(fitToDBQueryFmter);
			String fitEndDate = endDate.plusDays(1).format(fitToDBQueryFmter);
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

			List<Pair<Integer[], Integer>> detailOverLike = statisticDao.statisticDetailOverLike(fitStartDate,
					fitEndDate);
			type = new TypeToken<List<Pair<Integer[], Integer>>>() {
			}.getType();
			String jsonDetailOverLike = gson.toJson(detailOverLike, type);

			List<Pair<Integer[], Integer>> detailOverFollow = statisticDao.statisticDetailOverFollow(fitStartDate,
					fitEndDate);
			String jsonDetailOverFollow = gson.toJson(detailOverFollow, type);

			request.setAttribute("dataOverviewViewBehavior", jsonOverviewViewBehavior);
			request.setAttribute("dataDetailViewBehavior", jsonDetailViewBehavior);
			request.setAttribute("dataDetailOverLike", jsonDetailOverLike);
			request.setAttribute("dataDetailOverFollow", jsonDetailOverFollow);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("startDate", rawStartDate);
		request.setAttribute("endDate", rawEndDate);
		request.getServletContext().getRequestDispatcher("/jsps/pages/admin-new.statistic.user-behavior.jsp")
				.forward(request, response);
	}
	

	// YYYY-MM-dd for input format
	private LocalDate extractDate(String src) {
		return LocalDate.of(Integer.parseInt(src.substring(0, 4)), Integer.parseInt(src.substring(5, 7)),
				Integer.parseInt(src.substring(8, 10)));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
