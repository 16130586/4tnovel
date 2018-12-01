package t4novel.azurewebsites.net.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.forms.AddingVolForm;
import t4novel.azurewebsites.net.models.Vol;

public class TestAddingVol {
	public static void doPost() throws SQLException {
		Connection cnn = null;
		String dbURL = "jdbc:sqlserver://localhost:6000;databaseName=4T;user=thachpro44;password=mjsdhekoqa";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cnn = DriverManager.getConnection(dbURL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AddingVolForm form = new AddingVolForm();
		form.setTitle("tập 1");
		form.setNovelOwnerId(1);
		form.setDescription("mô tả tập 1");
		if (!form.isOnError() && cnn != null) {
			// TODO writing to db , and something related
			VolDAO VolDAO = new VolDAO(cnn);
			Vol vol = (Vol) form.getMappingData();

			try {
				VolDAO.insertVol(vol);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// set sucessed for user
			System.out.println("suceess!");
		} else {
			System.out.println("eror!");
		}
		cnn.close();
	}

	public static void main(String[] args) {
		try {
			doPost();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
