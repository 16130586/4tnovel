package t4novel.azurewebsites.net.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

public class ImageDAO {
	
	public static void insertImage(int idOwner, String type, InputStream in, Connection cnn) throws Exception {
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
	

	public static void updateImage(int idOwner, String type, InputStream in, Connection cnn) throws Exception{
		String query;
		PreparedStatement stmt;
		query = ("update IMAGE set IMG = ? where IDOWNER = ?");
		stmt = cnn.prepareStatement(query);
		stmt.setBlob(1, in);
		stmt.setInt(2, idOwner);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public static void deleteImageById(int idOwner, String type, Connection cnn) throws Exception {
		PreparedStatement stmt;
		String query = "delete from IMAGE where IDOWNER = ? and TYPE = ?";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idOwner);
		stmt.setString(2, type);
		stmt.executeUpdate();
		stmt.close();
	}
	public static String getEncodeImage(int idOwner, String type, Connection cnn) throws Exception {
		String query = "select IMG from IMAGE where IDOWNER = ? and TYPE = ?";
		PreparedStatement stmt;
		String result = "";
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idOwner);
		stmt.setString(2, type);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			result = getEncode(rs.getBytes(1));
		}
		rs.close();
		stmt.close();
		return result;
	}
	
//	public getEncodesImage()
	
	public static String getEncode(byte[] buf) {
		return Base64.getEncoder().encodeToString(buf);
	}
}
