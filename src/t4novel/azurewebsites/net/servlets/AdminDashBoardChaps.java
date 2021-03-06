package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.DAO.CensoredChapDAO;
import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class AdminDashBoardChaps
 */
@WebServlet("/manage/admin/dashboard-chaps")
public class AdminDashBoardChaps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashBoardChaps() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		NovelDAO novelDao = new NovelDAO(cnn);
		try {
			List<Novel> novels = novelDao.getNovels(null, null, 0, Integer.MAX_VALUE);
			AccountDAO accDao = new AccountDAO(cnn);
			VolDAO volDao = new VolDAO(cnn);
			ChapDAO chapDao = new CensoredChapDAO(cnn);
			GroupDAO groupDao = new GroupDAO(cnn);
			for (Novel n : novels) {
				n.setOwner(accDao.getAccountByID(n.getAccountOwnerId()));
				n.setGroup(groupDao.getGroup(n.getGroupId()));
				n.setVols(volDao.getVolsOfNovel(n.getId()));
				for (Vol v : n.getVols()) {
					v.setChaps(chapDao.getPartOfChapsByVolId(v.getId()));
				}
			}
			request.setAttribute("novels", novels);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/jsps/pages/admin-new.all-chap.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
