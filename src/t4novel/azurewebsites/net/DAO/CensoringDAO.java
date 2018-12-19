package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CensoringDAO {
	private Connection cnn;

	public CensoringDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	public void insertCensor(String stream, int targetID, int ownerID) throws SQLException {
		String query = "INSERT INTO CENSORING(TARGET_ID,OWNER_ID,STREAM,IS_PUBLISHED) VALUES(? , ? , ?, ?)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, targetID);
		stmt.setInt(2, ownerID);
		stmt.setString(3, stream);
		stmt.setBoolean(4, false);
		stmt.executeUpdate();
		stmt.close();
	}

	public void deleteCensor(int targetID, int ownerID) throws SQLException {
		String query = "DELETE FROM CENSORING WHERE TARGET_ID=? AND HOST_ID=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, targetID);
		stmt.setInt(2, ownerID);
		stmt.executeUpdate();
		stmt.close();
	}

	public void published(int targetID, int ownerID) throws SQLException {
		String query = "UPDATE CENSORING SET IS_PUBLISHED=? WHERE TARGET_ID=? AND HOST_ID=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setBoolean(1, true);
		stmt.setInt(2, targetID);
		stmt.setInt(3, ownerID);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void unPublished(int targetID, int ownerID) throws SQLException {
		String query = "UPDATE CENSORING SET IS_PUBLISHED=? WHERE TARGET_ID=? AND HOST_ID=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setBoolean(1, false);
		stmt.setInt(2, targetID);
		stmt.setInt(3, ownerID);
		stmt.executeUpdate();
		stmt.close();
	}
}
