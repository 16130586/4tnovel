package t4novel.azurewebsites.net.censoring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import t4novel.azurewebsites.net.DAO.CensoringDAO;
import t4novel.azurewebsites.net.DAO.FollowDAO;
import t4novel.azurewebsites.net.models.Message;
import t4novel.azurewebsites.net.web.container.lifecircle.ContextHelper;
import t4novel.azurewebsites.net.ws.notifycation.EntityAcceptByCensoringMessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.MessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.NotifycationSystem;

public abstract class AbstractBot implements CensoringBot, Runnable {
	protected CensoringSystem censorSystem;
	protected long period;
	protected Thread runningThread;
	protected boolean stillRunning = true;
	protected Context initContext;
	protected Context envContext;
	protected DataSource ds;
	protected CensoringDAO censorDao;

	public AbstractBot(CensoringSystem system, long period) {
		this.censorSystem = system;
		this.period = period;
		try {
			initContext = new InitialContext();
			envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/sqlserver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public abstract boolean censor(CensorEntity entity);

	@Override
	public void run() {
		while (stillRunning) {
			try {
				CensorEntity en = censorSystem.getNeedToCensorEntity();
				if (en == null) {
					Thread.sleep(period);
					continue;
				}
				boolean isEntityPass = censor(en);
				Connection cnn = null;
				try {
					cnn = ds.getConnection();
					censorDao = new CensoringDAO(cnn);
				} catch (SQLException e) {
					Thread.sleep(period /4);
					continue;
				}
				// basing on isEntityPass case then build message then notify to user and
				// writting to db
				en.setAcceptedByCensorSystem(isEntityPass);
				try {
					censorDao.onCensoringEventUpdate(en);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				MessageBuilder msgBuilder = new EntityAcceptByCensoringMessageBuilder(en);
				Message msg = msgBuilder.getData();
				NotifycationSystem.notifyToUser(en.getOwnerAccountId(), msg, cnn);

				if (en.isAccepted()) {
					ContextHelper.increasePaginationNumber(en);
					FollowDAO followDao = new FollowDAO(cnn);
					List<Integer> followersId = null;
					try {
						followersId = followDao.getFollowersId(en.getOwnerTargetId());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (!followersId.isEmpty()) {
						msgBuilder = MessageBuilderFactory.create(en);
						msg = msgBuilder.getData();
						NotifycationSystem.notifyToListUser(followersId, msg);
					}
					
				}
				cnn.close();
				Thread.sleep(period);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void start() {
		runningThread = new Thread(this);
		runningThread.start();
	}

	@Override
	public void end() {
		stillRunning = false;
		censorSystem.unresigterBot(this);
	}
}
