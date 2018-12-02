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

	public void insertChap(Chap chap) throws Exception {
		PreparedStatement stmt = null;
		String querry = "INSERT INTO CHAP (ID_VOL, ID_NOVEL, CONTENT) VALUES (?, ?, ?)";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chap.getVolOwnerId());
			stmt.setInt(2, chap.getNovelOwnerId());
			stmt.setString(3, chap.getContent());
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Insert chap completed!");
			System.out.println("insert chap content: " + chap.getContent());
		} catch (SQLException e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public Chap getChapByID(int chapID) throws Exception {
		Chap chap = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT * FROM CHAP WHERE ID = ?";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				chap = new Chap();
				chap.setId(rs.getInt(1));
				chap.setVolOwnerId(rs.getInt(2));
				chap.setNovelOwnerId(rs.getInt(3));
				chap.setContent(rs.getString(4));
				chap.setDateUp(rs.getDate(5)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return chap;
	}

	public List<Chap> getChapsByVolID(int volID) throws Exception {
		LinkedList<Chap> listChap = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT * FROM CHAP WHERE ID_VOL = ?";

		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, volID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Chap chap = new Chap();
				chap.setId(rs.getInt(1));
				chap.setVolOwnerId(rs.getInt(2));
				chap.setNovelOwnerId(rs.getInt(3));
				chap.setContent(rs.getString(4));
				chap.setDateUp(rs.getDate(5)) ;  
				listChap.add(chap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return listChap;
	}

	public void deleteChapByID(int chapID) throws Exception {
		PreparedStatement stmt = null;
		String querry = "DELETE FROM CHAP WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapID);
			stmt.executeUpdate();
			stmt.close();
			cnn.commit();
			System.out.println("Delete chap completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateChap(Chap chap) throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE CHAP set CONTENT = ? WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, chap.getContent());
			stmt.setInt(2, chap.getId());
			stmt.executeUpdate();
			stmt.close();
			cnn.commit();
			System.out.println("Update chap completed");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}
}
