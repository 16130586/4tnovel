package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.data.structure.Pair;
import t4novel.azurewebsites.net.data.structure.Tripple;

public class StatisticDAO {
	private Connection cnn;

	public StatisticDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	public List<Pair<Integer[], Integer>> statisticOverViewViewBehavior(String startDate, String endDate)
			throws SQLException {
		String query = "select datepart(hour, VIEWING.VIEWDATE) AS E, count(VIEWING.VIEWDATE) / DATEDIFF(day, ?, ?) AS C from VIEWING where VIEWING.VIEWDATE between ? and ? and VIEWING.STREAM = ? group by datepart(hour, VIEWING.VIEWDATE)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<Integer[], Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		stmt.setString(3, startDate);
		stmt.setString(4, endDate);
		stmt.setString(5, "global");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Integer[] combineToTimeOfDate = new Integer[4];
			combineToTimeOfDate[0] = rs.getInt(1);
			combineToTimeOfDate[1] = 0;
			combineToTimeOfDate[2] = 0;
			combineToTimeOfDate[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDate, rs.getInt(2)));
		}
		stmt.close();
		rs.close();
		
		if(ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4] , combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] =23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 10));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay,10));
		}
		return ret;
	}

	private List<Pair<Integer[], Integer>> statisticDetailGuestView(String startDate, String endDate)
			throws SQLException {
		String query = "select datepart(hour, VIEWING.VIEWDATE) AS E, count(VIEWING.VIEWDATE) / DATEDIFF(day, ?, ?) AS C from VIEWING where VIEWING.VIEWDATE between ? and ? and VIEWING.STREAM = ? and VIEWING.ACCID < 0 group by datepart(hour, VIEWING.VIEWDATE)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<Integer[], Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		stmt.setString(3, startDate);
		stmt.setString(4, endDate);
		stmt.setString(5, "global");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Integer[] combineToTimeOfDate = new Integer[4];
			combineToTimeOfDate[0] = rs.getInt(1);
			combineToTimeOfDate[1] = 0;
			combineToTimeOfDate[2] = 0;
			combineToTimeOfDate[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDate, rs.getInt(2)));
		}
		stmt.close();
		rs.close();
		if(ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4] , combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 10));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay,20));
		}
		return ret;
	}

	private List<Pair<Integer[], Integer>> statisticDetailUserView(String startDate, String endDate)
			throws SQLException {
		String query = "select datepart(hour, VIEWING.VIEWDATE) AS E, count(VIEWING.VIEWDATE) / DATEDIFF(day, ?, ?) AS C from VIEWING where VIEWING.VIEWDATE between ? and ? and VIEWING.STREAM = ? and VIEWING.ACCID >= 0 group by datepart(hour, VIEWING.VIEWDATE)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<Integer[], Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		stmt.setString(3, startDate);
		stmt.setString(4, endDate);
		stmt.setString(5, "global");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Integer[] combineToTimeOfDate = new Integer[4];
			combineToTimeOfDate[0] = rs.getInt(1);
			combineToTimeOfDate[1] = 0;
			combineToTimeOfDate[2] = 0;
			combineToTimeOfDate[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDate, rs.getInt(2)));
		}
		stmt.close();
		rs.close();
		if(ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4] , combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 10));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay,100));
		}
		return ret;
	}

	public List<Tripple<Integer[], Integer, Integer>> statisticDetailViewBehavior(String startDate, String endDate)
			throws SQLException {
		List<Pair<Integer[], Integer>> guest = statisticDetailGuestView(startDate, endDate);
		List<Pair<Integer[], Integer>> user = statisticDetailUserView(startDate, endDate);
		List<Tripple<Integer[], Integer, Integer>> combineForTwoLineChart = combineOnSameKey(guest, user);
		return combineForTwoLineChart;
	}

	private List<Tripple<Integer[], Integer, Integer>> combineOnSameKey(List<Pair<Integer[], Integer>> guest,
			List<Pair<Integer[], Integer>> user) {
		List<Tripple<Integer[], Integer, Integer>> ret = new ArrayList<>(guest.size());
		Pair<Integer[], Integer> pair;
		for (int i = 0; i < guest.size(); i++) {
			pair = guest.get(i);
			ret.add(new Tripple<Integer[], Integer, Integer>(pair.k, pair.v, user.get(i).v));
		}
		return ret;
	}

	/*
	 * public List<Pair<String, Integer>> statisticOverLike(String startDate, String
	 * endDate) throws SQLException { String query =
	 * "select format(LIKING.DATEUP, 'MM/dd/yyyy') as _Days, count(LIKING.DATEUP) as _Likes from LIKING where LIKING.DATEUP between ? and ? and LIKING.STREAM = ? group by format(LIKING.DATEUP, 'MM/dd/yyyy')"
	 * ; PreparedStatement stmt = cnn.prepareStatement(query); List<Pair<String,
	 * Integer>> ret = new LinkedList<>();
	 * 
	 * stmt.setString(1, startDate); stmt.setString(2, endDate); stmt.setString(3,
	 * "novel"); ResultSet rs = stmt.executeQuery(); while (rs.next()) {
	 * System.out.println(rs.getString(1)); ret.add(new Pair<String,
	 * Integer>(rs.getString(1), rs.getInt(2))); } stmt.close(); rs.close(); return
	 * ret; }
	 */

	public List<Pair<Integer[], Integer>> statisticDetailOverLike(String startDate, String endDate)
			throws SQLException {
		String query = "select datepart(hour, LIKING.DATEUP) as _Days, count(LIKING.DATEUP) / DATEDIFF(day, ?, ?) AS _Likes "
				+ "from LIKING where LIKING.DATEUP between ? and ? and LIKING.STREAM = ? group by datepart(hour, LIKING.DATEUP)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<Integer[], Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		stmt.setString(3, startDate);
		stmt.setString(4, endDate);
		stmt.setString(5, "novel");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Integer[] combineToTimeOfDate = new Integer[4];
			combineToTimeOfDate[0] = rs.getInt(1);
			combineToTimeOfDate[1] = 0;
			combineToTimeOfDate[2] = 0;
			combineToTimeOfDate[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDate, rs.getInt(2)));
		}
		stmt.close();
		rs.close();
		if(ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4] , combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 10));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay,100));
		}
		return ret;
	}
	
	public List<Pair<Integer[], Integer>> statisticDetailOverFollow(String startDate, String endDate)
			throws SQLException {
		String query = "select datepart(hour, FOLLOW.DATEUP) as _Hour, count(FOLLOW.DATEUP) / DATEDIFF(day, ?, ?) AS _Follow from FOLLOW where FOLLOW.DATEUP between ? and ? and FOLLOW.STREAM = ? group by datepart(hour, FOLLOW.DATEUP)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<Integer[], Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		stmt.setString(3, startDate);
		stmt.setString(4, endDate);
		stmt.setString(5, "novel");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Integer[] combineToTimeOfDate = new Integer[4];
			combineToTimeOfDate[0] = rs.getInt(1);
			combineToTimeOfDate[1] = 0;
			combineToTimeOfDate[2] = 0;
			combineToTimeOfDate[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDate, rs.getInt(2)));
		}
		stmt.close();
		rs.close();
		if(ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4] , combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 0));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay,0));
		}
		return ret;
	}
}
