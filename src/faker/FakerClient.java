package faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Random;

import com.github.javafaker.Faker;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

public class FakerClient {

	public static void main(String[] args) {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connectionString = "jdbc:sqlserver://mafteru.database.windows.net:1433;database=4T;user=mafteru@mafteru;password={nnt09021998!@#};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		long period = 0;
		Random rd = new Random();
		try {
			Class.forName(driver);
			Connection cnn = DriverManager.getConnection(connectionString);
			AccountFaker accFaker = new AccountFaker();
			GroupFaker groupFaker = new GroupFaker();
			NovelFaker novelFaker = new NovelFaker();
			VolFaker volFaker = new VolFaker();
			ChapFaker chapFaker = new ChapFaker();

			period = rd.nextInt( 10 * 60 * 1000);
			// ten minutes for 1 fake
			Faker faker = new Faker();
			while(true) {
				if(cnn == null) cnn = DriverManager.getConnection(connectionString);
				Account ac = accFaker.fake(cnn, faker);
				ac.setJoinGroup(new LinkedList<>());
				groupFaker.fake(ac, cnn, faker);
				Novel novel = novelFaker.fake(cnn, faker, ac);
				Vol vol = volFaker.fake(novel, faker, cnn);
				int maxChap = rd.nextInt(15);
				for(int  i = 0 ; i < maxChap ; i ++) {
					chapFaker.fake(cnn, faker, novel, vol, ac);
				}
				System.out.println("insert success : [" + ac + "]" + "\n and 1 novel: [" + novel.getName() + "] with : " + maxChap + " chaps!");
				System.out.println("next insert after: " + period + "s!");
				System.out.println("\n\n");
				cnn.close();
				cnn = null;
				Thread.sleep(period);
			}
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
