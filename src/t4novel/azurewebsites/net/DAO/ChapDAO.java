package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Vol;

public class ChapDAO {
	
	private Connection cnn;

	public ChapDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertChap(Chap chap) {
		PreparedStatement stmt;
		String querry = "INSERT INTO CHAP (ID_VOL, CONTENT, DATEUP) VALUES (?, ?, ?)";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chap.getVolOwnerId());
			stmt.setString(2, chap.getContent());
			stmt.setDate(3, (Date) chap.getDateUp());
			ResultSet rs = stmt.executeQuery();
			
			System.out.println("Insert chap completed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Chap getChapByID(int chapID) {
		Chap chap = null;
		PreparedStatement stmt;
		String querry = "SELECT * FROM CHAP WHERE ID = ?";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				chap = new Chap();
				chap.setId(rs.getInt(1));
				chap.setNovelOwnerId(rs.getInt(2));
				chap.setContent(rs.getString(3));
				chap.setDateUp(rs.getDate(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chap;
	}
	
	public List<Chap> getChapByVolID(int volID) {
		LinkedList<Chap> listChap = new LinkedList<>();
		PreparedStatement stmt;
		String querry = "SELECT * FROM CHAP WHERE ID_VOL = ?";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, volID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Chap chap = new Chap();
				chap.setId(rs.getInt(1));
				chap.setNovelOwnerId(rs.getInt(2));
				chap.setContent(rs.getString(3));
				chap.setDateUp(rs.getDate(4));
				listChap.add(chap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listChap;
	}
	
	public void deleteChapByID(int chapID) {
		PreparedStatement stmt;
		String querry = "DELETE FROM CHAP WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapID);
			stmt.executeQuery();
			
			System.out.println("Delete chap completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Chap> getAllChap(){
		LinkedList<Chap> listChap = new LinkedList<>();
		PreparedStatement stmt;
		String querry = "SELECT * FROM CHAP";
		
		try {
			stmt = cnn.prepareStatement(querry);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Chap chap = new Chap();
				chap.setId(rs.getInt(1));
				chap.setNovelOwnerId(rs.getInt(2));
				chap.setContent(rs.getString(3));
				chap.setDateUp(rs.getDate(4));
				listChap.add(chap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listChap;
	}
	
	public void updateChap(Chap chap) {
		PreparedStatement stmt;
		String query = "UPDATE CHAP set CONTENT = ? WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, chap.getContent());
			stmt.setInt(2, chap.getId());
			
			System.out.println("Update chap completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
