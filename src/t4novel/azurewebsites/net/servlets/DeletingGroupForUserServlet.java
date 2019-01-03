package t4novel.azurewebsites.net.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.ResponseDate;

import t4novel.azurewebsites.net.DAO.GroupDAO;

/**
 * Servlet implementation class DeletingGroupForUserServlet
 */
@WebServlet("/deletingGroupForUserServlet")
public class DeletingGroupForUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletingGroupForUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("manage/account/dashboard-groups");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey() + "  " + Arrays.toString(entry.getValue()));
		}
		Connection cnn = (Connection) request.getAttribute("connection");
		GroupDAO groupDAO = new GroupDAO(cnn);
		int groupID = Integer.parseInt(request.getParameter("id"));
		try {
			groupDAO.removeAllMemberFromGroup(groupID);
			groupDAO.deleteGroupByID(groupID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
