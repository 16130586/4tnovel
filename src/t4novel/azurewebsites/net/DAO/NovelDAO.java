package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Novel;

public class NovelDAO {
	private Connection cnn;

	public NovelDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	
	public List<Novel> getNovelsFromUser(String accountId) {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp = new Novel();
		String query = "select * from LN where IDOWNER = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, accountId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				tmp.setId(rs.getInt(1));
				tmp.setName(rs.getString(2));
				tmp.setDescription(rs.getString(3));
				tmp.setAccountOwnerId(rs.getInt(4));
				result.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Novel> getNovelsByName(String name) {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp = new Novel();
		String query = "select * from LN where NAME like %?%";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while(rs.next()) {
				tmp.setId(rs.getInt(1));
				tmp.setName(rs.getString(2));
				tmp.setDescription(rs.getString(3));
				tmp.setAccountOwnerId(rs.getInt(4));
				result.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Novel> getAllNovel() {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp = new Novel();
		String query = "select * from LN";
		try {
			stmt = cnn.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				tmp.setId(rs.getInt(1));
				tmp.setName(rs.getString(2));
				tmp.setDescription(rs.getString(3));
				tmp.setAccountOwnerId(rs.getInt(4));
				result.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void delNovelById(String idNovel) {
		PreparedStatement stmt;
		String query = "delete from LN where ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(idNovel));
			stmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateNovel(Novel novel) {
		PreparedStatement stmt;
		String query = "update LN set NAME = ?, DESCRIBE = ? where ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setInt(3, novel.getId());
			stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
