package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.DAO.FollowDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.censoring.CensoringSystem;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingChapterForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Message;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;
import t4novel.azurewebsites.net.ws.notifycation.MessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.NewChapterHtmlMessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.NotifycationSystem;

/**
 * Servlet implementation class AddingChapterServlet
 */
@WebServlet("/add-chapter")
public class AddingChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingChapterServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Account hostAccount = (Account) request.getSession().getAttribute("account");
		Connection cnn = (Connection) request.getAttribute("connection");
		// because of lazy loading!
		if (hostAccount.getOwnNovels() == null)
			try {
				NovelDAO novelDao = new NovelDAO(cnn);
				hostAccount.setOwnNovels(novelDao.getNovelsByUserId(hostAccount.getId()));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		// because of making for lazy loading! then we have to loading vols in
		// ownerNovels
		VolDAO volDao = new VolDAO(cnn);
		ChapDAO chapDao = new ChapDAO(cnn);
		try {
			for (Novel ownNovel : hostAccount.getOwnNovels()) {

				if (ownNovel.getVols() == null) {
					ownNovel.setVols(volDao.getVolsOfNovel(ownNovel.getId()));
				}
				for (Vol vol : ownNovel.getVols()) {
					if (vol.getChaps() == null)
						vol.setChaps(chapDao.getPartOfChapsByVolId(vol.getId()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-chapter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		AbstractMappingForm form = new AddingChapterForm(request);
		Account hostAccount = (Account) request.getSession().getAttribute("account");
		if (!form.isOnError()) {
			Connection cnn = (Connection) request.getAttribute("connection");
			ChapDAO chapDAO = new ChapDAO(cnn);
			Chap chapter = (Chap) form.getMappingData();

			NovelDAO novelDao = new NovelDAO(cnn);

			// write data to database
			try {
				chapter.setNovelOwner(novelDao.getNovelById(chapter.getNovelOwnerId()));
				chapter.getNovelOwner().setOwner(hostAccount);
				chapter.setId(chapDAO.getNextID());
				chapDAO.insertChap(chapter);

				// notification to followers
//				FollowDAO followDao = new FollowDAO(cnn);
//				List<Integer> followersId = followDao.getFollowersId(chapter.getNovelOwnerId());
//				if(!followersId.isEmpty()) {
//					MessageBuilder msgBuilder = new NewChapterHtmlMessageBuilder(chapter);	
//					Message msg = msgBuilder.getData();
//					NotifycationSystem.notifyToListUser(followersId, msg);
//				}
				CensoringSystem.getSystem().addNewEntity(chapter, cnn);
			} catch (Exception e) {
				e.printStackTrace();
			}

			hostAccount.addNewOwnerChap(chapter);

			// set sucessed for user
			request.setAttribute("sucessed", "Thêm chương thành công!");
		} else {
			form.applyErrorsToUI(request);
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-chapter.jsp").forward(request, response);
	}

}
