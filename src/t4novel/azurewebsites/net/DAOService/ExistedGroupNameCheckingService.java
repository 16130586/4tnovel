package t4novel.azurewebsites.net.DAOService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistedGroupNameCheckingService extends BaseDaoService implements DAOService {

	public ExistedGroupNameCheckingService(Connection databaseConnection) {
		super(databaseConnection);
	}

	@Override
	public boolean check(String data, String onQuery) {
		PreparedStatement sttm = null;
		boolean isExisted = false;
		ResultSet rs = null;
		try {
			sttm = cnn.prepareStatement(onQuery);
			sttm.setString(1, data);
			rs = sttm.executeQuery();
			isExisted = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sttm != null)
					sttm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return isExisted;
	}
}
