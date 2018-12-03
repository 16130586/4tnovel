package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NextIdGenrator {
	private String tableName;
	public NextIdGenrator(String tableName) {
		this.tableName = tableName;
	}
	public synchronized int nextAutoIncrementId(Connection cnn) throws Exception {
		System.out.println("table name " + tableName);
		int result = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT MAX(ID) AS MAXID FROM " + tableName;
		try {
			stmt = cnn.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.next();
			result = rs.getInt("MAXID") + 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return result;
	}
}
