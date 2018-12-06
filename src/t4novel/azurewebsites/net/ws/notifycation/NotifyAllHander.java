package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import t4novel.azurewebsites.net.sercurities.Role;

public class NotifyAllHander implements Runnable {
	private String jsonData;

	public NotifyAllHander(String jsonData) {
		super();
		this.jsonData = jsonData;
	}

	@Override
	public void run() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/sqlserver");
			Connection cnn = ds.getConnection();
			
			String query = "SELECT ID FROM ACCOUNT WHERE ROLE <>  ?";
			PreparedStatement stmt = cnn.prepareStatement(query);
			stmt.setInt(1, Role.ADMINISTRATOR.getIntValue());
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				NotifycationSystem.notifyToUser(resultSet.getInt("ID"), this.jsonData, cnn);
			}
			resultSet.close();
			stmt.close();
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
