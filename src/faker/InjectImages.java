package faker;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.utils.StringUtil;

public class InjectImages {
	public static void main(String[] args) {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connectionString = "jdbc:sqlserver://mafteru.database.windows.net:1433;database=4T;user=mafteru@mafteru;password={nnt09021998!@#};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

		try {
			Class.forName(driver);
			Connection cnn = DriverManager.getConnection(connectionString);
			ImageDAO imgDao = new ImageDAO(cnn);
			File imgSource = new File("E:\\img-data");
			for (File img : imgSource.listFiles()) {
				InputStream input = new BufferedInputStream(new FileInputStream(img));
				imgDao.insertNewImage(input);
				System.out.println("inserted img: " + img.getName());
				input.close();
			}
			computeETag(cnn, imgDao);
			cnn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void computeETag(Connection cnn, ImageDAO imgDao) throws SQLException, NoSuchAlgorithmException {
		String query = "SELECT * FROM IMAGES WHERE ETAG IS NULL";
		PreparedStatement stmt = cnn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println("computing etag of img: " + rs.getInt("ID"));
			imgDao.updateEtag(StringUtil.hashWith256(rs.getBytes("DATA")), rs.getInt("ID"));
		}
		rs.close();
		stmt.close();
	}
}
