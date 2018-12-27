package t4novel.azurewebsites.net.DAO;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.map.LRUMap;

import t4novel.azurewebsites.net.utils.StringUtil;

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
		if (bytesImg != null) {
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

	// new logic with new db
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

	public Timestamp getDateUp(int imgId) throws SQLException {
		String query = "SELECT DATEUP FROM IMAGES WHERE ID = ?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, imgId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getTimestamp("DATEUP");
		}
		return null;
	}

	public void updateImage(InputStream inputStream, int coverId) throws SQLException, NoSuchAlgorithmException {
		String query;
		PreparedStatement stmt = null;
		query = ("update IMAGES set DATA = ?, DATEUP=? where ID = ?");
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setBlob(1, inputStream);
			stmt.setTimestamp(2, new Timestamp(new Date().getTime()));
			System.out.println("cover id : " + coverId);
			stmt.setInt(3, coverId);
			stmt.executeUpdate();
			IMGS_CACHE.remove(coverId);

			byte[] datas = getBytesOfImage(coverId);
			updateEtag(StringUtil.hashWith256(datas), coverId);

		} finally {
			stmt.close();
		}
	}

	public String getEtag(int imgId) throws SQLException {
		String query = "SELECT ETAG FROM IMAGES WHERE ID = ?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, imgId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getString("ETAG");
		}
		rs.close();
		stmt.close();
		return null;
	}

	public void updateEtag(String etag, int imgId) throws SQLException {
		String query = "UPDATE IMAGES SET ETAG=? WHERE ID=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setString(1, etag);
		stmt.setInt(2, imgId);
		stmt.executeUpdate();
		stmt.close();
	}
}
