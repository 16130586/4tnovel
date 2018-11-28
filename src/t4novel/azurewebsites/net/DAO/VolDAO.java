package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Vol;

public class VolDAO {

	private Connection cnn;

	public VolDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertVol(Vol vol) {
		PreparedStatement stmt;
		String query = "INSERT INTO VOL (ID_LN, TITLE, DESCRIBE, DATEUP) VALUES (?, ?, ?, ?)";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, vol.getNovelOwnerId());
			stmt.setString(2, vol.getTitle());
			stmt.setString(3, vol.getDescription());
			stmt.setDate(4, (Date) vol.getDateUp());
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Insert vol completed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vol getVolByID(int volID) {
		Vol vol = null;
		PreparedStatement stmt;
		String query = "SELECT * FROM VOL WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, volID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				vol = new Vol();
				vol.setId(rs.getInt(1));
				vol.setNovelOwnerId(rs.getInt(2));
				vol.setTitle(rs.getString(3));
				vol.setDescription(rs.getString(4));
				vol.setDateUp(rs.getDate(5));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vol;
	}

	public List<Vol> getVolsOfNovel(int novelID) {
		LinkedList<Vol> listVol = new LinkedList<>();
		PreparedStatement stmt;
		String query = "SELECT * FROM VOL WHERE ID_LN = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, novelID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Vol vol = new Vol();
				vol.setId(rs.getInt(1));
				vol.setNovelOwnerId(rs.getInt(2));
				vol.setTitle(rs.getString(3));
				vol.setDescription(rs.getString(4));
				vol.setDateUp(rs.getDate(5));
				listVol.add(vol);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listVol;
	}
	
	public void updateVol(Vol vol) {
		PreparedStatement stmt;
		String query = "UPDATE VOL SET TITLE = ?, DESCRIBE = ? WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, vol.getTitle());
			stmt.setString(2, vol.getDescription());
			stmt.setInt(3, vol.getId());
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Update vol completed!");
		} catch (Exception e) {
		}
	}
	
	public void deleteVolByID(int volID) {
		PreparedStatement stmt;
		String query = "DELETE FROM VOL WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, volID);
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Delete vol completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteVolsOfNovel(int novelID) {
		PreparedStatement stmt;
		String query = "DELETE FROM VOL WHERE ID_LN = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, novelID);
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Delete vol completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
