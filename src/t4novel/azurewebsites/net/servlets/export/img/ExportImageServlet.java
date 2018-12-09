package t4novel.azurewebsites.net.servlets.export.img;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.ImageDAO;

@WebServlet("/resources/imgs")
public class ExportImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExportImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int imgId = Integer.parseInt(request.getParameter("id"));
			Connection cnn = (Connection) request.getAttribute("connection");
			ImageDAO imgDao = new ImageDAO(cnn);
			String cacheStatus = request.getHeader("Cache-Control");
			if ("no-cache".equalsIgnoreCase(cacheStatus) || cacheStatus == null) {
				byte[] bytesImage = imgDao.getBytesOfImage(imgId);
				
				if(bytesImage == null || bytesImage.length == 0) {
					response.setStatus(404);
					response.sendError(404);
					return;
				}
				long cacheAge = 1 * 24 * 3600;
				long expiry = new Date().getTime() + cacheAge * 1000;
				
				response.setStatus(200);
				response.setDateHeader("Expires", expiry);
				response.setHeader("Cache-Control", "max-age=" + cacheAge + ", public, must-revalidate");
			
				OutputStream netOut = new BufferedOutputStream(response.getOutputStream());
				netOut.write(bytesImage, 0, bytesImage.length);
				netOut.flush();
			} else {
				response.setStatus(304);
				return;
			}
		} catch (Exception e) {
			System.out.println("bad requesting img");
			response.sendError(400);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
