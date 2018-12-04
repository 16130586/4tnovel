package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

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

/**
 * Servlet implementation class ReadServlet
 */
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
			if(acquireChap == null) {response.sendError(404); return;}

			NovelDAO novelDao = new NovelDAO(cnn);
			VolDAO volDao = new VolDAO(cnn);
			Novel ownerNovel = novelDao.getNovelById(acquireChap.getNovelOwnerId());
			
			Vol ownerVol = volDao.getVolByID(acquireChap.getVolOwnerId());
			
			
			if (ownerVol.getChaps() == null)
				ownerVol.setChaps(chapDao.getEntireChapsByVolId(ownerVol.getId()));
			else {
				for(Chap c : ownerVol.getChaps()) {
					if(c.getContent() == null)
						c.setContent(chapDao.getContentOfChap(c));
				}
			}
			ownerVol.setOwnerNovel(ownerNovel);
			acquireChap.setNovelOwner(ownerNovel);
			acquireChap.setVolOwner(ownerVol);
			request.setAttribute("chap", acquireChap);
		} catch (NumberFormatException e) {
			response.sendError(404);
			return;
		} catch (Exception e) {
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
	}

}
