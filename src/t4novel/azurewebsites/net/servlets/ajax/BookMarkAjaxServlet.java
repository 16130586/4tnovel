package t4novel.azurewebsites.net.servlets.ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.BookMarkDAO;
import t4novel.azurewebsites.net.DAO.BookmarkFolderDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.BookMark;
import t4novel.azurewebsites.net.models.BookMarkFolder;

/**
 * Servlet implementation class BookMarkAjaxServlet
 */
@WebServlet("/bookmark")
public class BookMarkAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMarkAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		String url = request.getParameter("url");
		System.out.println(title);
		System.out.println(detail);
		System.out.println(url);
		
		Connection cnn = (Connection) request.getAttribute("connection");
		Account account = (Account) request.getSession().getAttribute("account");
		BookMarkDAO bmDao = new BookMarkDAO(cnn);
		List<BookMarkFolder> bmFolders = account.getBookMarkFolders();
		BookMarkFolder bmFolder;
		if (bmFolders.isEmpty()) {
			BookmarkFolderDAO bmFolderDao = new BookmarkFolderDAO(cnn);
			bmFolder = new BookMarkFolder();
			bmFolder.setAccountOnwerID(account.getId());
			bmFolder.setTitle("folder");
			bmFolders.add(bmFolder);
			try {
				bmFolderDao.insertBookmarkFolder(bmFolder);
				bmFolder.setId(bmFolderDao.getNextId()-1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			bmFolder = bmFolders.get(0);
		BookMark bm = new BookMark();
		bm.setTitle(title);
		bm.setDetail(detail);
		bm.setUrl(url);
		bm.setBookmarkFolderId(bmFolder.getId());
		List<BookMark> bms = new LinkedList<>();
		bms.add(bm);
		bmFolder.getBookMarks().add(0, bm);
		try {
			bmDao.insertBookmark(bm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
