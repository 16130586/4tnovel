package t4novel.azurewebsites.net.acessviasocial.DAOService;

import java.sql.Connection;


public class UserNameCheckingService extends EmailCheckingService implements DAOService {

	public UserNameCheckingService(Connection connection) {
		super(connection);
	}

}
