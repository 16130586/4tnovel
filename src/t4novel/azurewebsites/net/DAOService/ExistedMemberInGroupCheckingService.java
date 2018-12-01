package t4novel.azurewebsites.net.DAOService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExistedMemberInGroupCheckingService extends BaseDaoService implements DAOService {

	public ExistedMemberInGroupCheckingService(Connection cnn) {
		super(cnn);
	}

	@Override
	public boolean check(String idAcc, String idGroup, String onQuery) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println("Checking");
		
		try {
			stmt = cnn.prepareStatement(onQuery);
			stmt.setInt(1, Integer.parseInt(idAcc));
			stmt.setInt(2, Integer.parseInt(idGroup));
			rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
}
