package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NextIdGenrator {
	private static NextIdGenrator instance;

	private NextIdGenrator() {

	}

	public synchronized int nextAutoIncrementFromTable(String tableName, Connection cnn) {
		int result = -1;
		PreparedStatement stmt;
		String query = "SELECT MAX(ID) FROM ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, tableName);
			ResultSet rs = stmt.executeQuery();
			result = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static synchronized NextIdGenrator getGenrator() {
		if (instance == null)
			instance = new NextIdGenrator();
		return instance;
	}

}
