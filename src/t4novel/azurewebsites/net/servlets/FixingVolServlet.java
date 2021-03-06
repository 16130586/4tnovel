package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingVolForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class FixingVolServlet
 */
@WebServlet("/fix-vol")
public class FixingVolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FixingVolServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Account account = (Account) request.getSession().getAttribute("account");

		int volID = Integer.parseInt(request.getParameter("id-vol"));
		Vol fixingVol = account.getOwnerVol(volID);
		Novel novel = account.getANovel(fixingVol.getNovelOwnerId());
		request.setAttribute("novel", novel);
		request.setAttribute("fixingVol", fixingVol);
		getServletContext().getRequestDispatcher("/jsps/pages/fix-vol.jsp").forward(request, response);

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
		VolDAO volDAO = new VolDAO(cnn);
		String isAdmin = request.getParameter("admin");

//		if ("fix-vol".equals(action)) {
//			int volID = Integer.parseInt(request.getParameter("id-vol"));
//			Vol fixingVol = account.getOwnerVol(volID);
//			Novel novel = account.getANovel(fixingVol.getNovelOwnerId());
//			request.setAttribute("novel", novel);
//			request.setAttribute("fixingVol", fixingVol);
//			getServletContext().getRequestDispatcher("/jsps/pages/fix-vol.jsp").forward(request, response);
//		} else {
			AbstractMappingForm form = new AddingVolForm(request);
			if (!form.isOnError()) {
				Vol fixedVol = (Vol) form.getMappingData();
				try {
					int volID = Integer.parseInt(request.getParameter("fixedVolID"));
					fixedVol.setId(volID);
					volDAO.updateVol(fixedVol);
					if (!"1".equals(isAdmin)) {
						Account account = (Account) request.getSession().getAttribute("account");
						account.setOwnerVol(fixedVol);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				form.applyErrorsToUI(request);
			}
			if (!"1".equals(isAdmin))
				response.sendRedirect("manage/account/dashboard-vols");
			else
				response.sendRedirect("manage/admin/dashboard-vols");
		}
//	}
}
