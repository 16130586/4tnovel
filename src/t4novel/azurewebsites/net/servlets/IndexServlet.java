package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.CensoredChapDAO;
import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.DAO.InboxDAO;
import t4novel.azurewebsites.net.DAO.LikeDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.ViewDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet({ "", "/index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		Connection cnn = (Connection) request.getAttribute("connection");
		List<Chap> newChaps = null;
		ChapDAO chapDao = new CensoredChapDAO(cnn);
		NovelDAO novelDao = new NovelDAO(cnn);
		GenreDAO genreDao = new GenreDAO(cnn);
		ImageDAO imgDao = new ImageDAO(cnn);
		int limit = Integer.parseInt(getServletContext().getInitParameter("indexLimitChapterPagination"));

		try {
			newChaps = chapDao.getLatestChap(0, limit);
			for (Chap chap : newChaps) {
				Novel novel = novelDao.getNovelById(chap.getNovelOwnerId());
				novel.setCoverImg(novelDao.getEncodeImageById(chap.getNovelOwnerId(), imgDao));
				novel.setGenres(novelDao.getGenres(chap.getNovelOwnerId(), genreDao));
				chap.setNovelOwner(novel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LikeDAO likeDao = new LikeDAO(cnn);
		// loading like and view for newschap
		try {

			for (Chap c : newChaps) {
				c.getNovelOwner().setLike(likeDao.getLike(c.getNovelOwner().getId(), "novel"));
			}
		} catch (Exception e) {
			response.sendError(500);
			return;
		}
		// end loading like and view for newschaps
		request.setAttribute("newChaps", newChaps);

		// loading current read
		Cookie[] cookies = request.getCookies();
		int currentReadChapId = 0;
		if (cookies != null)
			for (Cookie c : cookies) {
				if (c.getName().equals("currentRead")) {
					currentReadChapId = Integer.parseInt(c.getValue());
				}
			}

		Chap currentRead = null;
		if (currentReadChapId != 0) {
			try {
				currentRead = chapDao.getPartOfChapsByChapId(currentReadChapId);
				// make sure never got in case last read is deadth chap(removing by owner)
				if (currentRead != null) {
					Novel novel = novelDao.getNovelById(currentRead.getNovelOwnerId());
					novel.setGenres(novelDao.getGenres(currentRead.getNovelOwnerId(), genreDao));
					currentRead.setNovelOwner(novel);
					request.setAttribute("currentRead", currentRead);
				}
				if (currentRead != null && currentRead.getNovelOwner() != null) {
					currentRead.getNovelOwner().setLike(likeDao.getLike(currentRead.getNovelOwner().getId(), "novel"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// end loading current chap

		Account hostAccount = (Account) request.getSession().getAttribute("account");
		// loading inbox
		if (hostAccount != null) {
			InboxDAO inboxDao = new InboxDAO(cnn);
			try {
				hostAccount.setMessages(inboxDao.getMessagesInInBox(0, 5, hostAccount.getId()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//  wtf is trending... load top 6 novel by view in week
		ViewDAO viewDao = new ViewDAO(cnn);
		List<Novel> newTrendingNovels = new LinkedList<>();
		try {
			for (Integer i : viewDao.getTopViewNovelsId(7, 0, 6)) {
				newTrendingNovels.add(novelDao.getNovelById(i));
			}
			request.setAttribute("newTrendingNovels", newTrendingNovels);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// load top 6 novel by view in month
		List<Novel> monthlyTopNovels = new LinkedList<>();
		try {
			for (Integer i : viewDao.getTopViewNovelsId(30, 0, 6)) {
				monthlyTopNovels.add(novelDao.getNovelById(i));
			}
			request.setAttribute("monthlyTopNovels", monthlyTopNovels);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/jsps/pages/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
