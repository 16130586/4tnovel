package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Message;

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
		stmt.close();
	}
	public List<Message> getAllMessageInInBox(int accountId) throws SQLException{
		List<Message> msges = new LinkedList<>();
		String query = "SELECT * FROM INBOX WHERE ACCID=? order by DATE DESC";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, accountId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Message msg = new Message();
			msg.setId(rs.getInt("ID"));
			msg.setContent(rs.getString("MESSAGE"));
			msg.setTime(rs.getDate("DATE"));
			msges.add(msg);
		}
		rs.close();
		stmt.close();
		return msges;
	}
	// SELECT * FROM INBOX WHERE ACCID = 1 order by date desc offset 5 rows fetch next 5 rows only
	public List<Message> getMessagesInInBox(int from, int limit , int accId) throws SQLException {
		String query = "SELECT * FROM INBOX WHERE ACCID=? order by DATE DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
		List<Message> msges = new LinkedList<>();
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, accId);
		stmt.setInt(2, from);
		stmt.setInt(3, limit);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Message msg = new Message();
			msg.setId(rs.getInt("ID"));
			msg.setContent(rs.getString("MESSAGE"));
			msg.setTime(rs.getDate("DATE"));
			msges.add(msg);
		}
		rs.close();
		stmt.close();
		return msges;
	}
}
