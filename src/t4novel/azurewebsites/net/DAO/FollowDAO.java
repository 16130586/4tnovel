package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FollowDAO {
	private Connection cnn;

	public FollowDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	public void subcribe(String stream, int targetId, int hostId) throws SQLException {
		String query = "INSERT INTO FOLLOW(TARGET_ID,HOST_ID,STREAM) VALUES(? , ? , ?)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, targetId);
		stmt.setInt(2, hostId);
		stmt.setString(3, stream);
		stmt.executeUpdate();
		stmt.close();

	}

	public void unSubcribe(String stream, int targetId, int hostId) throws SQLException {
		String query = "DELETE FROM FOLLOW WHERE TARGET_ID=? AND HOST_ID=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, targetId);
		stmt.setInt(2, hostId);
		stmt.executeUpdate();
		stmt.close();
	}

	public List<Integer> getNovelFollow(int accountId) throws SQLException {
		List<Integer> ids = new LinkedList<>();
		String query = "SELECT TARGET_ID FROM FOLLOW WHERE HOST_ID=? AND STREAM=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, accountId);
		stmt.setString(2, "novel");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ids.add(rs.getInt("TARGET_ID"));
		}
		for(Integer i : ids)
			System.out.println("account : " + accountId +  "  follows " + i);
		stmt.close();
		return ids;
	}

	public List<Integer> getFollowersId(int novelOwnerId) throws SQLException {
		List<Integer> ids = new LinkedList<>();
		String query = "SELECT HOST_ID FROM FOLLOW WHERE STREAM=? AND TARGET_ID=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setString(1, "novel");
		stmt.setInt(2, novelOwnerId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			ids.add(rs.getInt("HOST_ID"));
		return ids;
	}

}
