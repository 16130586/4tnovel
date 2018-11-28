package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.BookMarkFolder;

public class BookmarkFolderDAO {
	private Connection cnn;
	
	public BookmarkFolderDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}
	
	public List<BookMarkFolder> getBookmarkFolderByUser(int userID){
		LinkedList<BookMarkFolder> listBookmarkFolder = new LinkedList<>();
		PreparedStatement stmt;
		String query = "SELECT * FROM BMFOLDER WHERE ID_ACC = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookMarkFolder bmFolder = new BookMarkFolder();
				bmFolder.setId(rs.getInt(1));
				bmFolder.setAccountOnwerID(rs.getInt(2));
				bmFolder.setTitle(rs.getString(3));
				listBookmarkFolder.add(bmFolder);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listBookmarkFolder;
	}
	
	public List<BookMarkFolder> getAllBookmarkFolder(){
		LinkedList<BookMarkFolder> listBookmarkFolder = new LinkedList<>();
		PreparedStatement stmt;
		String query = "SELECT * FROM BMFOLDER";
		
		try {
			stmt = cnn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookMarkFolder bmFolder = new BookMarkFolder();
				bmFolder.setId(rs.getInt(1));
				bmFolder.setAccountOnwerID(rs.getInt(2));
				bmFolder.setTitle(rs.getString(3));
				listBookmarkFolder.add(bmFolder);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listBookmarkFolder;
	}
	
	public void insertBookmarkFolder(BookMarkFolder bookmarkFolder) {
		PreparedStatement stmt;
		String query = "INSERT INTO BMFOLDER (ID_ACC, TITLE) VALUES (?, ?)";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, bookmarkFolder.getAccountOnwerID());
			stmt.setString(2, bookmarkFolder.getTitle());
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Insert bookmark folder completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBookmarkFolderByID(int folderID) {
		PreparedStatement stmt;
		String query = "DELETE FROM BMFOLDER WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, folderID);
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Delete bookmark folder completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBookmarkFolder(BookMarkFolder bookmakFolder) {
		PreparedStatement stmt;
		String query = "UPDATE BMFOLDER SET TITLE = ? WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, bookmakFolder.getTitle());
			stmt.setInt(2, bookmakFolder.getId());
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Update bookmark folder completed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
