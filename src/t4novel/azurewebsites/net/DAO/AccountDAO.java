package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.collections4.map.LRUMap;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;

public class AccountDAO {
	private Connection cnn;
	private static final NextIdGenrator NEXT_ID_GENRATOR;
	private static final Map<Integer , Account> ACCOUNTS_CACHE;
	static {
		NEXT_ID_GENRATOR = new NextIdGenrator("ACCOUNT");
		ACCOUNTS_CACHE = Collections.synchronizedMap(new LRUMap<Integer , Account> (10, 5 , true));
	}
	public AccountDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertAccount(Account account) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO ACCOUNT (DISPLAYEDNAME, USERNAME, PASSWORD, EMAIL, ROLE, ISAUTO, ISBAN) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getUserName());
			stmt.setString(3, account.getPassword());
			stmt.setString(4, account.getGmail());
			stmt.setInt(5, account.getRole().getIntValue());
			stmt.setString(6, "NO");
			stmt.setString(7, "NO");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateAccount(Account account) throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE ACCOUNT SET DISPLAYEDNAME = ?, PASSWORD = ?, EMAIL= ?,ISAUTO = ?, ISBAN = ? WHERE ID = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getGmail());
			stmt.setString(4, account.isAutoPassPushlishment() ? "YES" : "NO");
			stmt.setString(5, account.isBanned() ? "YES" : "NO");
			stmt.setInt(6, account.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			cnn.setAutoCommit(true);
		}
	}

	public Account getAccountByUsername(String username) throws Exception {
		Account account = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ACCOUNT WHERE USERNAME = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if (!rs.next())
				return account;
			if (rs.getString(3) != null) {
				account = ACCOUNTS_CACHE.get(rs.getInt("ID"));
				if(account != null) {return account;}
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
				ACCOUNTS_CACHE.put(account.getId(), account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return account;
	}

	public Account getAccountByNickname(String nickname) throws Exception {
		Account account = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ACCOUNT WHERE DISPLAYEDNAME = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, nickname);
			rs = stmt.executeQuery();
			if (!rs.next())
				return account;
			if (rs.getString(3) != null) {
				account = ACCOUNTS_CACHE.get(rs.getInt("ID"));
				if(account != null) {return account;}
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
				ACCOUNTS_CACHE.put(account.getId(), account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return account;
	}

	public Account getAccountByEmail(String email) throws Exception {
		Account account = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ACCOUNT WHERE EMAIL = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if (!rs.next())
				return account;
			if (rs.getString(3) != null) {
				account = ACCOUNTS_CACHE.get(rs.getInt("ID"));
				if(account != null) {return account;}
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
				ACCOUNTS_CACHE.put(account.getId(), account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return account;
	}

	public Account getAccountByID(int accountID) throws Exception {
		Account account = ACCOUNTS_CACHE.get(accountID);
		if(account != null) { return account;}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ACCOUNT WHERE ID = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
			rs = stmt.executeQuery();
			if (!rs.next())
				return account;
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
				ACCOUNTS_CACHE.put(account.getId(), account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return account;
	}

	public void deleteAccountByID(int AccountID) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE FROM ACCOUTN WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, AccountID);
			stmt.executeUpdate();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			cnn.setAutoCommit(true);
		}
	}

	public int getNextID() throws Exception {
		return NEXT_ID_GENRATOR.nextAutoIncrementId(cnn);
	}
}
