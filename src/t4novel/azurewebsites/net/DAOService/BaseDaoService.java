package t4novel.azurewebsites.net.DAOService;

import java.sql.Connection;

public abstract class BaseDaoService implements DAOService {
	protected Connection cnn;

	public BaseDaoService(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	@Override
	public boolean check(String data, String onQuery) {
		throw new UnsupportedOperationException("UnsupportedOperationException");
	}

	@Override
	public boolean check(String data1, String data2, String onQuery) {
		throw new UnsupportedOperationException("UnsupportedOperationException");
	}

}
