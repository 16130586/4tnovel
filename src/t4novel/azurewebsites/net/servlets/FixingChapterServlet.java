package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingChapterForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Chap;

/**
 * Servlet implementation class FixingChapterServlet
 */
@WebServlet("/fix-chap")
public class FixingChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FixingChapterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: doget").append(request.getContextPath());
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

		String action = request.getParameter("action");
		Account account = (Account) request.getSession().getAttribute("account");
		Connection cnn = (Connection) request.getAttribute("connection");
		ChapDAO chapDAO = new ChapDAO(cnn);

		if (action.equals("fix-chap")) {
			int chapID = Integer.parseInt(request.getParameter("id-chap"));
			Chap fixingChap = account.getOwnerChap(chapID);
			try {
				if (fixingChap.getContent() == null) {
					fixingChap.setContent(chapDAO.getContentOfChap(fixingChap));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("fixingChap", fixingChap);
			getServletContext().getRequestDispatcher("/jsps/pages/fix-chapter.jsp").forward(request, response);
		} else {
			System.out.println(request.getParameter("in-novel"));
			System.out.println(request.getParameter("in-vol"));
			AbstractMappingForm form = new AddingChapterForm(request);
			if (!form.isOnError()) {
				Chap chapter = (Chap) form.getMappingData();

				try {
					int chapID = Integer.parseInt(request.getParameter("fixingChapID"));
					chapter.setId(chapID);
					chapDAO.updateChap(chapter);
					account.setOwnerChap(chapter);
					Chap fixingChap = account.getOwnerChap(chapID);
					System.out.println("title : " + fixingChap.getTitle());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				form.applyErrorsToUI(request);
			}
			response.sendRedirect("myNovel");
		}
	}
}
