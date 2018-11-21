package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingChapterForm;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.utils.Genrator;

/**
 * Servlet implementation class AddingChapterServlet
 */
@WebServlet("/add-chapter")
public class AddingChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingChapterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Genrator genrator = Genrator.getInstance();
		AbstractMappingForm form = new AddingChapterForm(request, genrator);
		if(!form.isOnError()) {
			//TODO writing to db , and something related
			Chap chapter = (Chap) form.getMappingData();
			
			// set sucessed for user
			request.setAttribute("sucessed", "Adding new chapter done!");
		}
		else {
			form.applyErrorsToUI(request);
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-vol.jsp").forward(request, response);
	}

}
