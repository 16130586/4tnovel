package t4novel.azurewebsites.net.censoring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import t4novel.azurewebsites.net.DAO.CensoringDAO;
import t4novel.azurewebsites.net.DAO.FollowDAO;
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
		try {
			censorDao.insertCensor(entity);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (entity.isOwnerAutoPassCensoringSystem()) {
			entity.setAcceptedByCensorSystem(true);
			try {
				censorDao.onCensoringEventUpdate(entity);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MessageBuilder msgBuilder = new EntityAcceptByCensoringMessageBuilder(entity);
			Message msg = msgBuilder.getData();
			NotifycationSystem.notifyToUser(entity.getOwnerAccountId(), msg, cnn);

			FollowDAO followDao = new FollowDAO(cnn);
			List<Integer> followersId = null;
			try {
				followersId = followDao.getFollowersId(entity.getOwnerTargetId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (!followersId.isEmpty()) {
				msgBuilder = MessageBuilderFactory.create(entity);
				msg = msgBuilder.getData();
				NotifycationSystem.notifyToListUser(followersId, msg);
			}

			System.out.println("adding new censor and notifycation message by autoPass choice");

		}
		// using bot
		else if (isTurnOnCensoringBot()) {
			try {
				CensoringSystem.entities.put(entity);
				System.out.println("adding new censor data by bot choice");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// manual
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

	public void turnOffCurrentBot() {
		if (this.bot != null && this.isTurnOnCensoringBot()) {
			this.bot.end();
		}
	}

	public CensorEntity getNeedToCensorEntity() throws InterruptedException {
		return CensoringSystem.entities.take();
	}
}
