package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

		if (ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4], combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 0));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay, 0));
		}
		Collections.sort(ret, new Comparator<Pair<Integer[], Integer>>() {
			@Override
			public int compare(Pair<Integer[], Integer> o1, Pair<Integer[], Integer> o2) {
				return o1.k[0].compareTo(o2.k[0]);
			}
		});
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
		if (ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4], combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 0));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay, 0));
		}
		Collections.sort(ret, new Comparator<Pair<Integer[], Integer>>() {
			@Override
			public int compare(Pair<Integer[], Integer> o1, Pair<Integer[], Integer> o2) {
				return o1.k[0].compareTo(o2.k[0]);
			}
		});
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
		if (ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4], combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 0));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay, 0));
		}
		Collections.sort(ret, new Comparator<Pair<Integer[], Integer>>() {
			@Override
			public int compare(Pair<Integer[], Integer> o1, Pair<Integer[], Integer> o2) {
				return o1.k[0].compareTo(o2.k[0]);
			}
		});
		return ret;
	}

	public List<Tripple<Integer[], Integer, Integer>> statisticDetailViewBehavior(String startDate, String endDate)
			throws SQLException {
		List<Pair<Integer[], Integer>> guest = statisticDetailGuestView(startDate, endDate);
		List<Pair<Integer[], Integer>> user = statisticDetailUserView(startDate, endDate);
		List<Tripple<Integer[], Integer, Integer>> combineForTwoLineChart = combineOnSameKey(guest, user);
		Collections.sort(combineForTwoLineChart, new Comparator<Tripple<Integer[], Integer, Integer>>() {
			@Override
			public int compare(Tripple<Integer[], Integer, Integer> o1, Tripple<Integer[], Integer, Integer> o2) {
				return o1.k[0].compareTo(o2.k[0]);
			}
		});
		return combineForTwoLineChart;
	}

	private List<Tripple<Integer[], Integer, Integer>> combineOnSameKey(List<Pair<Integer[], Integer>> guest,
			List<Pair<Integer[], Integer>> user) {
		List<Tripple<Integer[], Integer, Integer>> ret = new ArrayList<>(guest.size());

		// combine algorithm
		Tripple<Integer[], Integer, Integer> entry;
		Pair<Integer[], Integer> temp;
		int i = 0, j = 0;
		while (i < guest.size() && j < user.size()) {
			entry = new Tripple<Integer[], Integer, Integer>();
			temp = guest.get(i);
			entry.k = temp.k;
			if (guest.get(i).k[0].equals(user.get(j).k[0])) {
				entry.v1 = user.get(j).v;
				entry.v2 = temp.v;
				j++;
			} else {
				entry.v1 = temp.v;
				entry.v2 = 0;
			}
			i++;
			ret.add(entry);
		}
		while (i < guest.size()) {
			temp = guest.get(i);
			entry = new Tripple<>(temp.k, 0, temp.v);
			if (!ret.contains(entry))
				ret.add(entry);
			i++;
		}

		while (j < user.size()) {
			temp = user.get(j);
			entry = new Tripple<>(temp.k, temp.v, 0);
			if (!ret.contains(entry))
				ret.add(entry);
			j++;
		}

		// end combine algorithm
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
		if (ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4], combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 0));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay, 0));
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
		if (ret.isEmpty()) {
			Integer[] combineToTimeOfDateStart = new Integer[4], combineToTimeOfDateEndDay = new Integer[4];
			combineToTimeOfDateStart[0] = 0;
			combineToTimeOfDateStart[1] = 0;
			combineToTimeOfDateStart[2] = 0;
			combineToTimeOfDateStart[3] = 0;
			combineToTimeOfDateEndDay[0] = 23;
			combineToTimeOfDateEndDay[1] = 0;
			combineToTimeOfDateEndDay[2] = 0;
			combineToTimeOfDateEndDay[3] = 0;
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateStart, 0));
			ret.add(new Pair<Integer[], Integer>(combineToTimeOfDateEndDay, 0));
		}
		return ret;
	}
	
	
	public List<Pair<String, Integer>> statisticNovelOverDays(String startDate, String endDate) throws SQLException{
		String query = "SELECT FORMAT(LN.DATEUP,'yyyy-MM-dd') as _day, COUNT(LN.DATEUP) as _novels FROM LN " + 
				"WHERE LN.DATEUP BETWEEN ? AND ? GROUP BY FORMAT(LN.DATEUP,'yyyy-MM-dd')";	
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<String, Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ret.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
		}
		return ret;
	}
	
	public List<Pair<String, Integer>> statisticChapOverDays(String startDate, String endDate) throws SQLException{
		String query = "SELECT FORMAT(CHAP.DATEUP,'yyyy-MM-dd') as _day, COUNT(CHAP.DATEUP) as _chaps FROM CHAP " + 
				"WHERE CHAP.DATEUP BETWEEN ? AND ? GROUP BY FORMAT(CHAP.DATEUP,'yyyy-MM-dd')";	
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<String, Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ret.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
		}
		
		return ret;
	}
	
	public List<Pair<String, Integer>> statisticAccountOverDays(String startDate, String endDate) throws SQLException{
		String query = "SELECT FORMAT(ACCOUNT.DATECREATE,'yyyy-MM-dd') as _day, COUNT(ACCOUNT.DATECREATE) as _novels FROM ACCOUNT " + 
				"WHERE ACCOUNT.DATECREATE BETWEEN ? AND ? GROUP BY FORMAT(ACCOUNT.DATECREATE,'yyyy-MM-dd')";	
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<String, Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ret.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
		}
		
		return ret;
	}
	
	public List<Pair<String, Integer>> statisticThreadOverDays(String startDate, String endDate) throws SQLException{
		String query = "SELECT FORMAT(THREAD.DATEUP,'yyyy-MM-dd') as _day, COUNT(THREAD.DATEUP) as _novels FROM THREAD " + 
				"WHERE THREAD.DATEUP BETWEEN ? AND ? GROUP BY FORMAT(THREAD.DATEUP,'yyyy-MM-dd')";	
		PreparedStatement stmt = cnn.prepareStatement(query);
		List<Pair<String, Integer>> ret = new LinkedList<>();

		stmt.setString(1, startDate);
		stmt.setString(2, endDate);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString(1));
			ret.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
		}
		
		return ret;
	}
}
