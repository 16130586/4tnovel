package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;

public class GroupDAO {
	private Connection cnn;
	private AccountDAO accountDAO;
	
	public GroupDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public void insertGroup(Group group) {
		PreparedStatement stmt;
		String query = "INSERT INTO GROUPACC (NAME, DESCRIBE, DATECREATE, IDOWNER) VALUES (?, ?, ?, ?)";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, group.getName());
			stmt.setString(2, group.getDescription());
			stmt.setDate(3,(Date) group.getDateCreate());
			stmt.setInt(4, group.getOwner().getId());
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Insert group completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertMemberToAGroup(Account account, Group group) {
		PreparedStatement stmt;
		String query = "INSERT INTO JOININ (ID_ACC, ID_GROUP) VALUES (?, ?)";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, account.getId());
			stmt.setInt(2, group.getId());
			stmt.executeUpdate();
			
			System.out.println("Insert member completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Group getGroupByGroupName(String groupName) {
		accountDAO = new AccountDAO(cnn);
		Group group = null;
		PreparedStatement stmt;
		String query = "SELECT * FROM GROUPACC WHERE NAME = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, groupName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				group = new Group();
				group.setId(rs.getInt(1));
				group.setName(rs.getString(2));
				group.setDescription(rs.getString(3));
				group.setDateCreate(rs.getDate(4));
				group.setOwner(accountDAO.getAccountByID(rs.getInt(5)));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return group;
	}
	
	public void deleteGroup(Group group) {
		PreparedStatement stmt;
		String query = "DELETE FROM GROUPACC WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, group.getId());
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Delete group completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
