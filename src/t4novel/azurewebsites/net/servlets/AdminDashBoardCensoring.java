package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.CensoringDAO;
import t4novel.azurewebsites.net.DAO.FollowDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.censoring.CensorEntity;
import t4novel.azurewebsites.net.censoring.MessageBuilderFactory;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Message;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.web.container.lifecircle.ContextHelper;
import t4novel.azurewebsites.net.ws.notifycation.EntityAcceptByCensoringMessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.MessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.NotifycationSystem;

/**
 * Servlet implementation class CencoringServlet
 */
@WebServlet("/manage/admin/dashboard-censoring")
public class AdminDashBoardCensoring extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDashBoardCensoring() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		List<CensorEntity> entities = null;
		try {
			entities = new CensoringDAO(cnn).getAllUnCensoringEntities();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("censorList", entities);

		getServletContext().getRequestDispatcher("/jsps/pages/admin-new.censoring.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idEntity = Integer.parseInt(request.getParameter("id"));
		int isAccept = Integer.parseInt(request.getParameter("isAccept"));
		String stream = request.getParameter("stream");
		if ("chap".equalsIgnoreCase(stream))
			stream = "chapter";
		Connection cnn = (Connection) request.getAttribute("connection");
		CensoringDAO censorDao = new CensoringDAO(cnn);
		CensorEntity censorEntity = null;
		try {
			censorEntity = censorDao.getCensorEntityByIdAndStream(idEntity, stream);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ContextHelper.increasePaginationNumber(censorEntity);
		try {
			if (isAccept == 0)
				censorEntity.setAcceptedByCensorSystem(false);
			if (isAccept == 1)
				censorEntity.setAcceptedByCensorSystem(true);
			censorDao.onCensoringEventUpdate(censorEntity);
			
			MessageBuilder msgBuilder = new EntityAcceptByCensoringMessageBuilder(censorEntity);
			Message msg = msgBuilder.getData();
			NotifycationSystem.notifyToUser(censorEntity.getOwnerAccountId(), msg, cnn);
			
			if (censorEntity.isAccepted()) {
				FollowDAO followDao = new FollowDAO(cnn);
				List<Integer> followersId = followDao.getFollowersId(censorEntity.getOwnerTargetId());
				
				if (!followersId.isEmpty()) {
					NovelDAO novelDao = new NovelDAO(cnn);
					Novel novelOwnerEntity = novelDao.getNovelById(censorEntity.getOwnerTargetId());
					Chap chap = (Chap) censorEntity;
					chap.setNovelOwner(novelOwnerEntity);
					msgBuilder = MessageBuilderFactory.create(chap);
					msg = msgBuilder.getData();
					NotifycationSystem.notifyToListUser(followersId, msg);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
