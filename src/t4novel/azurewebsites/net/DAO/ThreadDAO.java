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

	public void insertThread(Thread thread) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO THREAD(TITLE, CONTENT, ID_OWNDER) VALUES (?, ?, ?)";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, thread.getTitle());
			stmt.setString(2, thread.getContent());
			stmt.setInt(3, thread.getAccountOwnerId());
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Insert thread completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public List<Thread> getThreadByUser(int userID) throws Exception {
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
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return listThread;
	}

	public List<Thread> getAllThread() throws Exception {
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
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return listThread;
	}

	public void updateThread(Thread thread) throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE THREAD SET TITLE = ?, CONTENT = ? WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, thread.getTitle());
			stmt.setString(2, thread.getContent());
			stmt.setInt(3, thread.getId());
			stmt.executeUpdate();
			stmt.close();
			cnn.commit();
			System.out.println("Update thread completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public Thread getThreadByID(int threadID) throws Exception {
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
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return thread;
	}

	public void deleteThreadByID(int threadID) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE FROM THREAD WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, threadID);
			stmt.executeUpdate();
			stmt.close();
			cnn.commit();
			System.out.println("Delete thread completed!");
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
