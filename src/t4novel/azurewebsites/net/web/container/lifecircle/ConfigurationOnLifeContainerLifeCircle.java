package t4novel.azurewebsites.net.web.container.lifecircle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
@WebListener
public class ConfigurationOnLifeContainerLifeCircle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// saving idGenrator
		// closing dbConnectionBroker

	}

	@Override
	public void contextInitialized(ServletContextEvent ev) {
		// loading idGenrator
		// loading dbConnectionBroker
		System.out.println("server loading");
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/sqlserver");
			ev.getServletContext().setAttribute("datasource", ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
