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

import t4novel.azurewebsites.net.models.Vol;

public class VolDAO {

	private Connection cnn;
	private static final NextIdGenrator NEXT_ID_GENRATOR;
//	private static final Map<Integer, Vol> VOLS_CACHE;
	static {
		NEXT_ID_GENRATOR = new NextIdGenrator("VOL");
//		VOLS_CACHE = Collections.synchronizedMap(new LRUMap<Integer, Vol>(20, 10, true));
	}

	public VolDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertVol(Vol vol) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO VOL (ID_LN, TITLE, DESCRIBE ) VALUES (?, ?, ? )";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, vol.getNovelOwnerId());
			stmt.setString(2, vol.getTitle());
			stmt.setString(3, vol.getDescription());
			stmt.executeUpdate();
			stmt.close();
			cnn.commit();
		} catch (SQLException e) {
			cnn.rollback();
			throw e;
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public Vol getVolByID(int volID) throws Exception {
//		Vol vol = VOLS_CACHE.get(volID);
//		if (vol != null) {
//			return vol;
//		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM VOL WHERE ID = ?";
		Vol vol = null;
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, volID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				 vol = new Vol();
				vol.setId(rs.getInt(1));
				vol.setNovelOwnerId(rs.getInt(2));
				vol.setTitle(rs.getString(3));
				vol.setDescription(rs.getString(4));
				vol.setDateUp(rs.getDate(5));
//				VOLS_CACHE.put(volID, vol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return vol;
	}

	public List<Vol> getVolsOfNovel(int novelID) throws Exception {
		LinkedList<Vol> listVol = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM VOL WHERE ID_LN = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, novelID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int volId = rs.getInt("ID");
//				Vol vol = VOLS_CACHE.get(volId);
//				if (vol != null) {
//					listVol.add(vol);
//					continue;
//				}
				Vol vol = new Vol();
				vol.setId(rs.getInt(1));
				vol.setNovelOwnerId(rs.getInt(2));
				vol.setTitle(rs.getString(3));
				vol.setDescription(rs.getString(4));
				vol.setDateUp(rs.getDate(5));
//				VOLS_CACHE.put(volId, vol);
				listVol.add(vol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return listVol;
	}

	public void updateVol(Vol vol) throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE VOL SET TITLE = ?, DESCRIBE = ? WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, vol.getTitle());
			stmt.setString(2, vol.getDescription());
			stmt.setInt(3, vol.getId());
			stmt.executeUpdate();
			stmt.close();
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

	public void deleteVolByID(int volID, ChapDAO chapDAO) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE FROM VOL WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			chapDAO.deleteChapByVolID(volID);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, volID);
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

	public void deleteVolsOfNovel(int novelID) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE FROM VOL WHERE ID_LN = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, novelID);
			stmt.executeUpdate();
			stmt.close();
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
