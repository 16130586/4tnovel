package t4novel.azurewebsites.net.censoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import t4novel.azurewebsites.net.DAO.ChapDAO;

public class CensorEntityFactory {
	private static CensorEntityFactory instance;

	private CensorEntityFactory() {
	}

	public CensorEntity create(ResultSet rs, Connection cnn) throws SQLException {
		CensorEntity ret = null;
		String stream = rs.getString("STREAM").trim();
		if ("chapter".equals(stream)) {
			try {
				ret = new ChapDAO(cnn).getChapByID(rs.getInt("TARGET_ID"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ret.loadData(rs);
		}
		return ret;
	}

	public static synchronized CensorEntityFactory getInstance() {
		if (instance == null) {
			instance = new CensorEntityFactory();
		}
		return instance;
	}
}
