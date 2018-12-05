package t4novel.azurewebsites.net.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelGenre;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;
import org.apache.commons.collections4.map.*;

public class NovelDAO {
	private Connection cnn;
	private static final NextIdGenrator NEXT_ID_GENRATOR;
	private static final Map<Integer, Novel> NOVELS_CACHE;
	static {
		NEXT_ID_GENRATOR = new NextIdGenrator("LN");
		NOVELS_CACHE = Collections.synchronizedMap(new LRUMap<Integer, Novel>(10, 5, true));
	}

	public NovelDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public List<Novel> getNovelsByUserId(int accountId) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, IDGROUP,KIND, STATUS from LN where IDOWNER = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int idNovel = rs.getInt("ID");
				tmp = NOVELS_CACHE.get(idNovel);
				if (tmp != null) {
					result.add(tmp);
					continue;
				}
				tmp = new Novel();
				tmp.setId(idNovel);
				tmp.setName(rs.getString("NAME"));
				tmp.setDescription(rs.getString("DESCRIBE"));
				tmp.setDateUp(rs.getDate("DATEUP"));
				tmp.setAccountOwnerId(rs.getInt("IDOWNER"));
				tmp.setGroupId(rs.getInt("IDGROUP"));
				tmp.setKind(NovelKind.getNovelKind(rs.getString("KIND")));
				tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt("STATUS")));
				result.add(tmp);
				NOVELS_CACHE.put(idNovel, tmp);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return result;
	}

	public List<Novel> searchNovelsByNamePattern(String name) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where NAME like N'%" + name
				+ "%'";
		try {
			stmt = cnn.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int idNovel = rs.getInt("ID");
				tmp = new Novel();
				tmp.setId(idNovel);
				tmp.setName(rs.getString("NAME"));
				tmp.setDescription(rs.getString("DESCRIBE"));
				tmp.setDateUp(rs.getDate("DATEUP"));
				tmp.setAccountOwnerId(rs.getInt("IDOWNER"));
				tmp.setKind(NovelKind.getNovelKind(rs.getString("KIND")));
				tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt("STATUS")));
				result.add(tmp);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return result;
	}

	public Novel getNovelById(int idNovel) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Novel result = NOVELS_CACHE.get(idNovel);
		if (result != null) {
			return result;
		}
		String query = "select * from LN where ID = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = new Novel();
				result.setId(idNovel);
				result.setName(rs.getString("NAME"));
				result.setDescription(rs.getString("DESCRIBE"));
				result.setDateUp(rs.getDate("DATEUP"));
				result.setAccountOwnerId(rs.getInt("IDOWNER"));
				result.setGroupId(rs.getInt("IDGROUP"));
				result.setKind(NovelKind.getNovelKind(rs.getString("KIND")));
				result.setStatus(NovelStatus.getNovelStatus(rs.getInt("STATUS")));
				NOVELS_CACHE.put(idNovel, result);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return result;
	}

	public void delNovelById(int idNovel, VolDAO volDAO, ChapDAO chapDAO, GenreDAO genreDAO) throws Exception {
		PreparedStatement stmt = null;
		String query = "delete from LN where ID = ?";
		try {
			genreDAO.deleteGenres(idNovel);
			chapDAO.deleteChapByNovelID(idNovel);
			volDAO.deleteVolsOfNovel(idNovel);
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.executeUpdate();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			throw e;
		} finally {
			stmt.close();
			cnn.setAutoCommit(true);
		}
	}

	public void updateNovel(Novel novel) throws Exception {
		PreparedStatement stmt = null;
		String query = "update LN set NAME = ?, DESCRIBE = ?, KIND = ?, STATUS = ? where ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setString(3, novel.getKind().toText());
			stmt.setInt(4, novel.getStatus().getValue());
			stmt.setInt(5, novel.getId());
			stmt.executeUpdate();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			throw e;
		} finally {
			stmt.close();
			cnn.setAutoCommit(true);
		}
	}

	public void insertNovel(Novel novel) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO LN (NAME, DESCRIBE, IDOWNER, IDGROUP ,KIND, STATUS) VALUES (? , ?, ?, ?, ?, ?)";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setInt(3, novel.getAccountOwnerId());
			stmt.setInt(4, novel.getGroupId());
			stmt.setString(5, novel.getKind().toText());
			stmt.setInt(6, novel.getStatus().getValue());
			stmt.executeUpdate();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			throw e;
		} finally {
			stmt.close();
			cnn.setAutoCommit(true);
		}
	}

	/**
	 * select IDNOVEL from genre where value in (?, ?...) group by idnovel having
	 * count(distinct value) = ? order by idnovel offset ? rows fetch next ? rows
	 * only;
	 */
	private String buildGetIdNovelsByGenresQuery(List<NovelGenre> genres) {
		StringBuffer query = new StringBuffer("select IDNOVEL from genre");
		boolean isEmptyGenres = genres.isEmpty();
		if (!isEmptyGenres) {
			query.append(" where value in (");
			for (int i = 0; i < genres.size(); i++) {
				if (i == genres.size() - 1)
					query.append("?)");
				else
					query.append("?,");
			}
		}
		query.append(" group by idnovel");
		if (!isEmptyGenres) {
			query.append(" having count(distinct value) = ?");
		}
		return query.toString();
	}

	public List<Novel> getNovelsByGenres(List<NovelGenre> genres, int offset, int limit) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Novel> result = new LinkedList<>();
		StringBuffer query = new StringBuffer(buildGetIdNovelsByGenresQuery(genres));
		query.append(" order by idnovel offset ? rows fetch next ? rows only");
		try {
			stmt = cnn.prepareStatement(query.toString());
			for (int i = 0; i < genres.size(); i++) {
				stmt.setInt(i + 1, genres.get(i).getValue());
			}
			stmt.setInt(genres.size() + 1, genres.size());
			stmt.setInt(genres.size() + 2, offset);
			stmt.setInt(genres.size() + 3, limit);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result.add(getNovelById(rs.getInt(1)));
			}
		} finally {
			if (rs != null)
				rs.close();
			stmt.close();
		}
		return result;

	}

	// select COUNT(ID) as TOTAL from LN where status = 0 and kind='TRANSLATE'
	// something just likes this
	public int getTotalNovels(String filterCondition) throws SQLException {
		int total = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = " select COUNT(ID) as TOTAL from LN "
				+ (filterCondition == null ? "" : " where " + filterCondition);
		stmt = cnn.prepareStatement(query);
		rs = stmt.executeQuery();
		if (rs.next()) {
			total = rs.getInt("TOTAL");
		}
		return total;
	}

	public List<Novel> getNovels(String sortByCondition, String filterCondition, int offSet, int limit)
			throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Novel> result = new LinkedList<>();
