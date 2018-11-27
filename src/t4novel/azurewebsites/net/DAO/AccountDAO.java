package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;

public class AccountDAO {
	private Connection cnn;
	private BookmarkFolderDAO bmFolderDAO;
	
	public AccountDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public void insertAccount(Account account) {
		PreparedStatement stmt;
		String query = "INSERT INTO ACCOUNT (DISPLAYEDNAME, USERNAME, PASSWORD, EMAIL, DATECREATE, ROLE, ISAUTO, ISBANNED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getUserName());
			stmt.setString(3, account.getPassword());
			stmt.setString(4, account.getGmail());
			stmt.setDate(5,(Date) account.getDateCreate());
			stmt.setInt(6, 1);
			stmt.setString(7, "NO");
			stmt.setString(8, "NO");
			stmt.executeQuery();
			
			System.out.println("Insert account completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateAccount(Account account) {
		PreparedStatement stmt;
		String query = "UPDATE ACCOUNT SET DISPLAYEDNAME = ?, PASSWORD = ?, ISAUTO = ?, ISBANNED = ? WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.isAutoPassPushlishment() ? "YES" : "NO");
			stmt.setString(4, account.isBanned() ? "YES" : "NO");
			stmt.executeUpdate();
			System.out.println("Update account completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Account getAccountByUsername(String username) {
		Account account = null;
		PreparedStatement stmt;
		String query = "SELECT * FROM ACCOUNT WHERE USERNAME = ?"; 
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
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
				//account.setBookMarkFolders(bmFolderDAO.getBookmarkFolders(account.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public Account getAccountByID(int accountID) {
		Account account = null;
		bmFolderDAO = new BookmarkFolderDAO(cnn);
		PreparedStatement stmt;
		String query = "SELECT * FROM ACCOUNT WHERE ID = ?"; 
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
			ResultSet rs = stmt.executeQuery();
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
				//account.setBookMarkFolders(bmFolderDAO.getBookmarkFolders(account.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public void deleteAccountByID(int AccountID) {
		PreparedStatement stmt;
		String query = "DELETE FROM ACCOUTN WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, AccountID);
			stmt.executeQuery();
			
			System.out.println("Delete account completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Account> getAllAccount() {
		LinkedList<Account> listAccount = new LinkedList<>();
		PreparedStatement stmt;
		String query = "SELECT * FROM ACCOUNT";
		
		try {
			stmt = cnn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt(1));
				account.setDisplayedName(rs.getString(2));
				account.setUserName(rs.getString(3));
				account.setPassword(rs.getString(4));
				account.setGmail(rs.getString(5));
				account.setDateCreate(rs.getDate(6));
				account.setRole(Role.getRole(rs.getInt(7)));
				account.setAutoPassPushlishment(rs.getString(8).equals("YES") ? true : false);
				account.setBanned(rs.getString(9).equals("YES") ? true : false);
				listAccount.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listAccount;
	}
}
