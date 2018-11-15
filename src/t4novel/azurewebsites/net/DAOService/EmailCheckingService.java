package t4novel.azurewebsites.net.DAOService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmailCheckingService extends BaseDaoService implements DAOService {
	private Connection cnn;

	public EmailCheckingService(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	@Override
	public boolean check(String data, String onQuery) {
		PreparedStatement stmt;
		boolean isExisted = false;
		try {
			stmt = cnn.prepareStatement(onQuery);
			stmt.setString(1, data);
			ResultSet rs = stmt.executeQuery();
			isExisted = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isExisted;
	}

}
