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
		if ("advanced".equals(type))
			url = "/jsps/pages/search-advanced.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String input = request.getParameter("input");
		String type = request.getParameter("type");

		Connection cnn = (Connection) request.getAttribute("connection");
		NovelDAO novelDAO = new NovelDAO(cnn);
		VolDAO volDAO = new VolDAO(cnn);
		GenreDAO genreDAO = new GenreDAO(cnn);
		LinkedList<Novel> novelList = null;

		try {
			novelList = (LinkedList<Novel>) novelDAO.searchNovelsByNamePattern(input);
			if (type.equals("normal")) {
				for (Novel novel : novelList) {
					LinkedList<Vol> volList = (LinkedList<Vol>) volDAO.getVolsOfNovel(novel.getId());
					LinkedList<NovelGenre> genreList = (LinkedList<NovelGenre>) novelDAO.getGenres(novel.getId(),
							genreDAO);
					novel.setVols(volList);
					novel.setGenres(genreList);
				}
			} else {
				String[] genre = request.getParameterValues("genre");
				String searchKind = request.getParameter("kind");
				String searchStatus = request.getParameter("status");

				novelListChecking: for (int i = 0; i < novelList.size(); i++) {
					Novel novel = novelList.get(i);
					LinkedList<NovelGenre> genreList = (LinkedList<NovelGenre>) novelDAO.getGenres(novel.getId(),
							genreDAO);
					if (!novel.getStatus().toText().equalsIgnoreCase(searchStatus) && !searchStatus.equals("all")) {
						novelList.remove(i);
						i--;
						continue novelListChecking;
					}
					if (!novel.getKind().toText().equalsIgnoreCase(searchKind) && !searchKind.equals("all")) {
						novelList.remove(i);
						i--;
						continue novelListChecking;
					}
					if (genre != null) {
						for (NovelGenre novelGenre : genreList) {
							for (String searchGenre : genre) {
								if (novelGenre.getDisplayName().equalsIgnoreCase(searchGenre)) {
									LinkedList<Vol> volList = (LinkedList<Vol>) volDAO.getVolsOfNovel(novel.getId());
									novel.setVols(volList);
									novel.setGenres(genreList);
									continue novelListChecking;
								}
							}
						}
						novelList.remove(i);
						i--;
					}
					LinkedList<Vol> volList = (LinkedList<Vol>) volDAO.getVolsOfNovel(novel.getId());
					novel.setVols(volList);
					novel.setGenres(genreList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("searchResultNovel", novelList);
		getServletContext().getRequestDispatcher("/jsps/pages/search-result.jsp").forward(request, response);
		// lấy ra danh sách 20 phần tử đâu tiên từ query theo params trên
		// >> đẩy vào request danh sách đó và chuyển hướng sang trang hiện kết quả tìm
		// kiếm : search-result.jsp
	}

}
