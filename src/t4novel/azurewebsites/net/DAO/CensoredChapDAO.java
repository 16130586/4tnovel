package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Chap;

public class CensoredChapDAO extends ChapDAO {
	public CensoredChapDAO(Connection databaseConnection) {
		super(databaseConnection);
	}

	@Override
	public Chap getChapByID(int chapID) throws SQLException {
		Chap chap = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT * FROM CHAP where ID=?";
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
				chap.setDateUp(rs.getTimestamp("DATEUP"));
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

	@Override
	public Chap getPartOfChapsByChapId(int chapId) throws SQLException {
		Chap chap = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String querry = "SELECT * FROM CHAP where ID=?";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setInt(1, chapId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				chap = new Chap();
				chap.setId(rs.getInt("ID"));
				chap.setVolOwnerId(rs.getInt("ID_VOL"));
				chap.setNovelOwnerId(rs.getInt("ID_NOVEL"));
				chap.setTitle(rs.getString("TITLE"));
				chap.setDateUp(rs.getTimestamp("DATEUP"));
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

	@Override
	public List<Chap> getPartOfChapsByVolId(int volId) throws SQLException {
		List<Chap> chaps = new LinkedList<>();
		String query = "SELECT * FROM CHAP where ID_VOL=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, volId);
		ResultSet rs = stmt.executeQuery();
		Chap temp = null;
		while (rs.next()) {
			temp = new Chap();
			temp.setId(rs.getInt("ID"));
			temp.setVolOwnerId(rs.getInt("ID_VOL"));
			temp.setNovelOwnerId(rs.getInt("ID_NOVEL"));
			temp.setTitle(rs.getString("TITLE"));
			temp.setDateUp(rs.getTimestamp("DATEUP"));
			chaps.add(temp);
		}
		return chaps;
	}

	@Override
	public List<Chap> getEntireChapsByVolId(int volID) throws Exception {
		List<Chap> chaps = new LinkedList<>();
		String query = "SELECT * FROM CHAP where ID_VOL=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, volID);
		ResultSet rs = stmt.executeQuery();
		Chap temp = null;
		while (rs.next()) {
			temp = new Chap();
			temp.setId(rs.getInt("ID"));
			temp.setVolOwnerId(rs.getInt("ID_VOL"));
			temp.setNovelOwnerId(rs.getInt("ID_NOVEL"));
			temp.setTitle(rs.getString("TITLE"));
			temp.setContent(rs.getString("CONTENT"));
			temp.setDateUp(rs.getTimestamp("DATEUP"));
			chaps.add(temp);
		}
		return chaps;
	}


	@Override
	public void deleteChapByID(int chapID) throws Exception {
		String deleteOnChapTable = "DELETE FROM CHAP WHERE ID=?";
		PreparedStatement stmt1 = cnn.prepareStatement(deleteOnChapTable);
		stmt1.setInt(1, chapID);

		String deleteOnCensoringTable = "DELETE FROM CENSORING WHERE TARGET_ID=? AND STREAM=?";
		PreparedStatement stmt2 = cnn.prepareStatement(deleteOnCensoringTable);
		stmt2.setInt(1, chapID);
		stmt2.setString(2, "chapter");

		cnn.setAutoCommit(false);
		stmt1.executeUpdate();
		stmt2.executeUpdate();
		cnn.commit();
		cnn.setAutoCommit(true);

		stmt2.close();
		stmt1.close();
	}

	@Override
	public void deleteChapByNovelID(int novelID) throws Exception {
		List<Integer> chapIds = new LinkedList<>();
		String getAllChapIdQuery = "SELECT ID FROM CHAP WHERE ID_NOVEL=?";

		PreparedStatement getAllChapIdStmt = cnn.prepareStatement(getAllChapIdQuery);
		getAllChapIdStmt.setInt(1, novelID);
		ResultSet rs = getAllChapIdStmt.executeQuery();
		while (rs.next()) {
			chapIds.add(rs.getInt("ID"));
		}
		cnn.setAutoCommit(false);
		for (int i = 0; i < chapIds.size(); i++) {
			deleteChapByID(chapIds.get(i));
		}
		cnn.commit();
		cnn.setAutoCommit(true);

	}

	@Override
	public void deleteChapByVolID(int volId) throws Exception {
		List<Chap> chaps = getPartOfChapsByVolId(volId);
		cnn.setAutoCommit(false);
		for (int i = 0; i < chaps.size(); i++) {
			deleteChapByID(chaps.get(i).getId());
		}
		cnn.commit();
		cnn.setAutoCommit(true);

	}

	@Override
	public List<Chap> getLatestChap(int offSet, int limit) throws SQLException {
		String query = "select max(ID) as TARGET_ID from CHAP inner join CENSORING on CHAP.ID = CENSORING.TARGET_ID \r\n" + 
				"where CENSORING.STREAM = 'chapter' and CENSORING.IS_PUBLISHED = 1\r\n" + 
				"group by ID_NOVEL order by max(OUT_DATE) desc offset ? rows fetch next ? rows only";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, offSet);
		stmt.setInt(2, limit);
		ResultSet rs = stmt.executeQuery();
		List<Chap> chaps = new LinkedList<>();
		while (rs.next()) {
			chaps.add(getChapByID(rs.getInt("TARGET_ID")));
		}
		rs.close();
		stmt.close();
		return chaps;
	}

	@Override
	public int getTotalChaps(String filterCondition) throws SQLException {
		int total = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = " select COUNT(TARGET_ID) as TOTAL from CENSORING WHERE STREAM='chapter' AND IS_PUBLISHED = 1 "
				+ (filterCondition == null ? "" : " AND " + filterCondition);
		stmt = cnn.prepareStatement(query);
		rs = stmt.executeQuery();
		if (rs.next()) {
			total = rs.getInt("TOTAL");
		}
		return total;
	}

}
