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

import t4novel.azurewebsites.net.DAO.CensoredChapDAO;
import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.censoring.CensoringSystem;
import t4novel.azurewebsites.net.censoring.SimpleCensoringBot;
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
		CensoringSystem.getSystem().turnOffCurrentBot();
	}

	@Override
	public void contextInitialized(ServletContextEvent ev) {
		System.out.println("server loading");
		ContextHelper.setContext(ev.getServletContext());

		SercureURLEngine.loadURLPatterns(ev.getServletContext().getRealPath(URL_SERCURITY_PATH));
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/sqlserver");
			ev.getServletContext().setAttribute("datasource", ds);

			// loading pagination
			Connection cnn = ds.getConnection();
			NovelDAO novelDao = new NovelDAO(cnn);
			ChapDAO chapDao = new CensoredChapDAO(cnn);
			//
			int totalNovels = novelDao.getTotalNovels(null);
			int totalChaps = chapDao.getTotalChaps(null);
			System.out.println(totalChaps + " on life cicle");
			//
			ev.getServletContext().setAttribute("totalNovels", totalNovels);
			ev.getServletContext().setAttribute("totalChaps", totalChaps);
			// ending loading pagination
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CensoringSystem.init();
		boolean isUsingCensorintBot = new Boolean(ev.getServletContext().getInitParameter("isUsingCensoringBot"));
		System.out.println("isUsingBot : " + isUsingCensorintBot);
		long censoringPeriodOfBot = Long.parseLong(ev.getServletContext().getInitParameter("censoringPeriodOfBot"));
		System.out.println("period bot: " + censoringPeriodOfBot);
		if (isUsingCensorintBot) {
			CensoringSystem.getSystem()
					.setAutoCensoringBot(new SimpleCensoringBot(CensoringSystem.getSystem(), censoringPeriodOfBot));
		
		}

	}

}
