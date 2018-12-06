package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import t4novel.azurewebsites.net.DAO.InboxDAO;

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

	@OnClose
	public void onCloseConnection(Session session) {
		SESSIONS.remove(currentUserId);
	}

	public static void notifyToUser(int userId, String jsonData, Connection cnn) {
		boolean isPushed = false;
		Session curSession = SESSIONS.get(userId);
		if (curSession != null) {
			try {
				curSession.getBasicRemote().sendText(jsonData);
				isPushed = true;
			} catch (Exception e) {
				isPushed = false;
				System.out.println("canot sending data : " + jsonData + " to userId " + userId);
			}

		}
		// user no longger connection then we have to place in user's inbox
		if (!isPushed) {
			InboxDAO inboxDao = new InboxDAO(cnn);
			try {
				inboxDao.addNewMessage(userId, jsonData);
			} catch (SQLException e) {
				System.out.println("cannot add new message to user inbox - userid :" + userId);
			}
		}
	}

	public static void notifyToListUser(List<Integer> userIds, String jsonData) {
		Thread s = new Thread(new NotificationToListUserHandler(userIds , jsonData));
		s.start();
	}

	public static void scheduleNotificationToListUser(List<Integer> userIds, String jsonData, long period) {
		Thread s = new Thread(new ScheduleNotificationHander(userIds, jsonData, period));
		s.start();
	}

	public static void notifyCationToAllUser(String jsonData) {
		Thread s = new Thread(new NotifyAllHander(jsonData));
		s.start();
	}
}
