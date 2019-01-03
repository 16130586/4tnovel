package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
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
import t4novel.azurewebsites.net.utils.DateExporter;
import t4novel.azurewebsites.net.utils.EnumAdapterFactory;

@WebServlet("/manage/admin/statistics/system")
public class AdminDashBoardStatisticSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDashBoardStatisticSystem() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rawStartDate = request.getParameter("startDate");
		String rawEndDate = request.getParameter("endDate");
		System.out.println(rawStartDate + " " + rawEndDate);

		Connection cnn = (Connection) request.getAttribute("connection");
		StatisticDAO statisticDao;

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new EnumAdapterFactory());
		Gson gson = builder.create();
		Type type;

		LocalDate currenVnesDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));

		LocalDate startDate = null, endDate = null;
		// start validation logic of start and end date
		// default choice

		if (rawStartDate == null || rawEndDate == null) {
			rawStartDate = rawEndDate = currenVnesDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
			startDate = endDate = currenVnesDate;
		}
		// end default choice

		// extract raw date to comparable date
		if (startDate == null || endDate == null) {
			startDate = DateExporter.extractDate(rawStartDate);
			endDate = DateExporter.extractDate(rawEndDate);
		}
		// end extract raw date

		if (endDate.compareTo(currenVnesDate) > 0) {
			endDate = currenVnesDate;
			rawEndDate = endDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		}
		if (startDate.compareTo(endDate) > 0) {
			startDate = endDate;
			rawStartDate = startDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		}
		// end validation logic for start and end date

		statisticDao = new StatisticDAO(cnn);
		try {
			List<Pair<String, Integer>> detailNovelOverDays, detailChapOverDays, detailAccountOverDays,
					detailThreadOverDays;
			String jsonDetailNovelOverDays, jsonDetailChapOverDays, jsonDetailAccountOverDays, jsonDetailThreadOverDays;
			int dataNovelOneDay, dataChapOneDay, dataAccountOneDay, dataThreadOneDay;
			DateTimeFormatter fitToDBQueryFmter = DateTimeFormatter.ofPattern("MM-dd-YYYY");

			// reformat for the using of query
			Date currentDate = new Date();
			String fitStartDate = startDate.format(fitToDBQueryFmter);
			String fitEndDate = endDate.plusDays(1).format(fitToDBQueryFmter);
			String fitCurrentDate = fitToDBQueryFmter.format(currenVnesDate);

			// end format

			// loading raw data
			detailNovelOverDays = statisticDao.statisticNovelOverDays(fitStartDate, fitEndDate);
			detailChapOverDays = statisticDao.statisticChapOverDays(fitStartDate, fitEndDate);
			detailAccountOverDays = statisticDao.statisticAccountOverDays(fitStartDate, fitEndDate);
			detailThreadOverDays = statisticDao.statisticThreadOverDays(fitStartDate, fitEndDate);
			
			dataNovelOneDay = statisticDao.statisticNovelOneDay(fitCurrentDate);
			dataChapOneDay = statisticDao.statisticChapOneDay(fitCurrentDate);
			dataAccountOneDay = statisticDao.statisticAccountOneDay(fitCurrentDate);
			dataThreadOneDay = statisticDao.statisticThreadOneDay(fitCurrentDate);

			// end loading raw data

			// start parsing to json for the chart's input
			type = new TypeToken<List<Pair<String, Integer>>>() {
			}.getType();

			jsonDetailNovelOverDays = gson.toJson(detailNovelOverDays, type);
			jsonDetailChapOverDays = gson.toJson(detailChapOverDays, type);
			jsonDetailAccountOverDays = gson.toJson(detailAccountOverDays, type);
			jsonDetailThreadOverDays = gson.toJson(detailThreadOverDays, type);

			// end
			System.out.println(jsonDetailNovelOverDays);
			System.out.println(jsonDetailChapOverDays);
			System.out.println(jsonDetailAccountOverDays);
			System.out.println(jsonDetailThreadOverDays);

			// passing data to chart on the view
			request.setAttribute("dataDetailNovelOverDays", jsonDetailNovelOverDays);
			request.setAttribute("dataDetailChapOverDays", jsonDetailChapOverDays);
			request.setAttribute("dataDetailAccountOverDays", jsonDetailAccountOverDays);
			request.setAttribute("dataDetailThreadOverDays", jsonDetailThreadOverDays);
			
			request.setAttribute("dataNovelOneDay", dataNovelOneDay);
			request.setAttribute("dataChapOneDay", dataChapOneDay);
			request.setAttribute("dataAccountOneDay", dataAccountOneDay);
			request.setAttribute("dataThreadOneDay", dataThreadOneDay);
			// end
			
			//
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("startDate", rawStartDate);
		request.setAttribute("endDate", rawEndDate);
		getServletContext().getRequestDispatcher("/jsps/pages/admin-new.statistic.system.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + "  " + Arrays.toString(entry.getValue()));
		}
		String action = request.getParameter("action");
		if (action == null) {
			response.sendError(404);
			return;
		}
		String rawStartDate = request.getParameter("startDate");
		String rawEndDate = request.getParameter("endDate");
		Connection cnn = (Connection) request.getAttribute("connection");
		StatisticDAO statisticDao;

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new EnumAdapterFactory());
		Gson gson = builder.create();
		Type type;

		LocalDate currenVnesDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));

		LocalDate startDate = null, endDate = null;
		// start validation logic of start and end date
		// default choice
		if (rawStartDate == null || rawEndDate == null) {
			rawStartDate = rawEndDate = currenVnesDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
			startDate = endDate = currenVnesDate;
		}
		// end default choice

		// extract raw date to comparable date
		if (startDate == null || endDate == null) {
			startDate = DateExporter.extractDate(rawStartDate);
			endDate = DateExporter.extractDate(rawEndDate);
		}
		// end extract raw date

		if (endDate.compareTo(currenVnesDate) > 0) {
			endDate = currenVnesDate;
			rawEndDate = endDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		}
		if (startDate.compareTo(endDate) > 0) {
			startDate = endDate;
			rawStartDate = startDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		}
		// end validation logic for start and end date

		statisticDao = new StatisticDAO(cnn);
		DateTimeFormatter fitToDBQueryFmter = DateTimeFormatter.ofPattern("MM-dd-YYYY");

		// reformat for the using of query
		String fitStartDate = startDate.format(fitToDBQueryFmter);
		String fitEndDate = endDate.plusDays(1).format(fitToDBQueryFmter);
		String json = "";
		if ("detailNovel".equals(action)) {
			try {
				Tripple<String, String, List<Pair<String, Integer>>> ret = new Tripple<>(rawStartDate, rawEndDate,
						statisticDao.statisticNovelOverDays(fitStartDate, fitEndDate));
				 type = new TypeToken<Tripple<String, String, List<Pair<String, Integer>>>>() {
					}.getType();
					
					json = gson.toJson(ret, type);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("detailChap".equals(action)) {
			try {
				Tripple<String, String, List<Pair<String, Integer>>> ret = new Tripple<>(rawStartDate, rawEndDate,
						statisticDao.statisticChapOverDays(fitStartDate, fitEndDate));
				 type = new TypeToken<Tripple<String, String, List<Pair<String, Integer>>>>() {
					}.getType();
					
					json = gson.toJson(ret, type);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if ("detailAccount".equals(action)) {
			try {
				Tripple<String, String, List<Pair<String, Integer>>> ret = new Tripple<>(rawStartDate, rawEndDate,
						statisticDao.statisticAccountOverDays(fitStartDate, fitEndDate));
				 type = new TypeToken<Tripple<String, String, List<Pair<String, Integer>>>>() {
					}.getType();
					
					json = gson.toJson(ret, type);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if ("detailThread".equals(action)) {
			try {
				Tripple<String, String, List<Pair<String, Integer>>> ret = new Tripple<>(rawStartDate, rawEndDate,
						statisticDao.statisticThreadOverDays(fitStartDate, fitEndDate));
				 type = new TypeToken<Tripple<String, String, List<Pair<String, Integer>>>>() {
					}.getType();
					
					json = gson.toJson(ret, type);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(action + " " + json);
		response.setStatus(200);
		response.setContentType("text/plain");
		response.getWriter().println(json);
		response.getWriter().flush();
	}

}
