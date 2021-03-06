package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingNovelForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;

/**
 * Servlet implementation class FixingNovelServlet
 */
@WebServlet("/fix-novel")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 4, // 4MB
		maxFileSize = 1024 * 1024 * 100, // 100MB
		maxRequestSize = 1024 * 1024 * 100) // 100MB
public class FixingNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FixingNovelServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		int novelID = Integer.parseInt(request.getParameter("id-novel"));
		Novel fixingNovel = null;
		try {
			fixingNovel = new NovelDAO(cnn).getNovelById(novelID);
			GroupDAO groupDao = new GroupDAO(cnn);
			fixingNovel.setGroup(groupDao.getGroup(fixingNovel.getGroupId()));
			fixingNovel.setGenres(new GenreDAO(cnn).getGenres(novelID));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		request.setAttribute("fixingNovel", fixingNovel);
		getServletContext().getRequestDispatcher("/jsps/pages/fix-novel.jsp").forward(request, response);
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

		Account account = (Account) request.getSession().getAttribute("account");
		Connection cnn = (Connection) request.getAttribute("connection");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<String> genres = new LinkedList<>();
		String tmp;
		try {
			List<FileItem> listFiles = upload.parseRequest(request);
			for (FileItem fileItem : listFiles) {
				if (fileItem.isFormField()) {
					tmp = new String(fileItem.get(), "utf-8");
					if ("genre".equals(fileItem.getFieldName()))
						genres.add(tmp);
					else
						request.setAttribute(fileItem.getFieldName(), tmp);
				}
				if (!fileItem.isFormField())
					request.setAttribute("fileImage", fileItem);

			}
			request.setAttribute("genres", genres);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		AbstractMappingForm form = new AddingNovelForm(request);
		if (!form.isOnError()) {
			NovelDAO novelDAO = new NovelDAO(cnn);
			ImageDAO imgDAO = new ImageDAO(cnn);
			GenreDAO genreDAO = new GenreDAO(cnn);
			Novel fixedNovel = (Novel) form.getMappingData();

			try {
				String novelId = (String) request.getAttribute("fixedNovelID");
				//
				// cai cu con trong ram
				//

				Novel oldNovel = novelDAO.getNovelById(Integer.parseInt(novelId)); // neu con trong ram -> update

				// new logic
				FileItem fileImage = (FileItem) request.getAttribute("fileImage");
				if (fileImage.getInputStream().available() > 0) {
					imgDAO.updateImage(fileImage.getInputStream() , oldNovel.getCoverId());
					fixedNovel.setCoverId(oldNovel.getCoverId());
				}
				// end new logic
				oldNovel.update(fixedNovel);
				// update dtb
				novelDAO.updateNovel(oldNovel);
				novelDAO.updateGenres(oldNovel.getId(), oldNovel.getGenres(), genreDAO);

				// old logic
//				if (fileImage != null) {
//					novelDAO.updateImageNovelById(fixedNovel.getId(), fileImage.getInputStream(), imgDAO);
//					fixedNovel.setCoverImg(novelDAO.getEncodeImageById(fixedNovel.getId(), imgDAO));
//				}
				// old logic
				account.updateNovelInMyNovel(oldNovel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			form.applyErrorsToUI(request);
		}
		response.sendRedirect("manage/account/dashboard-novels");
	}

}
