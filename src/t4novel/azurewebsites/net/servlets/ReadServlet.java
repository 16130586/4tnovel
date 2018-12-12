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
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

@WebServlet("/read")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int chapId = Integer.parseInt(request.getParameter("id"));
			Connection cnn = (Connection) request.getAttribute("connection");

			ChapDAO chapDao = new ChapDAO(cnn);
			Chap acquireChap = chapDao.getChapByID(chapId);
			if (acquireChap == null) {
				response.sendError(404);
				return;
			}

			NovelDAO novelDao = new NovelDAO(cnn);
			VolDAO volDao = new VolDAO(cnn);

			Novel ownerNovel = acquireChap.getNovelOwner();
			Vol ownerVol = acquireChap.getVolOwner();

			if (ownerNovel == null) {
				ownerNovel = novelDao.getNovelById(acquireChap.getNovelOwnerId());
				acquireChap.setNovelOwner(ownerNovel);
			}
			if (ownerVol == null) {
				ownerVol = volDao.getVolByID(acquireChap.getVolOwnerId());
				ownerVol.setOwnerNovel(ownerNovel);
				acquireChap.setVolOwner(ownerVol);
			}
			if (ownerVol.getChaps() == null) {
				List<Chap> chaps = chapDao.getPartOfChapsByVolId(ownerVol.getId());
				ownerVol.setChaps(chaps);
				acquireChap.setContent(chapDao.getContentOfChap(acquireChap));
			} else if (acquireChap.getContent() == null) {
				acquireChap.setContent(chapDao.getContentOfChap(acquireChap));
			}

			request.setAttribute("chap", acquireChap);
			response.setStatus(200);
		} catch (NumberFormatException e) {
			response.setStatus(404);
			response.sendError(404);
			return;
		} catch (Exception e) {
			response.setStatus(500);
			response.sendError(500);
			return;
		}
		getServletContext().getRequestDispatcher("/jsps/pages/chap.jsp").forward(request, response);
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
