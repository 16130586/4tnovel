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
import t4novel.azurewebsites.net.DAO.CensoringDAO;
import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.models.Group;

@WebServlet("/manage/admin/dashboard-groups")
public class AdminDashBoardGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDashBoardGroups() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		GroupDAO groupDao = new GroupDAO(cnn);
		try {
			List<Group> groups = groupDao.getAllGroups();
			AccountDAO accDao = new AccountDAO(cnn);
			for (Group g : groups) {
				g.setOwner(accDao.getAccountByID(g.getOwnerId()));
				g.setAccounts(groupDao.getAllMemberFromGroup(g.getId()));
			}
			request.setAttribute("groups", groups);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/jsps/pages/admin-new.translation-groups.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + "  " + Arrays.toString(entry.getValue()));
		}
		Connection cnn = (Connection) request.getAttribute("connection");
		GroupDAO groupDAO = new GroupDAO(cnn);
		CensoringDAO censoringDAO = new CensoringDAO(cnn);
		int groupID = Integer.parseInt(request.getParameter("id"));
		try {
			groupDAO.removeAllMemberFromGroup(groupID);
			groupDAO.deleteGroupByID(groupID);
			censoringDAO.unCensoringAllNovelByGroupID(groupID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
