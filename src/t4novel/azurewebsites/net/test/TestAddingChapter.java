package t4novel.azurewebsites.net.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.forms.AddingChapterForm;
import t4novel.azurewebsites.net.models.Chap;

public class TestAddingChapter {

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

		AddingChapterForm form = new AddingChapterForm();
		form.setTitle("chương 1");
		form.setContent(
				"Được dịch từ tiếng Anh-Trong lý thuyết văn học, văn bản là bất kỳ đối tượng nào có thể \"đọc\", cho dù đối tượng này là tác phẩm văn học, biển báo, sắp xếp các tòa nhà trên khối thành phố hoặc kiểu quần áo. Đó là một tập hợp các dấu hiệu truyền đạt một số loại thông điệp mang tính thông tin.");
		form.setInNovel(1);
		form.setInVol(1);
		if (!form.isOnError() && cnn != null) {
			// TODO writing to db , and something related
			Chap chap = (Chap) form.getMappingData();
			ChapDAO chapDAO = new ChapDAO(cnn);
			chapDAO.insertChap(chap);
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