//		String query = "SELECT * FROM LN WHERE ID IN(" + "SELECT ID FROM LN"
//				+ (filterCondition == null ? "" : " WHERE " + filterCondition) + " group by ID order by ID offset "
//				+ offSet + " rows fetch next " + limit + " rows only)"
//				+ (sortByCondition == null ? "" : " order by " + sortByCondition);

		String query = "SELECT ID FROM LN WHERE ID IN(" + "SELECT ID FROM LN "
				+ (filterCondition == null ? "" : " WHERE " + filterCondition) + "order by DATEUP DESC OFFSET " + offSet
				+ " rows fetch next " + limit + " rows only " + ")"
				+ (sortByCondition == null ? "" : " order by " + sortByCondition);
		stmt = cnn.prepareStatement(query);
		rs = stmt.executeQuery();
		while (rs.next()) {
			result.add(getNovelById(rs.getInt("ID")));
		}

		rs.close();
		stmt.close();

		return result;
	}

	// select IDNOVEL from genre where value in (?..) group by idnovel having
	// count(distinct value) = ?
	public int getAmountAcceptNovelBy(List<NovelGenre> genres) throws Exception {
		int result = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = buildGetIdNovelsByGenresQuery(genres);

		try {
			stmt = cnn.prepareStatement(query);
			for (int i = 1; i <= genres.size(); i++) {
				stmt.setInt(i, genres.get(i).getValue());
			}
			rs = stmt.executeQuery();
			result = rs.getRow();
		} finally {
			if (rs != null)
				rs.close();
			stmt.close();
		}
		return result;
	}

	public List<NovelGenre> getGenres(int idNovel, GenreDAO genreDAO) throws Exception {
		return genreDAO.getGenres(idNovel);
	}

	public void insertGenres(int idNovel, List<NovelGenre> genres, GenreDAO genreDAO) throws Exception {
		genreDAO.insertGenres(idNovel, genres);
	}

	public void deleteGenres(int idNovel, GenreDAO genreDAO) throws Exception {
		genreDAO.deleteGenres(idNovel);
	}

	public void updateGenres(int idNovel, List<NovelGenre> genres, GenreDAO genreDAO) throws Exception {
		genreDAO.updateGenres(idNovel, genres);
	}

	public String getEncodeImageById(int idNovel, ImageDAO imgDAO) throws Exception {
		return imgDAO.getEncodeImage(idNovel, "NOVEL");
	}

	public void insertImageNovel(int idNovel, InputStream in, ImageDAO imgDAO) throws Exception {
		imgDAO.insertImage(idNovel, "NOVEL", in);
	}

	public void deleteImageNovelById(int idNovel, ImageDAO imgDAO) throws Exception {
		imgDAO.deleteImageById(idNovel, "NOVEL");
	}

	public void updateImageNovelById(int idNovel, InputStream in, ImageDAO imgDAO) throws Exception {
		imgDAO.updateImage(idNovel, "NOVEL", in);
	}

	public int getNextID() throws Exception {
		return NEXT_ID_GENRATOR.nextAutoIncrementId(cnn);
	}

}
