package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.BookMark;

public class BookMarkDAO {
	private Connection cnn;

	public BookMarkDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public List<BookMark> getBookmarkByFolder(int folderID) throws Exception {
		LinkedList<BookMark> listBookmark = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM BM WHERE ID_BMFOLDER = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, folderID);
			rs = stmt.executeQuery();

			while (rs.next()) {
				BookMark bm = new BookMark();
				bm.setId(rs.getInt(1));
				bm.setUrl(rs.getString(3));
				bm.setTime(rs.getTimestamp(4));
				bm.setTitle(rs.getString(5));
				listBookmark.add(bm);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return listBookmark;
	}

	public void insertBookmark(BookMark bookmark) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO BOOKMARK (ID_BMFOLDER, URL, TIME_BM, TITLE) VALUES (?, ?, ?, ?)";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, bookmark.getBookmarkFolderId());
			stmt.setString(2, bookmark.getUrl());
			stmt.setDate(3, new Date(bookmark.getTime().getTime()));
			stmt.setString(4, bookmark.getTitle());
			stmt.executeUpdate();
			stmt.close();
			System.out.println("Insert bookmark completed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateBookmark(BookMark bookmark) throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE BOOKMARK SET URL = ?, TITLE = ? WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, bookmark.getUrl());
			stmt.setString(2, bookmark.getTitle());
			stmt.setInt(3, bookmark.getId());
			stmt.executeUpdate();
			stmt.close();
			cnn.commit();
			System.out.println("Update bookmark completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteBookmarkByID(int bookmarkID) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE FROM BOOKMARK WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, bookmarkID);
			stmt.executeUpdate();
			stmt.close();
			cnn.commit();
			System.out.println("Delete bookmark completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}
}
