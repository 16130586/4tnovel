package t4novel.azurewebsites.net.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Part;

import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelGenre;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;

public class NovelDAO {
	private Connection cnn;

	public NovelDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public List<Novel> getNovelsUser(int accountId) {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where IDOWNER = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, accountId);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Novel> getNovelsByName(String name) {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where NAME like '%?%'";
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Novel> getNovelsBySearchAdvance(List<NovelGenre> genres, int statusVal, String kind, String name) {
		PreparedStatement stmt;
		List<Novel> result = new LinkedList<>();
		
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN where STATUS = ? and KIND = ?";
		if(name != null && !name.isEmpty()) {
			query += "AND NAME = " + name;
		}
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Novel> getAllNovel() {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS from LN";
		try {
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
				tmp.setGenres(getGenres(rs.getInt(1)));
				tmp.setEncodeImg(getEncodeImage(rs.getInt(1)));
				result.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * delete data in LN, IMAGE, GENRE table
	 * @param idNovel
	 */
	public void delNovelById(int idNovel) {
		PreparedStatement stmt;
		String query = "delete from LN where ID = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.executeUpdate();
			deleteImageById(idNovel);  //delete image
			deleteGenres(idNovel); //delete genre
			// TODO : delete in vol, chap, comment, bm, bmFolder... table
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Image need be updated separately by using Part when user upload file img.
	 * @param novel
	 */
	public void updateNovel(Novel novel) {
		PreparedStatement stmt;
		String query = "update LN set NAME = ?, DESCRIBE = ?, KIND = ?, STATUS = ? where ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setString(3, novel.getKind().toText());
			stmt.setInt(4, novel.getStatus().getValue());
			stmt.setInt(5, novel.getId());
			updateGenres(novel.getId(), novel.getGenres());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Image need be inserted separately by using Part when user upload file img.
	 * @param novel
	 */
	public void insertNovel(Novel novel) {
		PreparedStatement stmt;
		String query = "INSERT INTO LN (NAME, DESCRIBE, DATEUP, IDOWNER, KIND, STATUS) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setDate(3, (Date) novel.getDateUp());
			stmt.setInt(4, novel.getAccountOwnerId());
			stmt.setString(5, novel.getKind().toText());
			stmt.setInt(6, novel.getStatus().getValue());
			insertGenres(novel.getId(), novel.getGenres());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExistGenresInNovel(int idNovel, List<NovelGenre> genres) {
		for (NovelGenre n : genres) {
			if (!isExistGenreInNovel(idNovel, n))
				return false;
		}
		return true;
	}
	
	public boolean isExistGenreInNovel(int idNovel, NovelGenre genre) {
		String query = "select * from GENRE where IDNOVEL = ? AND VALUE = ?";
		PreparedStatement stmt;
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.setInt(2, genre.getValue());
			return stmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<NovelGenre> getGenres(int idNovel) {
		List<NovelGenre> result = new LinkedList<>();
		PreparedStatement stmt;
		String query = "select VALUE from GENRE where IDNOVEL = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result.add(NovelGenre.getGenre(rs.getInt(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void insertGenres(int idNovel, List<NovelGenre> genres) {
		PreparedStatement stmt;
		String query = "insert into GENRE (IDNOVEL, VALUE) values (?, ?)";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			
			for (NovelGenre genre : genres) {
				stmt.setInt(2, genre.getValue());
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGenres(int idNovel) {
		PreparedStatement stmt;
		String query = "delete from GENRE where IDNOVEL = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateGenres(int idNovel, List<NovelGenre> genres) {
		deleteGenres(idNovel);
		insertGenres(idNovel, genres);
	}
	/**
	 * get encode String by using Base64
	 */
	public String getEncodeImage(int idNovel) {
		String query = "select IMG from IMAGE where IDOWNER = ? and TYPE = 'NOVEL'";
		PreparedStatement stmt;
		String result = "";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = getEncode(rs.getBytes(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void insertImage(int idNovel, Part filePart) {
		InputStream in;
		String query;
		PreparedStatement stmt;
		try {
			in = filePart.getInputStream();
			query = ("insert into IMAGE (IDOWNER, IMG, TYPE) values (?, ?, 'NOVEL')");
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.setBlob(2, in);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateImage(int idNovel, Part filePart){
		InputStream in;
		String query;
		PreparedStatement stmt;
		try {
			in = filePart.getInputStream();
			query = ("update IMAGE set IMG = ? where ID = ? and TYPE = 'NOVEL'");
			stmt = cnn.prepareStatement(query);
			stmt.setBlob(1, in);
			stmt.setInt(2, idNovel);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteImageById(int idNovel) {
		PreparedStatement stmt;
		String query = "delete from IMAGE where ID = ? and TYPE = 'NOVEL";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idNovel);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEncode(byte[] buf) {
		return Base64.getEncoder().encodeToString(buf);
	}
	
}
