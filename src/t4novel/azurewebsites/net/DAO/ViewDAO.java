package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewDAO {
	private Connection cnn;
	private final String insertQuery = "INSERT INTO VIEWING (ACCID,STREAM,TARGET_ID) VALUES (?,?,?)";

	public ViewDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	public void inserNewView(int accId, String stream, int targetId) throws SQLException {
		PreparedStatement stmt = cnn.prepareStatement(insertQuery);
		stmt.setInt(1, accId);
		stmt.setString(2, stream);
		stmt.setInt(3, targetId);
		stmt.executeUpdate();
		stmt.close();
	}


	

}
