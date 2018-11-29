package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;

public class AccountDAO {
	private Connection cnn;
	
	public AccountDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public void insertAccount(Account account) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO ACCOUNT (DISPLAYEDNAME, USERNAME, PASSWORD, EMAIL, DATECREATE, ROLE, ISAUTO, ISBAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getUserName());
			stmt.setString(3, account.getPassword());
			stmt.setString(4, account.getGmail());
			stmt.setDate(5,(Date) account.getDateCreate());
			stmt.setInt(6, 2);
			stmt.setString(7, "NO");
			stmt.setString(8, "NO");
			stmt.executeUpdate();
			
			System.out.println("Insert account completed!");
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
	
	public void updateAccount(Account account) {
		PreparedStatement stmt = null;
		String query = "UPDATE ACCOUNT SET DISPLAYEDNAME = ?, PASSWORD = ?, ISAUTO = ?, ISBAN = ? WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.isAutoPassPushlishment() ? "YES" : "NO");
			stmt.setString(4, account.isBanned() ? "YES" : "NO");
			stmt.setInt(5, account.getId());
			stmt.executeUpdate();
			
			System.out.println("Update account completed!");
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
	
	public Account getAccountByUsername(String username) {
		Account account = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ACCOUNT WHERE USERNAME = ?"; 
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if(!rs.next()) return account;
			if (rs.getString(3) != null) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setDisplayedName(rs.getString(2));
				account.setUserName(rs.getString(3));
				account.setPassword(rs.getString(4));
				account.setGmail(rs.getString(5));
				account.setDateCreate(rs.getDate(6));
				account.setRole(Role.getRole(rs.getInt(7)));
				account.setAutoPassPushlishment(rs.getString(8).equals("YES") ? true : false);
				account.setBanned(rs.getString(9).equals("YES") ? true : false);
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
		
		return account;
	}
	
	public Account getAccountByID(int accountID) {
		Account account = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ACCOUNT WHERE ID = ?"; 
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
			rs = stmt.executeQuery();
			if(!rs.next()) return account;
			if (rs.getString(3) != null) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setDisplayedName(rs.getString(2));
				account.setUserName(rs.getString(3));
				account.setPassword(rs.getString(4));
				account.setGmail(rs.getString(5));
				account.setDateCreate(rs.getDate(6));
				account.setRole(Role.getRole(rs.getInt(7)));
				account.setAutoPassPushlishment(rs.getString(8).equals("YES") ? true : false);
				account.setBanned(rs.getString(9).equals("YES") ? true : false);
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
		
		return account;
	}
	
	public void deleteAccountByID(int AccountID) {
		PreparedStatement stmt = null;
		String query = "DELETE FROM ACCOUTN WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, AccountID);
			stmt.executeUpdate();
			
			System.out.println("Delete account completed!");
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
