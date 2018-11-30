package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;

public class GroupDAO {
	private Connection cnn;

	public GroupDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertGroup(Group group) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO GROUPACC (NAME, DESCRIBE , IDOWNER) VALUES (?, ?, ?)";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, group.getName());
			stmt.setString(2, group.getDescription());
			stmt.setInt(3, group.getOwner().getId());
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
	public List<Account> getAllMemberFromGroup(int groupID){
		LinkedList<Account> listAccount = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM JOININ WHERE ID_GROUP = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, groupID);
			rs = stmt.executeQuery();
			System.out.println("on loading all member of group " + groupID);
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt(1));
				listAccount.add(account);
				System.out.println(account.getId() + " in group "  + groupID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs !=null) {
					rs.close();
				}
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listAccount;
	}
	
	public List<Group> getJoinGroups(int accountID){
		LinkedList<Group> listGroup = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT ID_GROUP, NAME, IDOWNER FROM GROUPACC INNER JOIN JOININ ON GROUPACC.ID = JOININ.ID_GROUP WHERE ID_ACC = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Group group = new Group();
				group.setId(rs.getInt(1));
				group.setName(rs.getString(2));
				group.setOwner(new Account(rs.getInt(3)));
				listGroup.add(group);
			}
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
		return listGroup;
	}

	public void insertMemberToGroup(Account account, Group group) {
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
	
	public boolean checkMemberExistInGroup(int accountID, int groupID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM JOININ WHERE ID_ACC = ? AND ID_GROUP = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
			stmt.setInt(2, groupID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Check existed completed");
				return true;
			}
			System.out.println("Check existed completed");
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
		return false;
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
		return genrator.nextAutoIncrementFromTable("GROUPACC", cnn);
	}
}
