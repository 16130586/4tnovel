package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

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
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

@WebServlet("/manage/account/dashboard-novels")
public class AccountDashBoardNovels extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountDashBoardNovels() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		NovelDAO novelDao = new NovelDAO(cnn);
		List<Novel> novels = null;
		Account hostAcc = (Account) request.getSession().getAttribute("account");
		try {
			
			if (hostAcc.getOwnNovels() == null) {
				 novels = novelDao.getNovels(null, "IDOWNER=" + hostAcc.getId(), 0, Integer.MAX_VALUE);
				AccountDAO accDao = new AccountDAO(cnn);
				VolDAO volDao = new VolDAO(cnn);
				ChapDAO chapDao = new ChapDAO(cnn);
				GroupDAO groupDao = new GroupDAO(cnn);
				for (Novel n : novels) {
					hostAcc.addNewOwnerNovel(n);
					n.setOwner(accDao.getAccountByID(n.getAccountOwnerId()));
					n.setGroup(groupDao.getGroup(n.getGroupId()));
					n.setVols(volDao.getVolsOfNovel(n.getId()));
					for (Vol v : n.getVols()) {
						v.setChaps(chapDao.getPartOfChapsByVolId(v.getId()));
					}
				}
			}
			else {
				novels = hostAcc.getOwnNovels();
			}
			request.setAttribute("novels", novels);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/jsps/pages/account-new.all-novel.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + "  " + Arrays.toString(entry.getValue()));
		}
		doGet(request, response);
	}

}
