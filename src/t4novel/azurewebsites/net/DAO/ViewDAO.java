package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ViewDAO {
	private Connection cnn;
	private final String insertQuery = "INSERT INTO VIEWING (ACCID,STREAM,TARGET_ID) VALUES (?,?,?)";

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

	public List<Integer> getTopViewNovelsId(int day, int offset, int limit) throws SQLException {
		List<Integer> result = new LinkedList<>();
		String query = "select target_id, count(target_id) as [view] from viewing where stream ='novel' and viewdate > DATEADD(DAY, -?, getdate() AT TIME ZONE 'SE Asia Standard Time') group by target_id order by [view] desc offset ? rows fetch next ? rows only";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, day);
		stmt.setInt(2, offset);
		stmt.setInt(3, limit);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			result.add(rs.getInt("TARGET_ID"));
		}
		rs.close();
		stmt.close();
		return result;
	}
	

}
