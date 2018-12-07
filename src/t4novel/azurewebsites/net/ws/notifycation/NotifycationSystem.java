package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import t4novel.azurewebsites.net.DAO.InboxDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Message;
import t4novel.azurewebsites.net.session.OnlineAccounts;

@ServerEndpoint(value = "/notify-system/{userid}")
public class NotifycationSystem {
	private int currentUserId;
	private Session currentSession;
	private static final Map<Integer, Session> SESSIONS = Collections.synchronizedMap(new HashMap<>());

	@OnOpen
	public void onOpenConnection(Session session, @PathParam("userid") int userId) {
		this.currentSession = session;
		this.currentUserId = userId;
		SESSIONS.put(currentUserId, currentSession);
	}

	@OnMessage
	public void onMessageComming(Session ss, String msg) {
		System.out.println(msg);
	}

	@OnClose
	public void onCloseConnection(Session session) {
		SESSIONS.remove(currentUserId);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		SESSIONS.remove(currentUserId);
	}

	public static void notifyToUser(int userId, Message message, Connection cnn) {
		Session curSession = SESSIONS.get(userId);
		if (curSession != null) {
			try {
				curSession.getBasicRemote().sendText(message.getContent());
				// write to ram
				Account acc = OnlineAccounts.getAccount(userId);
				if (acc != null) {
					// thoai mai ghi vao nha cau
					acc.addNewMessage(message);
				}
			} catch (Exception e) {
				System.out.println("canot sending data : " + message.getContent() + " to userId " + userId);
			}

		}
		// user no longger connection then we have to place in user's inbox
		InboxDAO inboxDao = new InboxDAO(cnn);
		try {
			inboxDao.addNewMessage(userId, message.getContent());
		} catch (SQLException e) {
			System.out.println("cannot add new message to user inbox - userid :" + userId);
		}
	}

	public static void notifyToListUser(List<Integer> userIds, Message msg) {
		Thread s = new Thread(new NotificationToListUserHandler(userIds, msg));
		s.start();
	}

	public static void scheduleNotificationToListUser(List<Integer> userIds, Message msg, long period) {
		Thread s = new Thread(new ScheduleNotificationHander(userIds, msg, period));
		s.start();
	}

	public static void notifyCationToAllUser(Message msg) {
		Thread s = new Thread(new NotifyAllHander(msg));
		s.start();
	}
}
