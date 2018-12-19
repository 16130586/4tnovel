package t4novel.azurewebsites.net.censoring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import t4novel.azurewebsites.net.DAO.CensoringDAO;
import t4novel.azurewebsites.net.models.Message;
import t4novel.azurewebsites.net.ws.notifycation.EntityAcceptByCensoringMessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.MessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.NewChapterHtmlMessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.NotifycationSystem;

public class CensoringSystem {
	private static LinkedBlockingQueue<CensorEntity> entities;
	private static CensoringSystem instance;
	private CensoringBot bot;

	private CensoringSystem() {
		super();
	}

	public static void init() {
		instance = new CensoringSystem();
		entities = new LinkedBlockingQueue<>();

	}

	public void addNewEntity(CensorEntity entity, Connection cnn) {
		CensoringDAO censorDao = new CensoringDAO(cnn);
		if (entity.isOwnerAutoPassCensoringSystem()) {
			// write success to db
			try {
				censorDao.published(entity.getCensorId(), entity.getOwnerId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// notifycation to user inbox
			MessageBuilder msgBuilder = new EntityAcceptByCensoringMessageBuilder(entity);
			Message msg = msgBuilder.getData();
			NotifycationSystem.notifyToUser(entity.getOwnerId(), msg, cnn);
		}
		// using bot
		else if (isTurnOnCensoringBot()) {
			try {
				CensoringSystem.entities.put(entity);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// manual
		else {
			// write to db
		}
	}

	private boolean isTurnOnCensoringBot() {
		return this.bot != null;
	}

	public static CensoringSystem getSystem() {
		return instance;
	}

	public void setAutoCensoringBot(CensoringBot bot) {
		this.bot = bot;
		this.bot.start();
	}

	public void unresigterBot(CensoringBot bot) {
		if (isTurnOnCensoringBot() && this.bot == bot) {
			this.bot = null;
		}
	}

	public CensorEntity getNeedToCensorEntity() throws InterruptedException {
		return CensoringSystem.entities.take();
	}
}
