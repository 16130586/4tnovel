package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Vol;

public class ChapDAO {
	
	private Connection cnn;

	public ChapDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertChapter(Chap chap) {
		PreparedStatement stmt;
		String querry = "INSERT INTO CHAP (ID, ID_VOL, CONTENT, DATEUP) VALUES (?, ?, ?, ?)";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chap.getId());
			stmt.setInt(2, chap.getVolOwnerId());
			stmt.setString(3, chap.getContent());
			stmt.setDate(4, (Date) chap.getDateUp());
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
