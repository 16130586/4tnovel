package t4novel.azurewebsites.net.DAOService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistedDisplayedNameCheckingService extends BaseDaoService implements DAOService {

	public ExistedDisplayedNameCheckingService(Connection databaseConnection) {
		super(databaseConnection);
	}

	@Override
	public boolean check(String data, String onQuery) {
		PreparedStatement stm = null;
		boolean isExisted = false;
		ResultSet rs = null;
		try {
			stm = cnn.prepareStatement(onQuery);
			stm.setString(1, data);
			rs = stm.executeQuery();
			isExisted = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isExisted;
	}
}
