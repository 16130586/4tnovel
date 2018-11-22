package t4novel.azurewebsites.net.DAOService;

public abstract class BaseDaoService implements DAOService {
	@Override
	public boolean check(String data, String onQuery) {
		
		return false;
	}

	@Override
	public boolean check(String data1, String data2, String onQuery) {
		
		return false;
	}
}
