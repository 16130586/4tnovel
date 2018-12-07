package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import t4novel.azurewebsites.net.models.Message;

public class ScheduleNotificationHander implements Runnable {
	private long period;
	private List<Integer> userIds;
	private Message msg;

	public ScheduleNotificationHander(List<Integer> userIds, Message msg, long period) {
		super();
		this.period = period;
		this.userIds = userIds;
		this.msg = msg;
	}

	@Override
	public void run() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/sqlserver");
			Connection cnn = ds.getConnection();

			for (Integer userId : userIds) {
				Thread.sleep(period);
				NotifycationSystem.notifyToUser(userId, msg, cnn);
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
