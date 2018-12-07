package t4novel.azurewebsites.net.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import t4novel.azurewebsites.net.models.Account;

/**
 * Application Lifecycle Listener implementation class OnAutoDestroySession
 *
 */
@WebListener
public class OnAutoDestroySession implements HttpSessionListener {

    public OnAutoDestroySession() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event)  { 
    	Account logoutAcc = (Account) event.getSession().getAttribute("account");
    	if(logoutAcc != null) {
    		OnlineAccounts.onLogoutAccount(logoutAcc);
    	}
    }
	
}
