package t4novel.azurewebsites.net.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Part;

import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;

public class NovelDAO {
	private Connection cnn;

	public NovelDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	
	public List<Novel> getNovelsFromUser(int accountId) {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select * from LN where IDOWNER = ?";
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
				tmp.setGenres(Novel.parseStringToGenres(rs.getString(6)));
				tmp.setKind(NovelKind.getNovelKind(rs.getInt(7)));
				tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt(8)));
				tmp.setEncodeImg(getEncode(rs.getBytes(9)));
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
		String query = "select * from LN where NAME like \'%?%\'";
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
				tmp.setGenres(Novel.parseStringToGenres(rs.getString(6)));
				tmp.setKind(NovelKind.getNovelKind(rs.getInt(7)));
				tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt(8)));
				tmp.setEncodeImg(getEncode(rs.getBytes(9)));
				result.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @param input is list value
	 * @return 
	 */
	public List<Novel> getNovelsBySearchAdvance(List<Integer> values, int statusVal, int kindVal, String name) {
		Statement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		StringBuffer query = new StringBuffer("select * from LN where ");
		for (int i = 0; i < values.size(); i++) {
			query.append("GENRE like \'%"+values.get(i)+"%\' AND ");
		}
		
		//kind & status have default value in form search advance
		query.append("STATUS = " + statusVal + " AND KIND = " + kindVal);
		if(name != null && !name.isEmpty()) {
			query.append("AND NAME = " + name);
		}
		try {
			stmt = cnn.createStatement();
			rs = stmt.executeQuery(query.toString());
			while(rs.next()) {
				tmp = new Novel();
				tmp.setId(rs.getInt(1));
				tmp.setName(rs.getString(2));
				tmp.setDescription(rs.getString(3));
				tmp.setDateUp(rs.getDate(4));
				tmp.setAccountOwnerId(rs.getInt(5));
				tmp.setGenres(Novel.parseStringToGenres(rs.getString(6)));
				tmp.setKind(NovelKind.getNovelKind(rs.getInt(7)));
				tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt(8)));
				tmp.setEncodeImg(getEncode(rs.getBytes(9)));
				result.add(tmp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public List<Novel> getAllNovel() {
		PreparedStatement stmt;
		ResultSet rs;
		List<Novel> result = new LinkedList<>();
		Novel tmp;
		String query = "select ID, NAME, DESCRIBE, DATEUP, IDOWNER, GENRE, KIND, STATUS, IMAGE from LN";
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
				tmp.setGenres(Novel.parseStringToGenres(rs.getString(6)));
				tmp.setKind(NovelKind.getNovelKind(rs.getInt(7)));
				tmp.setStatus(NovelStatus.getNovelStatus(rs.getInt(8)));
				tmp.setEncodeImg(getEncode(rs.getBytes(9)));
				result.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void delNovelById(String idNovel) {
		PreparedStatement stmt;
		String query = "delete from LN where ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(idNovel));
			stmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateNovel(Novel novel) {
		PreparedStatement stmt;
		String query = "update LN set NAME = ?, DESCRIBE = ?, GENRE = ?, KIND = ?, STATUS = ? where ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setString(3, novel.genreToString());
			stmt.setInt(4, novel.getKind().getValue());
			stmt.setInt(5, novel.getStatus().getValue());
			stmt.setInt(6, novel.getId());
			stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertNovel(Novel novel) {
		PreparedStatement stmt;
		String querry = "INSERT INTO LN (NAME, DESCRIBE, DATEUP, IDOWNER, GENRE, KIND, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			stmt = cnn.prepareStatement(querry);
			stmt.setString(1, novel.getName());
			stmt.setString(2, novel.getDescription());
			stmt.setDate(3, (Date) novel.getDateUp());
			stmt.setInt(4, novel.getAccountOwnerId());
			stmt.setString(5, novel.genreToString());
			stmt.setInt(6, novel.getKind().getValue());
			stmt.setInt(7, novel.getStatus().getValue());
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateImage(int idNovel, Part filePart){
		int len = (int) filePart.getSize();
		InputStream in;
		String query;
		PreparedStatement stmt;
		try {
			in = filePart.getInputStream();
			query = ("update LN set IMAGE = ? where ID = ?");
			stmt = cnn.prepareStatement(query);
			stmt.setBlob(1, in);
			stmt.setInt(2, idNovel);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEncode(byte[] buf) {
		return Base64.getEncoder().encodeToString(buf);
	}
	
}
