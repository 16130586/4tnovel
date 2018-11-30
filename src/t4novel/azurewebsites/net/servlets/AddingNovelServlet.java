package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.ImageDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingNovelForm;
import t4novel.azurewebsites.net.models.Novel;

/**
 * Servlet implementation class AddingNovelServlet
 */
@WebServlet("/add-novel")

public class AddingNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingNovelServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("forward here");
		getServletContext().getRequestDispatcher("/jsps/pages/add-novel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		AbstractMappingForm form = new AddingNovelForm(request);
		
		if(!form.isOnError()) {
			//TODO write to dtb , apply to account 
			Connection cnn = (Connection) request.getAttribute("connection");

			NovelDAO novelDAO = new NovelDAO(cnn);
			ImageDAO imgDAO = new ImageDAO(cnn);
			GenreDAO genreDAO = new GenreDAO(cnn);
			
			Novel novel = (Novel) form.getMappingData();
			try {
				novelDAO.insertNovel(novel);
				novelDAO.insertGenres(novelDAO.getMaxID(), novel.getGenres(), genreDAO);
				FileItem fileImage = (FileItem) request.getAttribute("fileImage");
				if (fileImage != null)
					novelDAO.insertImageNovel(novelDAO.getMaxID(), fileImage.getInputStream(), imgDAO);
				else {
					novelDAO.insertImageNovel(novelDAO.getMaxID(), null, imgDAO);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("adding novel sucessed!	");
			System.out.println("sucessed");
			// TODO if success then set sucess for user
			request.setAttribute("sucessed", "Adding new novel done!");
		}
		else {
			form.applyErrorsToUI(request);
			System.out.println("error!");
			System.out.println(form.getErrors().entrySet().iterator().next());
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-novel.jsp").forward(request, response);
	}

}
