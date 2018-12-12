package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import t4novel.azurewebsites.net.models.Account;

public class LikeDAO {
	private Connection cnn;

	public LikeDAO(Connection cnn) {
		this.cnn = cnn;
	}

	public int like(Account ac, int targetId, String stream) throws SQLException {
		int newstLike = -1;
		String query = "INSERT INTO LIKING (ACC_ID , TARGET_ID , STREAM) values (? , ? , ?)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, ac.getId());
		stmt.setInt(2, targetId);
		stmt.setString(3, stream);
		stmt.executeUpdate();

		String getNewsLikeSql = "SELECT COUNT(ACC_ID) AS NEWEST_LIKE FROM LIKING WHERE TARGET_ID=? AND STREAM=?";
		stmt = cnn.prepareStatement(getNewsLikeSql);
		stmt.setInt(1, targetId);
		stmt.setString(2, stream);
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
			newstLike = rs.getInt("NEWEST_LIKE");
		rs.close();
		stmt.close();
		return newstLike;
	}

	public int unlike(Account ac, int targetId, String stream) throws SQLException {
		int newstLike = -1;
		String query = "DELETE FROM LIKING WHERE ACC_ID=? AND STREAM = ?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, ac.getId());
		stmt.setString(2, stream);
		stmt.executeUpdate();

		String getNewsLikeSql = "SELECT COUNT(ACC_ID) AS NEWEST_LIKE FROM LIKING WHERE TARGET_ID=? AND STREAM=?";
		stmt = cnn.prepareStatement(getNewsLikeSql);
		stmt.setInt(1, targetId);
		stmt.setString(2, stream);
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
			newstLike = rs.getInt("NEWEST_LIKE");
		rs.close();
		stmt.close();
		return newstLike;
	}

	public boolean isLike(Account acc, int novelId, String stream) throws SQLException {
		boolean isLike = false;
		String sql = "SELECT ACC_ID FROM LIKING WHERE ACC_ID=? AND TARGET_ID=? AND STREAM=?";
		PreparedStatement stmt = cnn.prepareStatement(sql);
		stmt.setInt(1, acc.getId());
		stmt.setInt(2, novelId);
		stmt.setString(3, stream);
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
			isLike = true;
		rs.close();
		stmt.close();
		return isLike;
	}

	public int getLike(int targetId, String stream) throws SQLException {
		int totalLike = -1;
		String query = "SELECT COUNT(ACC_ID) AS TOTAL_LIKE FROM LIKING WHERE TARGET_ID=? AND STREAM=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, targetId);
		stmt.setString(2, stream);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			totalLike = rs.getInt("TOTAL_LIKE");
		return totalLike;
	}

}
