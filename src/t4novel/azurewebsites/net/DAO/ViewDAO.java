package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import t4novel.azurewebsites.net.models.Novel;

public class ViewDAO {
	private Connection cnn;
	private final String insertQuery = "INSERT INTO VIEWING (ACCID,STREAM,TARGET_ID) VALUES (?,?,?)";
	private static Map<Integer, LocalDate> lastMakeStatisticQueryMap = new HashMap<>();
	private static Map<Integer, List<Novel>> statisticNovelByDateDiffMap = new HashMap<>();

	public ViewDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	public void inserNewView(int accId, String stream, int targetId) throws SQLException {
		PreparedStatement stmt = cnn.prepareStatement(insertQuery);
		stmt.setInt(1, accId);
		stmt.setString(2, stream);
		stmt.setInt(3, targetId);
		stmt.executeUpdate();
		stmt.close();
	}

	public List<Novel> getTopViewNovels(int day, int offset, int limit, NovelDAO novelDao) throws Exception {
		LocalDate lastMakeStatisticQuery = lastMakeStatisticQueryMap.get(day);
		if (lastMakeStatisticQuery == null)
			lastMakeStatisticQuery = LocalDate.of(1990, 1, 1);
		LocalDate now = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
		List<Novel> result = null;
		LocalDate nextQueryMakeDate = lastMakeStatisticQuery.plusDays(day);
		if (nextQueryMakeDate.compareTo(now) <= 0) {
			result = new LinkedList<>();
			String query = "select target_id, count(target_id) as [view] from viewing where stream ='novel' and viewdate > DATEADD(DAY, -?, getdate() AT TIME ZONE 'SE Asia Standard Time') group by target_id order by [view] desc offset ? rows fetch next ? rows only";
			PreparedStatement stmt = cnn.prepareStatement(query);
			stmt.setInt(1, day);
			stmt.setInt(2, offset);
			stmt.setInt(3, limit);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result.add(novelDao.getNovelById(rs.getInt("TARGET_ID")));
			}
			rs.close();
			stmt.close();
			lastMakeStatisticQuery = now;
			statisticNovelByDateDiffMap.put(day, result);
			lastMakeStatisticQueryMap.put(day, lastMakeStatisticQuery);
			System.out.println("reload or exprie on query day: " + day);
		} else {
			result = statisticNovelByDateDiffMap.get(day);
			System.out.println("cache hit on query day: " + day);
			return result;
		}

		return result == null ? new LinkedList<>() : result;
	}

}
