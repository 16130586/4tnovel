package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;

public class GroupDAO {
	private Connection cnn;
	private static final NextIdGenrator NEXT_ID_GENRATOR;
	static {
		NEXT_ID_GENRATOR = new NextIdGenrator("GROUPACC");
	}

	public GroupDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertGroup(Group group) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO GROUPACC (NAME, DESCRIBE , IDOWNER) VALUES (?, ?, ?)";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, group.getName());
			stmt.setString(2, group.getDescription());
			stmt.setInt(3, group.getOwner().getId());
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Insert group completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public List<Account> getAllMemberFromGroup(int groupID) throws Exception {
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
				System.out.println(account.getId() + " in group " + groupID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null)
				stmt.close();
		}
		return listAccount;
	}

	public List<Group> getJoinGroups(int accountID) throws Exception {
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
			if (stmt != null)
				stmt.close();
		}
		return listGroup;
	}

	public void insertMemberToGroup(Account account, Group group) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO JOININ (ID_ACC, ID_GROUP) VALUES (?, ?)";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, account.getId());
			stmt.setInt(2, group.getId());
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Insert member completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void removeMemberFromGroup(Account account, Group group) throws SQLException {
		PreparedStatement stmt = null;
		String query = "DELETE FROM JOININ WHERE ID_ACC = ? AND ID_GROUP = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, account.getId());
			stmt.setInt(2, group.getId());
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Remove member completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteGroupsByGroupOwnerID(int groupOwnerID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT ID FROM GROUPACC WHERE IDOWNER = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, groupOwnerID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int groupID = rs.getInt(1);
				removeAllMemberFromGroup(groupID);
				deleteGroupByID(groupID);
			}
			System.out.println("Delete groups by owner completed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public void removeAllMemberFromGroup(int groupID) throws SQLException {
		PreparedStatement stmt = null;
		String query = "DELETE FROM JOININ WHERE ID_GROUP = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, groupID);
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Remove all member completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public boolean checkMemberExistInGroup(int accountID, int groupID) throws Exception {
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
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return false;
	}

	public void deleteGroupByID(int groupID) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE FROM GROUPACC WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, groupID);
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Delete group completed");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public Group getGroup(int idGroup) throws SQLException {
		Group result = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT NAME, DESCRIBE , DATECREATE, IDOWNER FROM GROUPACC WHERE ID = ?";

		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idGroup);
		rs = stmt.executeQuery();
		if (rs.next()) {
			result = new Group();
			result.setId(idGroup);
			result.setName(rs.getString("NAME"));
			result.setDateCreate(rs.getDate("DATECREATE"));
			result.setOwnerId(rs.getInt("IDOWNER"));
		}
		return result;
	}

	public int getNextID() throws Exception {
		return NEXT_ID_GENRATOR.nextAutoIncrementId(cnn);
	}
}
