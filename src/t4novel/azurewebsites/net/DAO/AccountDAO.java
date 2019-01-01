package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.sercurities.Role;

public class AccountDAO {
	private Connection cnn;
	private static final NextIdGenrator NEXT_ID_GENRATOR;
//	private static final Map<Integer, Account> ACCOUNTS_CACHE;
	static {
		NEXT_ID_GENRATOR = new NextIdGenrator("ACCOUNT");
//		ACCOUNTS_CACHE = Collections.synchronizedMap(new LRUMap<Integer, Account>(10, 5, true));
	}

	public AccountDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertAccount(Account account) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO ACCOUNT (DISPLAYEDNAME, USERNAME, PASSWORD, EMAIL, ROLE, ISAUTO, ISPIN, ISBAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getUserName());
			stmt.setString(3, account.getPassword());
			stmt.setString(4, account.getGmail());
			stmt.setInt(5, account.getRole().getIntValue());
			stmt.setString(6, account.isAutoPassPushlishment() ? "YES" : "NO");
			stmt.setString(7, account.isPin() ? "YES" : "NO");
			stmt.setString(8, account.isBan() ? "YES" : "NO");
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
		String query = "UPDATE ACCOUNT SET DISPLAYEDNAME = ?, PASSWORD = ?, EMAIL= ?, ISAUTO = ?, ISPIN = ?, ISBAN = ?, ROLE = ? WHERE ID = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, account.getDisplayedName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getGmail());
			stmt.setString(4, account.isAutoPassPushlishment() ? "YES" : "NO");
			stmt.setString(5, account.isPin() ? "YES" : "NO");
			stmt.setString(6, account.isBan() ? "YES" : "NO");
			stmt.setInt(7, account.getRole().getIntValue());
			stmt.setInt(8, account.getId());
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
//				account = ACCOUNTS_CACHE.get(rs.getInt("ID"));
//				if (account != null) {
//					return account;
//				}
				account = new Account();
				account.setId(rs.getInt(1));
				account.setDisplayedName(rs.getString(2));
				account.setUserName(rs.getString(3));
				account.setPassword(rs.getString(4));
				account.setGmail(rs.getString(5));
				account.setDateCreate(rs.getTimestamp(6));
				account.setRole(Role.getRole(rs.getInt(7)));
				account.setAutoPassPushlishment(rs.getString(8).equals("YES") ? true : false);
				account.setPin(rs.getString(9).equals("YES") ? true : false);
				account.setBan(rs.getString(10).equals("YES") ? true : false);
//				ACCOUNTS_CACHE.put(account.getId(), account);
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
//				account = ACCOUNTS_CACHE.get(rs.getInt("ID"));
//				if (account != null) {
//					return account;
//				}
				account = new Account();
				account.setId(rs.getInt(1));
				account.setDisplayedName(rs.getString(2));
				account.setUserName(rs.getString(3));
				account.setPassword(rs.getString(4));
				account.setGmail(rs.getString(5));
				account.setDateCreate(rs.getTimestamp(6));
				account.setRole(Role.getRole(rs.getInt(7)));
				account.setAutoPassPushlishment(rs.getString(8).equals("YES") ? true : false);
				account.setPin(rs.getString(9).equals("YES") ? true : false);
				account.setBan(rs.getString(10).equals("YES") ? true : false);
//				ACCOUNTS_CACHE.put(account.getId(), account);
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
//				account = ACCOUNTS_CACHE.get(rs.getInt("ID"));
//				if (account != null) {
//					return account;
//				}
				account = new Account();
				account.setId(rs.getInt(1));
				account.setDisplayedName(rs.getString(2));
				account.setUserName(rs.getString(3));
				account.setPassword(rs.getString(4));
				account.setGmail(rs.getString(5));
				account.setDateCreate(rs.getTimestamp(6));
				account.setRole(Role.getRole(rs.getInt(7)));
				account.setAutoPassPushlishment(rs.getString(8).equals("YES") ? true : false);
				account.setPin(rs.getString(9).equals("YES") ? true : false);
				account.setBan(rs.getString(10).equals("YES") ? true : false);
//				ACCOUNTS_CACHE.put(account.getId(), account);
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
//		Account account = ACCOUNTS_CACHE.get(accountID);
//		if (account != null) {
//			return account;
//		}
		Account account = null;
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
				account.setDateCreate(rs.getTimestamp(6));
				account.setRole(Role.getRole(rs.getInt(7)));
				account.setAutoPassPushlishment(rs.getString(8).equals("YES") ? true : false);
				account.setPin(rs.getString(9).equals("YES") ? true : false);
				account.setBan(rs.getString(10).equals("YES") ? true : false);
//				ACCOUNTS_CACHE.put(account.getId(), account);
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

	public void deleteAccount(Account account, NovelDAO novelDAO, GroupDAO groupDAO, CensoringDAO censoringDAO)
			throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE ACCOUNT SET ISBAN = 'YES', ISPIN = 'NO', ISAUTO = 'NO' WHERE ID = ?";

		account.setOwnNovels(novelDAO.getNovelsByUserId(account.getId()));
		for (Novel novel : account.getOwnNovels()) {
			novelDAO.delNovelById(novel.getId(), new VolDAO(cnn), new ChapDAO(cnn), new GenreDAO(cnn));
		}
		account.setJoinGroup(groupDAO.getJoinGroups(account.getId()));
		for (Group group : account.getJoinGroup()) {
			groupDAO.removeMemberFromGroup(account, group);
		}
		groupDAO.deleteGroupsByGroupOwnerID(account.getId());

		censoringDAO.unCensoringAllNovelByAccountID(account.getId());
		
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, account.getId());
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

	public void banAccountByID(int accountID) throws SQLException {
		PreparedStatement stmt = null;
		String query = "UPDATE ACCOUNT SET ISBAN = 'YES' WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
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

	public void unBanAccountByID(int accountID) throws SQLException {
		PreparedStatement stmt = null;
		String query = "UPDATE ACCOUNT SET ISBAN = 'NO' WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
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

	public void grantPinByID(int accountID) throws SQLException {
		PreparedStatement stmt = null;
		String query = "UPDATE ACCOUNT SET ISPIN = 'YES' WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
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

	public void unPinByID(int accountID) throws SQLException {
		PreparedStatement stmt = null;
		String query = "UPDATE ACCOUNT SET ISPIN = 'NO' WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountID);
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

	public java.util.List<Account> getBasicInformationAccounts() throws SQLException {
		List<Account> accounts = new LinkedList<>();
		String query = "SELECT * FROM ACCOUNT";
		PreparedStatement stmt = cnn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		Account loader = null;
		while (rs.next()) {
			loader = new Account();
			loader.setId(rs.getInt("ID"));
			loader.setUserName(rs.getString("USERNAME"));
			loader.setGmail(rs.getString("EMAIL"));
			loader.setDateCreate(rs.getTimestamp("DATECREATE"));
			loader.setRole(Role.getRole(rs.getInt("ROLE")));
			loader.setAutoPassPushlishment("NO".equalsIgnoreCase(rs.getString("ISAUTO").trim()) ? false : true);
			loader.setPin("NO".equalsIgnoreCase(rs.getString("ISPIN").trim()) ? false : true);
			loader.setBan("NO".equalsIgnoreCase(rs.getString("ISBAN").trim()) ? false : true);
			accounts.add(loader);
		}
		return accounts;
	}
}
