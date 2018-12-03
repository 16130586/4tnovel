package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
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
import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingNovelForm;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;

/**
 * Servlet implementation class AddingNovelServlet
 */
@WebServlet("/add-novel")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 4, // 4MB
		maxFileSize = 1024 * 1024 * 100, // 100MB
		maxRequestSize = 1024 * 1024 * 100) // 100MB
public class AddingNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingNovelServlet() {
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
		System.out.println("forward here");
		getServletContext().getRequestDispatcher("/jsps/pages/add-novel.jsp").forward(request, response);
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
		Account hostAccount = (Account) request.getSession().getAttribute("account");
		if (!form.isOnError()) {
			// TODO write to dtb , apply to account
			Connection cnn = (Connection) request.getAttribute("connection");

			NovelDAO novelDAO = new NovelDAO(cnn);
			ImageDAO imgDAO = new ImageDAO(cnn);
			GenreDAO genreDAO = new GenreDAO(cnn);

			Novel novel = (Novel) form.getMappingData();
			try {
				novel.setId(novelDAO.getNextID());
				novelDAO.insertNovel(novel);
				novelDAO.insertGenres(novel.getId(), novel.getGenres(), genreDAO);
				novel.setVols(new LinkedList<>());
				FileItem fileImage = (FileItem) request.getAttribute("fileImage");
				if (fileImage != null)
					novelDAO.insertImageNovel(novel.getId(), fileImage.getInputStream(), imgDAO);
				else {
					novelDAO.insertImageNovel(novel.getId(), null, imgDAO);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// ram
			hostAccount.addNewOwnerNovel(novel);
			
			System.out.println("adding novel sucessed!	");
			System.out.println("sucessed");
			// TODO if success then set sucess for user
			request.setAttribute("sucessed", "Thêm truyện thành công!");
			
			//logic for pagination 
			int lastestTotalNovels =  (int) getServletContext().getAttribute("totalNovels");
			getServletContext().setAttribute("totalNovels",lastestTotalNovels + 1);
			
			// ending logic for pagintion
		} else {
			form.applyErrorsToUI(request);
			System.out.println("error!");
			System.out.println(form.getErrors().entrySet().iterator().next());
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-novel.jsp").forward(request, response);
	}

}
