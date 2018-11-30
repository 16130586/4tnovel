package t4novel.azurewebsites.net.DAOService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailCheckingService extends BaseDaoService implements DAOService {

	public EmailCheckingService(Connection databaseConnection) {
		super(databaseConnection);
	}

	@Override
	public boolean check(String data, String onQuery) {
		PreparedStatement stmt = null;
		boolean isExisted = false;
		ResultSet rs = null;
		try {
			stmt = cnn.prepareStatement(onQuery);
			stmt.setString(1, data);
			rs = stmt.executeQuery();
			isExisted = rs.next();
		} catch (SQLException e) {
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

		return isExisted;
	}

	@Override
	public boolean check(String data1, String data2, String onQuery) {
		PreparedStatement stmt = null;
		boolean isExisted = false;
		ResultSet rs = null;
		try {
			stmt = cnn.prepareStatement(onQuery);
			stmt.setInt(1, Integer.parseInt(data1));
			stmt.setString(2, data2);
			rs = stmt.executeQuery();
			isExisted = rs.next();
		} catch (SQLException e) {
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

		return isExisted;
	}

}
