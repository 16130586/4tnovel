package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import t4novel.azurewebsites.net.models.Message;

public class NotificationToListUserHandler implements Runnable {
	private List<Integer> ids;
	private Message message;

	public NotificationToListUserHandler(List<Integer> ids, Message message) {
		super();
		this.ids = ids;
		this.message = message;
	}

	@Override
	public void run() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/sqlserver");
			Connection cnn = ds.getConnection();
			for (Integer userId : ids) {
				NotifycationSystem.notifyToUser(userId, message, cnn);
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
