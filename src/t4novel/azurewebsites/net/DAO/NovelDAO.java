package t4novel.azurewebsites.net.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelGenre;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;

public class NovelDAO {
	private Connection cnn;

	public NovelDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public List<Novel> getNovelsByUserId(int accountId) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where IDOWNER = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountId);
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

	public List<Novel> searchNovelsByNamePattern(String name) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where NAME like '%" + name
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
			rs.close();
			stmt.close();
		}
		return result;
	}

	public List<Novel> searchByAdvance(List<NovelGenre> genres, int statusVal, String kind, String name)
			throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where STATUS = ? and KIND = ?";
		if (name != null && !name.isEmpty()) {
			query += "AND NAME = " + name;
		}
		try {
			stmt = cnn.prepareStatement(query.toString());
			stmt.setInt(1, statusVal);
			stmt.setString(2, kind);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int idNovel = rs.getInt("ID");
				if (isExistGenresInNovel(idNovel, genres)) {
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
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return result;
	}

	public void delNovelById(int idNovel) throws Exception {
		PreparedStatement stmt = null;
		String query = "delete from LN where ID = ?";
		try {
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
		String query = "INSERT INTO LN (NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setDate(3, new Date(novel.getDateUp().getTime()));
			stmt.setInt(4, novel.getAccountOwnerId());
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

	public synchronized int getMaxID() throws Exception {
		int result = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = cnn.prepareStatement("select max(ID) from LN");
			rs = stmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} finally {
			rs.close();
			stmt.close();
		}
		return result;
	}

	public boolean isExistGenresInNovel(int idNovel, List<NovelGenre> genres) throws Exception {
		return isExistGenresInNovel(idNovel, genres);
	}

	public boolean isExistGenreInNovel(int idNovel, NovelGenre genre, GenreDAO genreDAO) throws Exception {
		return genreDAO.isExistGenreInNovel(idNovel, genre);
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
}
