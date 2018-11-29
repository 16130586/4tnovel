package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NextIdGenrator {
	private static NextIdGenrator instance;

	private NextIdGenrator() {

	}

	public int nextAutoIncrementFromTable(String tableName, Connection cnn) {
		int result = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT MAX(ID) FROM ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, tableName);
			rs = stmt.executeQuery();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	public static synchronized NextIdGenrator getGenrator() {
		if (instance == null)
			instance = new NextIdGenrator();
		return instance;
	}

}
