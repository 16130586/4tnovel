package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;

public class AccountDAO {
	private Connection cnn;
	private BookmarkFolderDAO bmFolderDAO;
	
	public AccountDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public void insertAccount(Account account) {
		
	}
	
	public Account getAccount(String username) {
		Account account = null;
		bmFolderDAO = new BookmarkFolderDAO(cnn);
		PreparedStatement stmt;
		String query = "SELECT * FROM ACCOUNT WHERE USERNAME = ?"; 
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()) return null;
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
				System.out.println(account.isAutoPassPushlishment());
				account.setBanned(rs.getString(9).equals("YES") ? true : false);
				System.out.println(account.isBanned());
				account.setBookMarkFolders(bmFolderDAO.getBookmarkFolders(account.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}
}
