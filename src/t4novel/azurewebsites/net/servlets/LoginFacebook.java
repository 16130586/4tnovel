package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restfb.types.User;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.DAO.BookMarkDAO;
import t4novel.azurewebsites.net.DAO.BookmarkFolderDAO;
import t4novel.azurewebsites.net.DAO.FollowDAO;
import t4novel.azurewebsites.net.DAO.InboxDAO;
import t4novel.azurewebsites.net.DAOService.EmailCheckingService;
import t4novel.azurewebsites.net.DAOService.UserNameCheckingService;
import t4novel.azurewebsites.net.acessviafacebook.common.RestFB;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.RegisterSocialForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.BookMarkFolder;
import t4novel.azurewebsites.net.session.OnlineAccounts;

/**
 * Servlet implementation class LoginFacebook
 */
@WebServlet("/login-facebook")
public class LoginFacebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginFacebook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			response.sendRedirect("login");
		} else {
			String accessToken = RestFB.getToken(code);
			User user = RestFB.getUserInfo(accessToken);

			Connection cnn = (Connection) request.getAttribute("connection");
			AccountDAO accountDAO = new AccountDAO(cnn);

			FollowDAO followDao = new FollowDAO(cnn);
			InboxDAO inboxDao = new InboxDAO(cnn);
			BookmarkFolderDAO bookmarkFolderDAO = new BookmarkFolderDAO(cnn);
			BookMarkDAO bookmarkDAO = new BookMarkDAO(cnn);
			Account account = null;

			try {
				// check facebook id da co trong db chua?
				account = accountDAO.getAccountByFacebookID(user.getId());
				boolean fbIdExisted = true;
				// neu fb id khong co trong db thi check email xem
				if (account == null) {
					account = accountDAO.getAccountByEmail(user.getEmail());
					fbIdExisted = false;
				}
				// neu gmail ton tai va fb id khong co -> them fb id vao
				if (account != null && fbIdExisted == false) {
					accountDAO.updateAccountFacebookID(account.getId(), user.getId());
				}
				// neu gmail khong ton tai thi tao moi account, dua ra form cho nguoi dung nhap
				if (account == null && fbIdExisted == false) {
					request.setAttribute("fbID", user.getId());
					request.setAttribute("fbName", user.getName());
					request.setAttribute("fbEmail", user.getEmail());
					getServletContext().getRequestDispatcher("/jsps/pages/register-facebook.jsp").forward(request,
							response);
				} else {
					// tien hanh dang nhap neu fb id co hoac gmail co ton tai trong db
					List<BookMarkFolder> bookmarkFolder = bookmarkFolderDAO.getBookmarkFolderByUser(account.getId());
					for (BookMarkFolder bmf : bookmarkFolder) {
						bmf.setBookMarks(bookmarkDAO.getBookmarkByFolder(bmf.getId()));
					}
					account.setBookMarkFolders(bookmarkFolder);
					account.setNovelFollowsId(followDao.getNovelFollow(account.getId()));
					account.setMessages(inboxDao.getMessagesInInBox(0, 5, account.getId()));
					HttpSession session = request.getSession();
					session.setAttribute("account", account);
					OnlineAccounts.onLoginAccount(account);
					response.sendRedirect("index");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		Connection cnn = (Connection) request.getAttribute("connection");
		AbstractMappingForm userSubmittedForm = new RegisterSocialForm(request, new EmailCheckingService(cnn),
				new UserNameCheckingService(cnn));

		String url = "";
		if (!userSubmittedForm.isOnError()) {
			AccountDAO accDAO = new AccountDAO(cnn);
			url = "/index";
			String fbID = request.getParameter("fbID");
			Account account = (Account) userSubmittedForm.getMappingData();

			try {
				accDAO.insertAccount(account);
				account.setId(accDAO.getNextID() - 1);
				accDAO.updateAccountFacebookID(account.getId(), fbID);
			} catch (Exception e) {
				response.sendError(500);
				e.printStackTrace();
			}

			request.getSession().setAttribute("account", account);
			response.sendRedirect("index");
		} else {
			String fbName = request.getParameter("fbName");
			String fbID = request.getParameter("fbID");
			String fbEmail = request.getParameter("fbEmail");
			
			request.setAttribute("fbName", fbName);
			request.setAttribute("fbID", fbID);
			request.setAttribute("fbEmail", fbEmail);
			
			url = "/jsps/pages/register-facebook.jsp";
			userSubmittedForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}

}
