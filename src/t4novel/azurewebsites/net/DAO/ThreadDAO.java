package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Thread;

public class ThreadDAO {
	private Connection cnn;
	
	public ThreadDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public void insertThread(Thread thread) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO THREAD(TITLE, CONTENT, ID_OWNDER) VALUES (?, ?, ?)";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, thread.getTitle());
			stmt.setString(2, thread.getContent());
			stmt.setInt(3, thread.getAccountOwnerId());
			stmt.executeUpdate();
			
			System.out.println("Insert thread completed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Thread> getThreadByUser(int userID) {
		LinkedList<Thread> listThread = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM THREAD WHERE ID_OWNER = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, userID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Thread thread = new Thread();
				thread.setId(rs.getInt(1));
				thread.setTitle(rs.getString(2));
				thread.setContent(rs.getString(3));
				thread.setAccountOwnerId(rs.getInt(4));
				listThread.add(thread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listThread;
	}
	
	public List<Thread> getAllThread() {
		LinkedList<Thread> listThread = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM THREAD";
		
		try {
			stmt = cnn.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Thread thread = new Thread();
				thread.setId(rs.getInt(1));
				thread.setTitle(rs.getString(2));
				thread.setContent(rs.getString(3));
				thread.setAccountOwnerId(rs.getInt(4));
				listThread.add(thread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listThread;
	}
	
	public void updateThread(Thread thread) {
		PreparedStatement stmt = null;
		String query = "UPDATE THREAD SET TITLE = ?, CONTENT = ? WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, thread.getTitle());
			stmt.setString(2, thread.getContent());
			stmt.setInt(3, thread.getId());
			stmt.executeUpdate();
			
			System.out.println("Update thread completed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Thread getThreadByID(int threadID) {
		Thread thread = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM THREAD WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, threadID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				thread = new Thread();
				thread.setId(rs.getInt(1));
				thread.setTitle(rs.getString(2));
				thread.setContent(rs.getString(3));
				thread.setAccountOwnerId(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return thread;
	}
	
	public void deleteThreadByID(int threadID) {
		PreparedStatement stmt = null;
		String query = "DELETE FROM THREAD WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, threadID);
			stmt.executeUpdate();
			
			System.out.println("Delete thread completed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
