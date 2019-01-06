package t4novel.azurewebsites.net.servlets.export.img;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

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
		response.setHeader("Content-Type", "image/jpeg");
		try {
			int imgId = Integer.parseInt(request.getParameter("id"));
			Connection cnn = (Connection) request.getAttribute("connection");
			ImageDAO imgDao = new ImageDAO(cnn);
			String cacheStatus = request.getHeader("Cache-Control");
			if (cacheStatus == null)
				cacheStatus = "no-cache";
			cacheStatus.trim();
			if ("no-cache".equalsIgnoreCase(cacheStatus)) {
				byte[] bytesImage = imgDao.getBytesOfImage(imgId);

				if (bytesImage == null || bytesImage.length == 0) {
					response.setStatus(404);
					response.sendError(404);
					return;
				}
				response.setStatus(200);
				response.setHeader("Cache-Control", "private, must-revalidate");
				response.setDateHeader("Last-Modified", imgDao.getDateUp(imgId).getTime());
				response.setHeader("etag", "\"" + imgDao.getEtag(imgId) + "\"");
				response.setHeader("content-length", bytesImage.length + "");
				OutputStream netOut = new BufferedOutputStream(response.getOutputStream());
				netOut.write(bytesImage);
				netOut.flush();
				netOut.close();
			} else if ("max-age=0".equalsIgnoreCase(cacheStatus)) {
				String dbEtag = "\"" + imgDao.getEtag(imgId) + "\"";

				long dbDateUp = imgDao.getDateUp(imgId).getTime();

				// case 1 , still good then set 304
				if (validateOldDataInClientCache(request, dbEtag, dbDateUp)) {
					response.setHeader("etag", dbEtag);
					response.setStatus(304);
					return;
				}
				// case 2 , not fresh then return a new copy from server to client
				else {
					byte[] bytesImage = imgDao.getBytesOfImage(imgId);
					if (bytesImage == null || bytesImage.length == 0) {
						response.setStatus(404);
						response.sendError(404);
						return;
					}

					response.setStatus(200);
					response.setHeader("Cache-Control", " public, must-revalidate");
					response.setDateHeader("Last-Modified", imgDao.getDateUp(imgId).getTime());
					response.setHeader("etag", dbEtag);
					response.setHeader("content-length", bytesImage.length + "");
					OutputStream netOut = new BufferedOutputStream(response.getOutputStream());
					netOut.write(bytesImage);
					netOut.flush();
					netOut.close();
					return;
				}
			} else {
				System.out.println(cacheStatus);
			}
		} catch (NumberFormatException e) {
//			System.out.println("bad requesting img");
			response.sendError(400);
		} catch (Exception e) {
//			System.out.println("client abort " + e.getMessage());
		}
	}

	private synchronized boolean validateOldDataInClientCache(HttpServletRequest request, String dbEtag,
			long dbLastModified) {
//		String imgId = request.getParameter("id");
		String clientModifiedSince = request.getHeader("if-modified-since");
		String clientNoneMatch = request.getHeader("if-none-match");
		long clientModified = request.getDateHeader("if-modified-since");
		if (clientModifiedSince == null || clientNoneMatch == null)
			return false;
		// checking di chu
		return clientNoneMatch.equals(dbEtag) && Long.compare(clientModified, dbLastModified) <= 0;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
