package t4novel.azurewebsites.net.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.collections4.map.LRUMap;

public class ImageDAO {
	private Connection cnn;
	private static final NextIdGenrator NEXT_ID_GENRATOR;
	private static final Map<Integer, byte[]> IMGS_CACHE;
	static {
		NEXT_ID_GENRATOR = new NextIdGenrator("IMAGES");
		IMGS_CACHE = Collections.synchronizedMap(new LRUMap<Integer, byte[]>(10, 5, true));
	}

	public ImageDAO(Connection cnn) {
		this.cnn = cnn;
	}

	public void insertImage(int idOwner, String type, InputStream in) throws Exception {
		String query;
		PreparedStatement stmt = null;
		query = ("insert into IMAGE (IDOWNER, IMG, TYPE) values (?, ?, ?)");
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idOwner);
			if (in != null)
				stmt.setBlob(2, in);
			else
				stmt.setBytes(2, new byte[0]);
			stmt.setString(3, type);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}

	public void updateImage(int idOwner, String type, InputStream in) throws Exception {
		String query;
		PreparedStatement stmt = null;
		query = ("update IMAGE set IMG = ? where IDOWNER = ?");
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setBlob(1, in);
			stmt.setInt(2, idOwner);
			stmt.executeUpdate();
			IMGS_CACHE.remove(idOwner);
		} finally {
			stmt.close();
		}
	}

	public void deleteImageById(int idOwner, String type) throws Exception {

		PreparedStatement stmt = null;
		String query = "delete from IMAGE where IDOWNER = ? and TYPE = ?";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idOwner);
			stmt.setString(2, type);
			stmt.executeUpdate();
			IMGS_CACHE.remove(idOwner);
		} finally {
			stmt.close();
		}
	}

	public String getEncodeImage(int idOwner, String type) throws Exception {
		String result = null;
		String query = "select IMG from IMAGE where IDOWNER = ? and TYPE = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, idOwner);
			stmt.setString(2, type);
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = getEncode(rs.getBytes(1));
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return result;
	}

	public byte[] getBytesOfImage(int id) throws SQLException {
		byte[] bytesImg = IMGS_CACHE.get(id);
		if(bytesImg != null) {
			return bytesImg;
		}
		String query = "SELECT DATA from IMAGES where ID = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				bytesImg = rs.getBytes(1);
				IMGS_CACHE.put(id, bytesImg);
			}
		} finally {
			rs.close();
			stmt.close();
		}

		return bytesImg;
	}

	
	//new logic with new db
	public void insertNewImage(InputStream inputStream) throws SQLException {
		String query = "INSERT INTO IMAGES(DATA) VALUES(?)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setBlob(1, inputStream);
		stmt.executeUpdate();
		stmt.close();
	}   
	public int getNextId(Connection cnn) throws Exception {
		return NEXT_ID_GENRATOR.nextAutoIncrementId(cnn);
	}
	public static String getEncode(byte[] buf) {
		return Base64.getEncoder().encodeToString(buf);
	}
}
