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
import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class SeeNovelKindServlet
 */
@WebServlet("/see")
public class SeeNovelKindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SeeNovelKindServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String writtingBy = request.getParameter("author");
		String pageNumber = request.getParameter("page-number");
		String url = "see";

		// loading from servlet Context

		int limit = Integer.parseInt(getServletContext().getInitParameter("limitPagination"));
		int totalPage = -1;

		Connection cnn = (Connection) request.getAttribute("connection");
		NovelDAO novelDao = new NovelDAO(cnn);
		List<Novel> results = null;

		try {
			if ("user".equals(writtingBy)) {
				int novelAcceptedFilter = novelDao.getTotalNovels("kind=\'" + NovelKind.COMPOSE.name() + "\'");
				totalPage = novelAcceptedFilter % limit > 0 ? (novelAcceptedFilter / limit) + 1 : (novelAcceptedFilter / limit);
			} else if ("writer".equals(writtingBy)) {
				int novelAcceptedFilter = novelDao.getTotalNovels("kind=\'" + NovelKind.TRANSLATE.name() + "\'");
				totalPage = novelAcceptedFilter % limit > 0 ? (novelAcceptedFilter / limit) + 1 : (novelAcceptedFilter / limit);
			}

			else {
				int totalNovels = (int) getServletContext().getAttribute("totalNovels");
				//
				int maxPaging = totalNovels % limit > 0 ? (totalNovels / limit) + 1
						: (totalNovels / limit);
				totalPage = maxPaging ;
			}
		} catch (Exception e) {
			response.sendError(500); return;
		}
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

		try {
			if ("user".equals(writtingBy)) {
				results = novelDao.getNovels("DATEUP DESC", "kind=\'" + NovelKind.COMPOSE.name() + "\'", offSet, limit);
				// setting put back url
				url += "?author=user";
			} else if ("writer".equals(writtingBy)) {
				results = novelDao.getNovels("DATEUP DESC", "kind=\'" + NovelKind.TRANSLATE.name() + "\'", offSet,
						limit);
				// setting put back url
				url += "?author=writer";
			}

			else if ("all".equals(writtingBy)) {
				results = novelDao.getNovels("DATEUP DESC", null, offSet, limit);
				url += "?author=all";
			}
			GenreDAO genreDao = new GenreDAO(cnn);
			VolDAO volDao = new VolDAO(cnn);
			ChapDAO chapDao  = new ChapDAO(cnn);
			for (Novel curNovel : results) {
				if(curNovel.getGenres() == null)
					curNovel.setGenres(genreDao.getGenres(curNovel.getId()));
				if(curNovel.getVols() == null) {
					curNovel.setVols(volDao.getVolsOfNovel(curNovel.getId()));
					for(Vol curVol : curNovel.getVols()) {
						if(curVol.getChaps() == null)
							curVol.setChaps(chapDao.getPartOfChapsByVolId(curVol.getId()));
					}
				}
				
					
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("searchResultNovel", results);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", pageNumber);

		// setting put back url
		request.setAttribute("url", url);
		request.getServletContext().getRequestDispatcher("/jsps/pages/search-result.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

}
