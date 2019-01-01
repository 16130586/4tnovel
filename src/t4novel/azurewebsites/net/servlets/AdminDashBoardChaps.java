package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.AccountDAO;
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
			ChapDAO chapDao = new ChapDAO(cnn);
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
		String action =  request.getParameter("action");
		Connection cnn = (Connection) request.getAttribute("connection");
		ChapDAO chapDAO = new ChapDAO(cnn);
		if ("fix".equals(action)) {
			int chapID = Integer.parseInt(request.getParameter("id-chap"));
			try {
				Chap fixingChap = chapDAO.getChapByID(chapID);
				request.setAttribute("fixingChap", fixingChap);
				request.setAttribute("admin", 1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/jsps/pages/fix-chapter.jsp").forward(request, response);
		}
	}

}
