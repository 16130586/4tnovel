package t4novel.azurewebsites.net.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
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
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where IDOWNER = ?";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, accountId);
		rs = stmt.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("ID"); 
			tmp = new Novel();
			tmp.setId(id);
			tmp.setName(rs.getString("NAME"));
			tmp.setDescription(rs.getString("DESCRIBE"));
			tmp.setDateUp(rs.getDate("DATEUP"));
			tmp.setAccountOwnerId(rs.getInt("IDOWNER"));
			tmp.setKind(NovelKind.getNovelKind(rs.getString("KIND")));
			tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt("STATUS")));
			tmp.setGenres(getGenres(id));
			tmp.setEncodeImg(getEncodeImage(id));
			result.add(tmp);
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	public List<Novel> searchNovelsByNamePattern(String name) throws Exception {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where NAME like '%?%'";
		stmt = cnn.prepareStatement(query);
		stmt.setString(1, name);
		rs = stmt.executeQuery();
		while(rs.next()) {
			tmp = new Novel();
			tmp.setId(rs.getInt(1));
			tmp.setName(rs.getString(2));
			tmp.setDescription(rs.getString(3));
			tmp.setDateUp(rs.getDate(4));
			tmp.setAccountOwnerId(rs.getInt(5));
			tmp.setKind(NovelKind.getNovelKind(rs.getString(6)));
			tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt(7)));
			tmp.setGenres(getGenres(rs.getInt(1)));
			tmp.setEncodeImg(getEncodeImage(rs.getInt(1)));
			result.add(tmp);
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	public List<Novel> searchByAdvance(List<NovelGenre> genres, int statusVal, String kind, String name) throws Exception {
		PreparedStatement stmt;
		List<Novel> result = new LinkedList<>();
		
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where STATUS = ? and KIND = ?";
		if(name != null && !name.isEmpty()) {
			query += "AND NAME = " + name;
		}
		stmt = cnn.prepareStatement(query.toString());
		stmt.setInt(1, statusVal);
		stmt.setString(2, kind);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			if (isExistGenresInNovel(rs.getInt(1), genres)) {
				tmp = new Novel();
				tmp.setId(rs.getInt(1));
				tmp.setName(rs.getString(2));
				tmp.setDescription(rs.getString(3));
				tmp.setDateUp(rs.getDate(4));
				tmp.setAccountOwnerId(rs.getInt(5));
				tmp.setKind(NovelKind.getNovelKind(rs.getString(6)));
				tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt(7)));
				tmp.setGenres(getGenres(rs.getInt(1)));
				tmp.setEncodeImg(getEncodeImage(rs.getInt(1)));
				result.add(tmp);
			}
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	public List<Novel> getAllNovel() throws Exception {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN";
		stmt = cnn.prepareStatement(query);
		rs = stmt.executeQuery();
		while(rs.next()) {
			tmp = new Novel();
			tmp.setId(rs.getInt(1));
			tmp.setName(rs.getString(2));
			tmp.setDescription(rs.getString(3));
			tmp.setDateUp(rs.getDate(4));
			tmp.setAccountOwnerId(rs.getInt(5));
			tmp.setKind(NovelKind.getNovelKind(rs.getString(6)));
			tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt(7)));
			tmp.setGenres(getGenres(tmp.getId()));
			tmp.setEncodeImg(getEncodeImage(tmp.getId()));
			result.add(tmp);
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	/**
	 * delete data in LN, IMAGE, GENRE table
	 * @param idNovel
	 * @throws Exception 
	 */
	public void delNovelById(int idNovel) throws Exception {
		PreparedStatement stmt;
		String query = "delete from LN where ID = ?";
		try {
			cnn.setAutoCommit(false);
			deleteImageById(idNovel);  //delete image
			deleteGenres(idNovel); //delete genre
			// TODO : delete in vol, chap, comment, bm, bmFolder... table
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.executeUpdate();
			cnn.commit();
			cnn.setAutoCommit(true);
		} catch (Exception e) {
			cnn.rollback();
			throw e;
		}
		stmt.close();
	}
	
	/**
	 * Image need be updated separately by using FileItem when user upload file img.
	 * @param novel
	 */
	public void updateNovel(Novel novel) throws Exception {
		PreparedStatement stmt;
		String query = "update LN set NAME = ?, DESCRIBE = ?, KIND = ?, STATUS = ? where ID = ?";
		
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setString(3, novel.getKind().toText());
			stmt.setInt(4, novel.getStatus().getValue());
			stmt.setInt(5, novel.getId());
			updateGenres(novel.getId(), novel.getGenres());
			stmt.executeUpdate();
			cnn.commit();
			cnn.setAutoCommit(true);
		} catch (Exception e) {
			cnn.rollback();
			throw e;
		}
		stmt.close();
	}
	
	/**
	 * Image need be inserted separately by using FileItem when user upload file img.
	 * @param novel
	 * @throws Exception 
	 */
	public void insertNovel(Novel novel) throws Exception {
		PreparedStatement stmt;
		String query = "INSERT INTO LN (NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS) VALUES (?, ?, ?, ?, ?, ?)";
		stmt = cnn.prepareStatement(query);
		stmt.setString(1, novel.getName());
		stmt.setString(2, novel.getDescription());
		stmt.setDate(3, new Date(novel.getDateUp().getTime()));
		stmt.setInt(4, novel.getAccountOwnerId());
		stmt.setString(5, novel.getKind().toText());
		stmt.setInt(6, novel.getStatus().getValue());
		stmt.executeUpdate();
		insertGenres(getMaxID(), novel.getGenres());
		stmt.close();
	}
	
	public int getMaxID() throws Exception {
		int result = 0;
		PreparedStatement stmt;
		ResultSet rs;
		stmt = cnn.prepareStatement("select max(ID) from LN");
		rs = stmt.executeQuery();
		if (rs.next())
			result = rs.getInt(1);
		rs.close();
		stmt.close();
		return result;
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
		PreparedStatement stmt;
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idNovel);
		stmt.setInt(2, genre.getValue());
		ResultSet rs = stmt.executeQuery();
		result = rs.next();
		rs.close();
		stmt.close();
		return result;
	}
	
	public List<NovelGenre> getGenres(int idNovel) throws Exception {
		List<NovelGenre> result = new LinkedList<>();
		PreparedStatement stmt;
		String query = "select VALUE from GENRE where IDNOVEL = ?";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idNovel);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			result.add(NovelGenre.getGenre(rs.getInt(1)));
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	public void insertGenres(int idNovel, List<NovelGenre> genres) throws Exception {
		PreparedStatement stmt;
		String query = "insert into GENRE (IDNOVEL, VALUE) values (?, ?)";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idNovel);
		
		for (NovelGenre genre : genres) {
			stmt.setInt(2, genre.getValue());
			stmt.executeUpdate();
		}
		stmt.close();
	}
	
	public void deleteGenres(int idNovel) throws Exception {
		PreparedStatement stmt;
		String query = "delete from GENRE where IDNOVEL = ?";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idNovel);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void updateGenres(int idNovel, List<NovelGenre> genres) throws Exception {
		try {
			cnn.setAutoCommit(false);
			deleteGenres(idNovel);
			insertGenres(idNovel, genres);
			cnn.commit();
			cnn.setAutoCommit(true);
		} catch (Exception e) {
			cnn.rollback();
			throw e;
		}
	}
	/**
	 * get encode String by using Base64
	 * @throws Exception 
	 */
	public String getEncodeImage(int idNovel) throws Exception {
		String query = "select IMG from IMAGE where IDOWNER = ? and TYPE = 'NOVEL'";
		PreparedStatement stmt;
		String result = "";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idNovel);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			result = getEncode(rs.getBytes(1));
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	public void insertImage(int idNovel, InputStream in) throws Exception {
		String query;
		PreparedStatement stmt;
		query = ("insert into IMAGE (IDOWNER, IMG, TYPE) values (?, ?, 'NOVEL')");
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idNovel);
		if (in != null)
			stmt.setBlob(2, in);
		else
			stmt.setBytes(2, new byte[0]);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void updateImage(int idNovel, InputStream in) throws Exception{
		String query;
		PreparedStatement stmt;
		query = ("update IMAGE set IMG = ? where IDOWNER = ? and TYPE = 'NOVEL'");
		stmt = cnn.prepareStatement(query);
		stmt.setBlob(1, in);
		stmt.setInt(2, idNovel);
		stmt.executeUpdate();
		stmt.close();
	}

	public void deleteImageById(int idNovel) throws Exception {
		PreparedStatement stmt;
		String query = "delete from IMAGE where IDOWNER = ? and TYPE = 'NOVEL'";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idNovel);
		stmt.executeUpdate();
		stmt.close();
	}

	public String getEncode(byte[] buf) {
		return Base64.getEncoder().encodeToString(buf);
	}
}
