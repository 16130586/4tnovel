package t4novel.azurewebsites.net.web.container.lifecircle;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.sercurities.SercureURLEngine;
import t4novel.azurewebsites.net.utils.MailUtils;

@WebListener
public class ConfigurationOnLifeContainerLifeCircle implements ServletContextListener {
	private static final String URL_SERCURITY_PATH = "/WEB-INF/url-sercurity.xml";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// saving idGenrator
		// closing dbConnectionBroker
		MailUtils.killVerifyCodesPool();
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

			// loading pagination
			Connection cnn = ds.getConnection();
			NovelDAO novelDao = new NovelDAO(cnn);
			//
			int totalNovels = novelDao.getTotalNovels(null);
			//
			ev.getServletContext().setAttribute("totalNovels", totalNovels);
			// ending loading pagination
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
