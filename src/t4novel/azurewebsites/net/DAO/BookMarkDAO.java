package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.BookMark;

public class BookMarkDAO {
private Connection cnn;
	
	public BookMarkDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public List<BookMark> getBookmarks(int folderID){
		LinkedList<BookMark> listBookmark = new LinkedList<>();
		PreparedStatement stmt;
		String query = "SELECT * FROM BM WHERE ID_BMFOLDER = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, folderID);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookMark bm = new BookMark();
				bm.setId(rs.getInt(1));
				System.out.println("bookmark id : " + bm.getId());
				bm.setUrl(rs.getString(3));
				bm.setTime(rs.getDate(4));
				bm.setTitle(rs.getString(5));
				listBookmark.add(bm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listBookmark;
	}
}
