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
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingChapterForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class AddingChapterServlet
 */
@WebServlet("/add-chapter")
public class AddingChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingChapterServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		Account hostAccount = (Account) request.getSession().getAttribute("account");
		// because of lazy loading!
		if (hostAccount.getOwnNovels() == null)
			try {
				Connection cnn = (Connection) request.getAttribute("connection");
				NovelDAO novelDao = new NovelDAO(cnn);
				hostAccount.setOwnNovels(novelDao.getNovelsByUserId(hostAccount.getId()));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		// because of making for lazy loading! then we have to loading vols in
		// ownerNovels
		Connection cnn = (Connection) request.getAttribute("connection");
		VolDAO volDao = new VolDAO(cnn);
		for (Novel ownNovel : hostAccount.getOwnNovels()) {
			List<Vol> volsOfCurrentNovel = volDao.getVolsOfNovel(ownNovel.getId());
			ownNovel.setVols(volsOfCurrentNovel);
			System.out.println("novel " + ownNovel.getName() + " have " + volsOfCurrentNovel.size() + " vols");

		}

		for (Novel own : hostAccount.getOwnNovels()) {
			for (Vol vol : own.getVols()) {
				System.out.println(own.getName() + " have " + vol.getTitle());
			}
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-chapter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AbstractMappingForm form = new AddingChapterForm(request);
		if (!form.isOnError()) {
			// TODO writing to db , and something related
			Connection cnn = (Connection) request.getAttribute("connection");
			ChapDAO chapDAO = new ChapDAO(cnn);
			Chap chapter = (Chap) form.getMappingData();

			chapDAO.insertChap(chapter);
			// set sucessed for user
			request.setAttribute("sucessed", "Adding new chapter done!");
		} else {
			form.applyErrorsToUI(request);
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-vol.jsp").forward(request, response);
	}

}
