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
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class MyNovelServlet
 */
@WebServlet("/myNovel")
public class MyNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyNovelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "/jsps/pages/account-manage-my-novel.jsp";
			// because of lazy loading!
			Account hostAccount = (Account) request.getSession().getAttribute("account");
			Connection cnn = (Connection) request.getAttribute("connection");
			if (hostAccount.getOwnNovels() == null)
				try {
					NovelDAO novelDao = new NovelDAO(cnn);
					hostAccount.setOwnNovels(novelDao.getNovelsByUserId(hostAccount.getId()));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(500);
				}
				// because of making for lazy loading! then we have to loading vols in
				// ownerNovels
				VolDAO volDao = new VolDAO(cnn);
				ChapDAO chapDao = new ChapDAO(cnn);
				for (Novel ownNovel : hostAccount.getOwnNovels()) {
					// dtb
					if (ownNovel.getVols() == null) {
						List<Vol> volsOfCurrentNovel = null;
						try {
							volsOfCurrentNovel = volDao.getVolsOfNovel(ownNovel.getId());
						} catch (Exception e) {
							e.printStackTrace();
						}
						ownNovel.setVols(volsOfCurrentNovel);
					}
					for (Vol vol : ownNovel.getVols()) {
						if (vol.getChaps() == null)
						try {
							vol.setChaps(chapDao.getChapsByVolID(vol.getId()));
							System.out.println(ownNovel.getName() + ": " + vol.getTitle() + ": " + chapDao.getChapsByVolID(vol.getId()).size());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// set attribute size novels
				request.setAttribute("sizeNovels", hostAccount.getOwnNovels().size());
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
