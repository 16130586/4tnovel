package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Thread;

public class ThreadDAO {
	private Connection cnn;
	
	public ThreadDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public List<Thread> getThread(int userID) {
		//de yen cho t dm :)
		LinkedList<Thread> listThread = new LinkedList<>();
		PreparedStatement stmt;
		String query = "SELECT * FROM THREAD WHERE ID_OWNER = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Thread thread = new Thread();
				thread.setId(rs.getInt(1));
				thread.setTitle(rs.getString(2));
				thread.setAccountOwnerId(rs.getInt(3));
				listThread.add(thread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listThread;
	}
}
