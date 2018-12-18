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
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class SeeNovelServletVer3
 */
@WebServlet("/see3")
public class SeeNovelServletVer3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeeNovelServletVer3() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String query = "";
		String searchKind = request.getParameter("kind");
		String searchStatus = request.getParameter("status");
		String searchGenre = request.getParameter("genre");
		String pageNumber = request.getParameter("page-number");
		String pushBackUrl = "";
		String searchString = "Tất cả";

		int limit = Integer.parseInt(getServletContext().getInitParameter("viewLimitPagination"));
		int maxPage = -1;

		if (pageNumber == null)
			pageNumber = "1";

		if (searchKind == null)
			searchKind = "all";

		if (searchStatus == null)
			searchStatus = "all";

		if (searchGenre == null)
			searchGenre = "all";

		pushBackUrl = "?status=" + searchStatus + "&kind=" + searchKind + "&genre=" + searchGenre + "&";
		request.setAttribute("kind", searchKind);
		request.setAttribute("status", searchStatus);
		request.setAttribute("genre", searchGenre);
		// create query
		if (searchKind.equals("all")) {
			searchKind = "(S.KIND = 'COMPOSE' OR S.KIND = 'TRANSLATE')";
		} else {
			searchString = NovelKind.getNovelKind(searchKind).getDisplayedName();
			searchKind = "(S.KIND = '" + searchKind + "')";
		}

		if (searchStatus.equals("all")) {
			searchStatus = "(S.STATUS = 0 OR S.STATUS = 1 OR S.STATUS = 2)";
		} else {
			searchString = NovelStatus.getNovelStatus(Integer.parseInt(searchStatus)).toText();
			searchStatus = "(S.STATUS = '" + searchStatus + "')";
		}

		if (!searchGenre.equals("all")) {
			searchString = NovelGenre.getGenre(Integer.parseInt(searchGenre)).getDisplayName();
			searchGenre = "AND (GENRE.VALUE=" + searchGenre + ")";
		} else {
			searchGenre = "";
		}
		query = "SELECT * FROM LN INNER JOIN (SELECT DISTINCT ID FROM (select * from LN) S  INNER JOIN GENRE ON GENRE.IDNOVEL = S.ID WHERE "
				+ searchKind + " AND " + searchStatus + searchGenre + " ) A ON A.ID = LN.ID";

		Connection cnn = (Connection) request.getAttribute("connection");
		NovelDAO novelDAO = new NovelDAO(cnn);
		VolDAO volDAO = new VolDAO(cnn);
		GenreDAO genreDAO = new GenreDAO(cnn);
		LinkedList<Novel> novelList = new LinkedList<>();

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

		// set atribute to create link doGet
		request.setAttribute("searchString", searchString);
		request.setAttribute("novelList", novelList);
		request.setAttribute("totalPage", maxPaging);
		request.setAttribute("currentPage", pageNumber);
		request.setAttribute("url", pushBackUrl);

		getServletContext().getRequestDispatcher("/jsps/pages/list-novel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
