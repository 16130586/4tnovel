package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ScheduleNotificationHander implements Runnable {
	private long period;
	private List<Integer> userIds;
	private String jsonData;
	public ScheduleNotificationHander(List<Integer> userIds, String jsonData,long period) {
		super();
		this.period = period;
		this.userIds = userIds;
		this.jsonData = jsonData;
	}

	@Override
	public void run() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/sqlserver");
			Connection cnn = ds.getConnection();
			
			for(Integer userId : userIds) {
				Thread.sleep(period);
				NotifycationSystem.notifyToUser(userId, jsonData, cnn);
			}
			cnn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
