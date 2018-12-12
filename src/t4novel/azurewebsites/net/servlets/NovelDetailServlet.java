package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.DAO.LikeDAO;
import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class NovelDetailServlet
 */
@WebServlet("/detail")
public class NovelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NovelDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("detail doGet");
		int novelId = -1;
		try {
			novelId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.setStatus(404);
			response.sendError(404);
			return;
		}
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accDao = new AccountDAO(cnn);
		NovelDAO novelDao = new NovelDAO(cnn);
		GenreDAO genreDao = new GenreDAO(cnn);
		VolDAO volDao = new VolDAO(cnn);
		ChapDAO chapDao = new ChapDAO(cnn);
		ImageDAO imgDAO = new ImageDAO(cnn);
		Novel requestNovel = null;
		boolean isLikeThisNovel = false;

		try {
			requestNovel = novelDao.getNovelById(novelId);
			if (requestNovel == null) {
				response.sendError(404);
				return;
			}

			if (requestNovel.getGenres() == null) {
				requestNovel.setGenres(genreDao.getGenres(novelId));
			}
			if (requestNovel.getOwner() == null) {
				Account owner = accDao.getAccountByID(requestNovel.getAccountOwnerId());
				requestNovel.setOwner(owner);
			}
			if (requestNovel.getVols() == null) {
				List<Vol> vols = volDao.getVolsOfNovel(novelId);
				requestNovel.setVols(vols);
				for (Vol vol : vols) {
					if (vol.getChaps() == null) {
						List<Chap> chaps = chapDao.getPartOfChapsByVolId(vol.getId());
						vol.setChaps(chaps);
					}
				}
			}
			if (requestNovel.getCoverImg() == null)
				requestNovel.setCoverImg(novelDao.getEncodeImageById(requestNovel.getId(), imgDAO));

			Account ac = (Account) request.getSession().getAttribute("account");
			if (ac != null) {
				LikeDAO likeDao = new LikeDAO(cnn);
				isLikeThisNovel = likeDao.isLike(ac, novelId, "novel");
				requestNovel.setLike(likeDao.getLike(requestNovel.getId() , "novel"));
			}
			response.setStatus(200);
			request.setAttribute("novel", requestNovel);
			request.setAttribute("isLikeThisNovel", isLikeThisNovel);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			response.setStatus(500);
			response.sendError(500);
			return;
		}

		getServletContext().getRequestDispatcher("/jsps/pages/novel-details.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
		return;
	}

}
