package t4novel.azurewebsites.net.servlets.ajax;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Message;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.utils.EnumAdapterFactory;

@WebServlet("/ajax-lastest-update")
public class LastestUpdateChapterAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LastestUpdateChapterAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int pageNumber = Integer.parseInt(request.getParameter("page-number"));
			System.out.println("somebody want to get more at page : " + pageNumber);

			Connection cnn = (Connection) request.getAttribute("connection");
			List<Chap> newChaps = null;
			ChapDAO chapDao = new ChapDAO(cnn);
			NovelDAO novelDao = new NovelDAO(cnn);
			GenreDAO genreDao = new GenreDAO(cnn);
			ImageDAO imgDao = new ImageDAO(cnn);

			newChaps = chapDao.getChaps(null, null, pageNumber * 5, 5);
			for (Chap chap : newChaps) {
				Novel novel = novelDao.getNovelById(chap.getNovelOwnerId());
				novel.setCoverImg(novelDao.getEncodeImageById(chap.getNovelOwnerId(), imgDao));
				novel.setGenres(novelDao.getGenres(chap.getNovelOwnerId(), genreDao));
				chap.setNovelOwner(novel);
			}
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapterFactory(new EnumAdapterFactory());
			Gson gson = builder.create();
			Type type = new TypeToken<List<Chap>>() {
			}.getType();
			String json = gson.toJson(newChaps, type);
			response.setStatus(200);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter netOut = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"), true);
			netOut.println(json);
			netOut.close();
		} catch (NumberFormatException e) {
			System.out.println("bad requesting next lastest update page");
			response.sendError(404);
			return;
		} catch (Exception e) {
			response.sendError(500);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

}
