package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.NovelGenre;

public class GenreDAO {
	private Connection cnn;

	public GenreDAO(Connection cnn) {
		this.cnn = cnn;
	}

	public boolean isExistGenresInNovel(int idNovel, List<NovelGenre> genres) throws Exception {
		for (NovelGenre n : genres) {
			if (!isExistGenreInNovel(idNovel, n))
				return false;
		}
		return true;
	}

	public boolean isExistGenreInNovel(int idNovel, NovelGenre genre) throws Exception {
		boolean result = false;
		String query = "select * from GENRE where IDNOVEL = ? AND VALUE = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.setInt(2, genre.getValue());
			rs = stmt.executeQuery();
			result = rs.next();

		} finally {
			rs.close();
			stmt.close();
		}
		return result;
	}

	public List<NovelGenre> getGenres(int idNovel) throws Exception {
		List<NovelGenre> result = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "select VALUE from GENRE where IDNOVEL = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result.add(NovelGenre.getGenre(rs.getInt(1)));
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return result;
	}

	public void insertGenres(int idNovel, List<NovelGenre> genres) throws Exception {
		PreparedStatement stmt = null;
		String query = "insert into GENRE (IDNOVEL, VALUE) values (?, ?)";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);

			for (NovelGenre genre : genres) {
				stmt.setInt(2, genre.getValue());
				stmt.executeUpdate();
			}
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			throw e;
		} finally {
			stmt.close();
			cnn.setAutoCommit(true);
		}
	}

	public void deleteGenres(int idNovel) throws Exception {
		PreparedStatement stmt = null;
		String query = "delete from GENRE where IDNOVEL = ?";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
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

	public void updateGenres(int idNovel, List<NovelGenre> genres) throws Exception {
		try {
			cnn.setAutoCommit(false);
			deleteGenres(idNovel);
			insertGenres(idNovel, genres);
			cnn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			cnn.rollback();
			throw e;
		} finally {
			cnn.setAutoCommit(true);
		}
	}
}
