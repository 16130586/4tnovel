package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import t4novel.azurewebsites.net.models.Novel;

public class NovelDAO {
	private Connection cnn;

	public NovelDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertNovel(Novel novel) {
		PreparedStatement stmt;
		String querry = "INSERT INTO LN (ID, NAME, DESCRIBE, DATEUP, IDOWNER) VALUES (?, ?, ?, ?, ?)";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, novel.getId());
			stmt.setString(2, novel.getName());
			stmt.setString(3, novel.getDescription());
			stmt.setDate(4, (Date) novel.getDateUp());
			stmt.setInt(5, novel.getAccountOwnerId());
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
