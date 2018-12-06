package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.LRUMap;

import t4novel.azurewebsites.net.models.Chap;

public class ChapDAO {

	private Connection cnn;
	private static final NextIdGenrator NEXT_ID_GENRATOR;
	private static final Map<Integer, Chap> CHAPS_CACHE;
	static {
		NEXT_ID_GENRATOR = new NextIdGenrator("CHAP");
		CHAPS_CACHE = Collections.synchronizedMap(new LRUMap<Integer, Chap>(40, 20, true));
	}

	public ChapDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertChap(Chap chap) throws Exception {
		PreparedStatement stmt = null;
		String querry = "INSERT INTO CHAP (ID_VOL, ID_NOVEL, TITLE ,CONTENT) VALUES (?, ?, ? , ?)";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chap.getVolOwnerId());
			stmt.setInt(2, chap.getNovelOwnerId());
			stmt.setString(3, chap.getTitle());
			stmt.setString(4, chap.getContent());
			stmt.executeUpdate();
			cnn.commit();
		} catch (SQLException e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public Chap getChapByID(int chapID) throws Exception {
		Chap chap = CHAPS_CACHE.get(chapID);
		if (chap != null) {
			return chap;
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT * FROM CHAP WHERE ID = ?";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				chap = new Chap();
				chap.setId(rs.getInt("ID"));
				chap.setVolOwnerId(rs.getInt("ID_VOL"));
				chap.setNovelOwnerId(rs.getInt("ID_NOVEL"));
				chap.setTitle(rs.getString("TITLE"));
				chap.setContent(rs.getString("CONTENT"));
				chap.setDateUp(rs.getDate("DATEUP"));
				CHAPS_CACHE.put(chapID, chap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return chap;
	}

	public List<Chap> getChaps(String sortByCondition, String filterCondition, int offSet, int limit)
			throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Chap> result = new LinkedList<>();
//		String query = "SELECT * FROM LN WHERE ID IN(" + "SELECT ID FROM LN"
//				+ (filterCondition == null ? "" : " WHERE " + filterCondition) + " group by ID order by ID offset "
//				+ offSet + " rows fetch next " + limit + " rows only)"
//				+ (sortByCondition == null ? "" : " order by " + sortByCondition);

		String query = "SELECT ID FROM CHAP WHERE ID IN(" + "SELECT ID FROM CHAP "
				+ (filterCondition == null ? "" : " WHERE " + filterCondition) + "order by DATEUP DESC OFFSET " + offSet
				+ " rows fetch next " + limit + " rows only " + ")"
				+ (sortByCondition == null ? "" : " order by " + sortByCondition);
		stmt = cnn.prepareStatement(query);
		rs = stmt.executeQuery();
		while (rs.next()) {
			result.add(getChapByID(rs.getInt("ID")));
		}

		rs.close();
		stmt.close();

		return result;
	}
	
	public List<Chap> getEntireChapsByVolId(int volID) throws Exception {
		LinkedList<Chap> listChap = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT * FROM CHAP WHERE ID_VOL = ?";

		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, volID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int chapId = rs.getInt("ID");
				Chap chap = CHAPS_CACHE.get(chapId);
				if (chap != null && chap.getContent() != null) {
					listChap.add(chap);
					continue;
				}
				chap = new Chap();
				chap.setId(chapId);
				chap.setVolOwnerId(rs.getInt("ID_VOL"));
				chap.setNovelOwnerId(rs.getInt("ID_NOVEL"));
				chap.setTitle(rs.getString("TITLE"));
				chap.setContent(rs.getString("CONTENT"));
				chap.setDateUp(rs.getDate("DATEUP"));
				CHAPS_CACHE.put(chapId, chap);
				listChap.add(chap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return listChap;
	}

	public List<Chap> getPartOfChapsByVolId(int volId) throws SQLException {
		LinkedList<Chap> listChap = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT ID , ID_NOVEL , TITLE , DATEUP FROM CHAP WHERE ID_VOL = ?";

		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, volId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int chapId = rs.getInt("ID");
				Chap chap = CHAPS_CACHE.get(chapId);
				if (chap != null) {
					listChap.add(chap);
					continue;
				}
				chap = new Chap();
				chap.setId(chapId);
				chap.setVolOwnerId(volId);
				chap.setNovelOwnerId(rs.getInt("ID_NOVEL"));
				chap.setTitle(rs.getString("TITLE"));
				chap.setDateUp(rs.getDate("DATEUP"));
				CHAPS_CACHE.put(chapId, chap);
				listChap.add(chap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return listChap;
	}

	public String getContentOfChap(Chap chap) throws Exception {
		if (chap.getContent() != null) {
			return chap.getContent();
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT CONTENT FROM CHAP WHERE ID = ?";
		String content = null;

		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chap.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				content = rs.getString("CONTENT");
				if (content == null)
					content = "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return content;
	}

	public void deleteChapByID(int chapID) throws Exception {
		PreparedStatement stmt = null;
		String querry = "DELETE FROM CHAP WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapID);
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Delete chap completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteChapByVolID(int chapID) throws Exception {
		PreparedStatement stmt = null;
		String querry = "DELETE FROM CHAP WHERE ID_VOL = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapID);
			stmt.executeUpdate();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteChapByNovelID(int novelID) throws Exception {
		PreparedStatement stmt = null;
		String querry = "DELETE FROM CHAP WHERE ID_NOVEL = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, novelID);
			stmt.executeUpdate();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateChap(Chap chap) throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE CHAP set ID_VOL = ? , ID_NOVEL = ? , TITLE = ? , CONTENT = ? WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, chap.getVolOwnerId());
			stmt.setInt(2, chap.getNovelOwnerId());
			stmt.setString(3, chap.getTitle());
			stmt.setString(4, chap.getContent());
			stmt.setInt(5, chap.getId());
			stmt.executeUpdate();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public int getNextID() throws Exception {
		return NEXT_ID_GENRATOR.nextAutoIncrementId(cnn);
	}

}
