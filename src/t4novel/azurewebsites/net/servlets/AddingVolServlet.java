package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.NextIdGenrator;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingVolForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

/**
 * Servlet implementation class AddingVolServlet
 */
@WebServlet("/add-vol")
public class AddingVolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingVolServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Account hostAccount = (Account) request.getSession().getAttribute("account");
		if (hostAccount.getOwnNovels() == null)
		try {
			Connection cnn = (Connection) request.getAttribute("connection");
			NovelDAO novelDao = new NovelDAO(cnn);
			hostAccount.setOwnNovels(novelDao.getNovelsByUserId(hostAccount.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-vol.jsp").forward(request, response);
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

		AbstractMappingForm form = new AddingVolForm(request);
		if (!form.isOnError()) {
			// TODO writing to db , and something related
			Connection cnn = (Connection) request.getAttribute("connection");

			VolDAO VolDAO = new VolDAO(cnn);
			Vol vol = (Vol) form.getMappingData();
			try {
				cnn.setAutoCommit(false);
				vol.setId(NextIdGenrator.getGenrator().nextAutoIncrementFromTable("VOL", cnn));
				VolDAO.insertVol(vol);
				request.setAttribute("sucessed", "Thêm tập thành công!");
			} catch (Exception e) {
				try {
					cnn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					cnn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			// ram
			Account hostAccount = (Account) request.getSession().getAttribute("account");
			for (Novel novel : hostAccount.getOwnNovels()) {
				if (novel.getId() == vol.getNovelOwnerId()) {
					novel.getVols().add(vol);
					break;
				}
			}
			
		} else {
			form.applyErrorsToUI(request);
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-vol.jsp").forward(request, response);
	}

}
