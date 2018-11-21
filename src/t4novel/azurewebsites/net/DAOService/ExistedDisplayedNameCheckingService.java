package t4novel.azurewebsites.net.DAOService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExistedDisplayedNameCheckingService extends BaseDaoService implements DAOService {
	private Connection cnn;
	
	public ExistedDisplayedNameCheckingService(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	@Override
	public boolean check(String data, String onQuery) {
		PreparedStatement sttm;
		boolean isExisted = false;
		try {
			sttm = cnn.prepareStatement(onQuery);
			sttm.setString(1, data);
			ResultSet rs = sttm.executeQuery();
			isExisted = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExisted;
	}
}
