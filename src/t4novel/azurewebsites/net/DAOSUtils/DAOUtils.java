package t4novel.azurewebsites.net.DAOSUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtils {
	private static DAOUtils instance;
	private final String user = "thachpro44";
	private final String pwd = "mjsdhekoqa";
	private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String url = "jdbc:sqlserver://localhost:6000;databaseName=4T";

	private DAOUtils() {
	}

	public Connection getConnection() {
		Connection cnn = null;
		try {
			Class.forName(driver);
			cnn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnn;
	}

	public void close(Connection cnn) {
		if (cnn != null)
			try {
				cnn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public synchronized static DAOUtils getInstance() {
		if (instance == null)
			instance = new DAOUtils();
		return instance;
	}
}
