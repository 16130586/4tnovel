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
import t4novel.azurewebsites.net.models.Chap;

/**
 * Servlet implementation class SeeNovelServletVer2
 */
@WebServlet("/see2")
public class SeeNovelServletVer2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeeNovelServletVer2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "see2";
		String pageNumber = request.getParameter("page-number");

		Connection cnn = (Connection) request.getAttribute("connection");

		int limit = 30;
		int totalNovels = (int) getServletContext().getAttribute("totalNovels");
		int totalPage = totalNovels % limit > 0 ? (totalNovels / limit) + 1 : (totalNovels / limit);
		// default page number
		if (null == pageNumber)
			pageNumber = "1";
		// bad request
		if (Integer.parseInt(pageNumber) > totalPage) {
			response.sendError(404);
			return;
		}
		// computes offset
		int offSet = (Integer.parseInt(pageNumber) - 1) * limit;

		NovelDAO novelDao = new NovelDAO(cnn);
		ChapDAO chapDao = new ChapDAO(cnn);
		List<Chap> newChaps = null;
		
		try {
			newChaps = chapDao.getLatestChap(offSet, limit);
			for (Chap chap : newChaps) {
				chap.setNovelOwner(novelDao.getNovelById(chap.getNovelOwnerId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("newChaps", newChaps);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", pageNumber);
		// setting put back url
		request.setAttribute("url", url);

		request.getServletContext().getRequestDispatcher("/jsps/pages/new-chaps.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
