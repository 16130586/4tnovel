package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

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

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 4, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String url = "";
		if ("add-novel".equals(type)) {
			url = "/add-novel";
		} else if ("add-vol".equals(type)) {
			url = "/add-vol";
		} else if ("add-chapter".equals(type)) {
			url = "/add-chapter";
		} else if ("add-group".equals(type)) {
			url = "/add-group";
		} else if ("add-member".equals(type)) {
			url = "/add-member";
		} else {
			response.sendError(404);
			return;
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<String> genres = new LinkedList<>();
		try {
			List<FileItem> listFiles = upload.parseRequest(request);
			for (FileItem fileItem : listFiles) {
				if (!fileItem.isFormField()) {
					request.setAttribute("fileImage", fileItem);
				} else {
					if ("genre".equals(fileItem.getFieldName()))
						genres.add(fileItem.getString());
					else
						request.setAttribute(fileItem.getFieldName(), fileItem.getString());
				}
			}
			request.setAttribute("genres", genres);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String type = (String) request.getAttribute("type");
		String url = "";
		if ("add-novel".equals(type)) {
			url = "/add-novel";
		}
		else if ("add-vol".equals(type)) {
			url = "/add-vol";
		}
		else	if ("add-chapter".equals(type)) {
			url = "/add-chapter";
		}
		else if ("add-group".equals(type)) {
			url = "/add-group";
		}
		else	if ("add-member".equals(type)) {
			url = "/add-member";
		}
		else {
			response.sendError(404);
			return;
		}
		System.out.println("forward!!! on add servlet");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
