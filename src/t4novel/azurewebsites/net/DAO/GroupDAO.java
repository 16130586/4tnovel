package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;

public class GroupDAO {
	private Connection cnn;

	public GroupDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertGroup(Group group) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO GROUPACC (NAME, DESCRIBE, DATECREATE, IDOWNER) VALUES (?, ?, ?, ?)";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, group.getName());
			stmt.setString(2, group.getDescription());
			stmt.setDate(3, (Date) group.getDateCreate());
			stmt.setInt(4, group.getOwner().getId());
			stmt.executeUpdate();

			System.out.println("Insert group completed!");
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

	public void insertMemberToAGroup(Account account, Group group) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO JOININ (ID_ACC, ID_GROUP) VALUES (?, ?)";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, account.getId());
			stmt.setInt(2, group.getId());
			stmt.executeUpdate();

			System.out.println("Insert member completed!");
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

	public void deleteGroup(Group group) {
		PreparedStatement stmt = null;
		String query = "DELETE FROM GROUPACC WHERE ID = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, group.getId());
			stmt.executeUpdate();

			System.out.println("Delete group completed");
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

	public int getNextID() {
		NextIdGenrator genrator = NextIdGenrator.getGenrator();
		return genrator.nextAutoIncrementFromTable("GROUP", cnn);
	}
}
