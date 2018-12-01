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
		form.setTitle("chÆ°Æ¡ng 1");
		form.setContent(
				"Ä�Æ°á»£c dá»‹ch tá»« tiáº¿ng Anh-Trong lÃ½ thuyáº¿t vÄƒn há»�c, vÄƒn báº£n lÃ  báº¥t ká»³ Ä‘á»‘i tÆ°á»£ng nÃ o cÃ³ thá»ƒ \"Ä‘á»�c\", cho dÃ¹ Ä‘á»‘i tÆ°á»£ng nÃ y lÃ  tÃ¡c pháº©m vÄƒn há»�c, biá»ƒn bÃ¡o, sáº¯p xáº¿p cÃ¡c tÃ²a nhÃ  trÃªn khá»‘i thÃ nh phá»‘ hoáº·c kiá»ƒu quáº§n Ã¡o. Ä�Ã³ lÃ  má»™t táº­p há»£p cÃ¡c dáº¥u hiá»‡u truyá»�n Ä‘áº¡t má»™t sá»‘ loáº¡i thÃ´ng Ä‘iá»‡p mang tÃ­nh thÃ´ng tin.");
		form.setInNovel(1);
		form.setInVol(1);
		if (!form.isOnError() && cnn != null) {
			// TODO writing to db , and something related
			Chap chap = (Chap) form.getMappingData();
			ChapDAO chapDAO = new ChapDAO(cnn);
			try {
				chapDAO.insertChap(chap);
			} catch (Exception e) {
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
