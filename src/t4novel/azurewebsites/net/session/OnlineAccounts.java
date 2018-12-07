package t4novel.azurewebsites.net.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import t4novel.azurewebsites.net.models.Account;

public class OnlineAccounts {
	private static Map<Integer, Account> accounts = new ConcurrentHashMap<>();
	
	public static void onLoginAccount(Account ac) {
		accounts.put(ac.getId(), ac);
	}
	public static void onLogoutAccount(Account ac) {
		if(ac != null)
			accounts.remove(ac.getId());
	}
	public static Account getAccount(int accId) {
		return accounts.get(accId);
	}
}
