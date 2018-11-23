package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.forms.AbstractMappingForm;
import t4novel.azurewebsites.net.forms.AddingVolForm;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/jsps/pages/add-vol.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractMappingForm form = new AddingVolForm(request);
		if(!form.isOnError()) {
			//TODO writing to db , and something related
			Connection cnn = (Connection) request.getAttribute("connection");
			VolDAO VolDAO = new VolDAO(cnn);
			Vol vol = (Vol) form.getMappingData();
			
			VolDAO.insertVol(vol);
			// set sucessed for user
			request.setAttribute("sucessed", "Adding new chapter done!");
			System.out.println("suceess!");
		}
		else {
			form.applyErrorsToUI(request);
			System.out.println("eror!");
		}
		getServletContext().getRequestDispatcher("/jsps/pages/add-vol.jsp").forward(request, response);
	}

}
