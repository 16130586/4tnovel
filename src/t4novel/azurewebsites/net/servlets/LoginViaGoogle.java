package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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
import t4novel.azurewebsites.net.acessviagoogle.common.GooglePojo;
import t4novel.azurewebsites.net.acessviagoogle.common.GoogleUtils;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.RegisterSocialForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.BookMarkFolder;
import t4novel.azurewebsites.net.session.OnlineAccounts;

/**
 * Servlet implementation class LoginViaGoogle
 */
@WebServlet("/login-google")
public class LoginViaGoogle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginViaGoogle() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String code = request.getParameter("code");
	    String url = "";
		if (code == null || code.isEmpty()) {
	        url = "/jsps/pages/login.jsp";
	      } else {
	        String accessToken = GoogleUtils.getToken(code);
	        GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
	        
			Connection cnn = (Connection) request.getAttribute("connection");
			
			AccountDAO accountDAO = new AccountDAO(cnn);
			FollowDAO followDao = new FollowDAO(cnn);
			InboxDAO inboxDao = new InboxDAO(cnn);
			BookmarkFolderDAO bookmarkFolderDAO = new BookmarkFolderDAO(cnn);
			BookMarkDAO bookmarkDAO = new BookMarkDAO(cnn);
			Account account = null;

			try {
				// check google id da co trong db chua?
				account = accountDAO.getAccountByGoogleID(googlePojo.getId());
				System.out.println(account);
				boolean ggIdExisted = true;
				// neu gg id khong co trong db thi check email xem
				if (account == null) {
					account = accountDAO.getAccountByEmail(googlePojo.getEmail());
					ggIdExisted = false;
				}
				// neu gmail ton tai va gg id khong co -> them gg id vao
				if (account != null && ggIdExisted == false) {
					accountDAO.updateAccountGoogleID(account.getId(), googlePojo.getId());
				}
				// neu gmail khong ton tai thi tao moi account, dua ra form cho nguoi dung nhap
				if (account == null && ggIdExisted == false) {
					request.setAttribute("ggID", googlePojo.getId());
					request.setAttribute("ggName", googlePojo.getName());
					request.setAttribute("ggEmail", googlePojo.getEmail());
					getServletContext().getRequestDispatcher("/jsps/pages/register-google.jsp").forward(request,
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String ggID = request.getParameter("ggID");
			Account account = (Account) userSubmittedForm.getMappingData();

			try {
				accDAO.insertAccount(account);
				account.setId(accDAO.getNextID() - 1);
				accDAO.updateAccountGoogleID(account.getId(), ggID);
			} catch (Exception e) {
				response.sendError(500);
				e.printStackTrace();
			}

			request.getSession().setAttribute("account", account);
			response.sendRedirect("index");
		} else {
			String ggName = request.getParameter("ggName");
			String ggID = request.getParameter("ggID");
			String ggEmail = request.getParameter("ggEmail");
			
			System.out.println(ggName);
			
			request.setAttribute("ggName", ggName);
			request.setAttribute("ggID", ggID);
			request.setAttribute("ggEmail", ggEmail);
			
			url = "/jsps/pages/register-google.jsp";
			userSubmittedForm.applyErrorsToUI(request);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}
}
