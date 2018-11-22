package t4novel.azurewebsites.net.web.container.lifecircle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import t4novel.azurewebsites.net.sercurities.SercureURLEngine;
@WebListener
public class ConfigurationOnLifeContainerLifeCircle implements ServletContextListener {
	private static final String URL_SERCURITY_PATH = "/WEB-INF/url-sercurity.xml";
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
		SercureURLEngine.loadURLPatterns(ev.getServletContext().getRealPath(URL_SERCURITY_PATH));
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
