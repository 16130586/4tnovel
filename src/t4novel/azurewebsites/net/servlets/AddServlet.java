package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingChapterForm;
import t4novel.azurewebsites.net.forms.AddingVolForm;
import t4novel.azurewebsites.net.models.Vol;
import t4novel.azurewebsites.net.utils.Genrator;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String url = "";
		if (type.equals("add-novel")) {
			url = "/jsps/pages/add-novel.jsp";
		}
		if (type.equals("add-vol")) {
			url = "/jsps/pages/add-vol.jsp";
		}
		if (type.equals("add-chapter")) {
			url = "/jsps/pages/add-chapter.jsp";
		}
		if (type.equals("add-group")) {
			url = "/jsps/pages/add-group.jsp";
		}
		if (type.equals("add-member")) {
			url = "/jsps/pages/add-member.jsp";
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
		}
		// for testing purpose
//		Genrator idGenrator = Genrator.getInstance();
//		AddingNovelForm addingNovelForm = new AddingNovelForm(request, idGenrator);
//		System.out.println(addingNovelForm.getMappingData());\

//		Genrator idGenrator = Genrator.getInstance();
//		AddingChapterForm addingChapForm = new AddingChapterForm(request, idGenrator);
//		System.out.println(addingChapForm.getMappingData());
		// for testing purpose
//		Genrator idGenrator = Genrator.getInstance();
//		AbstractMappingForm addingVolForm = new AddingVolForm(request, idGenrator);
//		System.out.println(addingVolForm.isOnError());
//		for (Entry<String, String> entry : addingVolForm.getErrors().entrySet()) {
//			System.out.println(entry.getKey() + " " + entry.getValue());
//		}
//		if(addingVolForm.isOnError()) {
//			// mapping loi qua jsp thong qua request
//			for (Entry<String, String> error : userSubmittedForm.getErrors().entrySet()) {
//				String errorName = error.getKey() + "Error";
//				request.setAttribute(errorName, error.getValue());
//			}
//		}
//		else {
//			// ghi xuong database , mapping success message cho ng dung ! , chuyen huong ve trang add//
//		}
//		// ghi xuong dtb , chuyen huong && thong bao cho ng dung!
		
	}

}
