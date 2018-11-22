package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;

import t4novel.azurewebsites.net.models.Account;

public class AccountDAO {
	private Connection cnn;
	
	public AccountDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public void insertAccount(Account account) {
		
	}
	
	public Account getAccount() {
		return null;
	}
}
