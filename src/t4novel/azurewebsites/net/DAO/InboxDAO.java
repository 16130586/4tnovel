package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InboxDAO {
	private Connection cnn;

	public InboxDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}
	public void addNewMessage(int userId , String message) throws SQLException {
		String query = "INSERT INTO INBOX (ACCID,MESSAGE) VALUES(? , ?)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, userId);
		stmt.setString(2, message);
		stmt.executeUpdate();
	}
}
