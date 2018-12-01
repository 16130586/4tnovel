package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NextIdGenrator {
	private static NextIdGenrator instance;

	private NextIdGenrator() {

	}

	public int nextAutoIncrementFromTable(String tableName, Connection cnn) throws Exception {
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

	public static synchronized NextIdGenrator getGenrator() {
		if (instance == null)
			instance = new NextIdGenrator();
		return instance;
	}

}
