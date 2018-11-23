package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.BookMarkFolder;

public class BookmarkFolderDAO {
	private Connection cnn;
	private BookMarkDAO bmDAO;
	
	public BookmarkFolderDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public List<BookMarkFolder> getBookmarkFolders(int userID){
		LinkedList<BookMarkFolder> listBookmarkFolder = new LinkedList<>();
		PreparedStatement stmt;
		bmDAO = new BookMarkDAO(cnn);
		String query = "SELECT * FROM BMFOLDER WHERE ID_ACC = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookMarkFolder bmFolder = new BookMarkFolder();
				bmFolder.setId(rs.getInt(1));
				System.out.println("folder id :" + bmFolder.getId());
				bmFolder.setBookMarks(bmDAO.getBookmarks(rs.getInt(1)));
				bmFolder.setTitle(rs.getString(3));
				listBookmarkFolder.add(bmFolder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listBookmarkFolder;
	}
}
