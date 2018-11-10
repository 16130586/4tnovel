package t4novel.azurewebsites.net.acessviasocial.DAOService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import t4novel.azurewebsites.net.acessviasocial.DAOSUtils.DAOUtils;

public class EmailCheckingService implements DAOService {
	private DAOUtils daoUtils;

	public EmailCheckingService(DAOUtils utils) {
		this.daoUtils = utils;
	}

	@Override
	public boolean check(String data, String onQuery) {
		Connection conn = daoUtils.getConnection();
		PreparedStatement stmt;
		boolean isExisted = false;
		try {
			stmt = conn.prepareStatement(onQuery);
			stmt.setString(1, data);
			ResultSet rs = stmt.executeQuery();
			isExisted = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			daoUtils.close(conn);
		}

		return isExisted;
	}

}
