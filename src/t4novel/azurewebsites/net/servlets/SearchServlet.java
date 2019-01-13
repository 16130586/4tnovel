package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelGenre;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String url = "/jsps/pages/search.jsp";
		String pageNumber = request.getParameter("page-number");

		if ("advanced".equals(type))
			url = "/jsps/pages/search-advanced.jsp";
		if (pageNumber != null) {
			doPost(request, response);
		} else {
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection cnn = (Connection) request.getAttribute("connection");

		String input = request.getParameter("input");
		String type = request.getParameter("type");
		String pageNumber = request.getParameter("page-number");
		String pushBackUrl = "search";
		String query = (String) request.getSession().getAttribute("search-query");

		NovelDAO novelDAO = new NovelDAO(cnn);
		VolDAO volDAO = new VolDAO(cnn);
		GenreDAO genreDAO = new GenreDAO(cnn);
		LinkedList<Novel> novelList = new LinkedList<>();
		int maxPage = 0;
		int limit = Integer.parseInt(getServletContext().getInitParameter("searchLimitPagination"));

		if (type.equals("normal") && pageNumber == null) {
			try {
				query = novelDAO.generateQueryForSearching("normal", input, null, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("search-query", query);
		}
		if (!type.equals("normal") && pageNumber == null) {
			String searchKind = request.getParameter("kind");
			String searchStatus = request.getParameter("status");
			String[] genre = request.getParameterValues("genre");

			try {
				novelDAO.generateQueryForSearching("advanced", input, searchStatus, searchKind, genre);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("search-query", query);
		}
		if (pageNumber == null) {
			pageNumber = "1";
		}
		if (type.equals("normal")) {
			pushBackUrl = "?type=normal";
		} else {
			pushBackUrl = "?type=advanced";
		}
		try {
			novelList = (LinkedList<Novel>) novelDAO.searchNovelsByQuery(query, Integer.parseInt(pageNumber) - 1,
					limit);
			maxPage = novelDAO.countNovelsByQuery(query);
			for (Novel novel : novelList) {
				LinkedList<Vol> volList = (LinkedList<Vol>) volDAO.getVolsOfNovel(novel.getId());
				LinkedList<NovelGenre> genreList = (LinkedList<NovelGenre>) novelDAO.getGenres(novel.getId(), genreDAO);
				novel.setVols(volList);
				novel.setGenres(genreList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int maxPaging = maxPage % limit > 0 ? (maxPage / limit) + 1 : (maxPage / limit);

		request.setAttribute("novelList", novelList);
		request.setAttribute("totalPage", maxPaging);
		request.setAttribute("currentPage", pageNumber);
		request.setAttribute("url", pushBackUrl);

		getServletContext().getRequestDispatcher("/jsps/pages/list-novel.jsp").forward(request, response);
	}

}
