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
import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet({"" , "/index"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		Connection cnn = (Connection) request.getAttribute("connection");
		List<Chap> newChaps = null;
		try {
			ChapDAO chapDao = new ChapDAO(cnn);
			NovelDAO novelDao = new NovelDAO(cnn);
			GenreDAO genreDao = new GenreDAO(cnn);
			ImageDAO imgDao = new ImageDAO(cnn);
			Novel novel;
			newChaps = chapDao.getChaps(null, null, 0, 5);
			for (Chap chap : newChaps) {
				novel = novelDao.getNovelById(chap.getNovelOwnerId());
				novel.setCoverImg(novelDao.getEncodeImageById(chap.getNovelOwnerId(), imgDao));
				novel.setGenres(novelDao.getGenres(chap.getNovelOwnerId(), genreDao));
				chap.setNovelOwner(novel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("newChaps", newChaps);
		getServletContext().getRequestDispatcher("/jsps/pages/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
