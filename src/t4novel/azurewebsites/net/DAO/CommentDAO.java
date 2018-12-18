package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import t4novel.azurewebsites.net.models.Comment;

public class CommentDAO {

	private Connection cnn;

	public CommentDAO(Connection databaseConnection) {
		this.cnn = databaseConnection;
	}

	public void insertComment(Comment comment) throws Exception {
		PreparedStatement stmt = null;
		String query = "INSERT INTO COMMENT (ID_OWNER, CONTENT, CREATETIME) VALUES (?, ?, ?)";
		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, comment.getAccountOwnerId());
			stmt.setString(2, comment.getContent());
			stmt.setDate(3, new Date(comment.getTime().getTime()));
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Insert comment completed!");
		} catch (SQLException e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public Comment getCommentByID(int commentID) throws Exception {
		Comment comment = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM COMMENT WHERE ID = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, commentID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getInt(1));
				comment.setAccountOwnerId(rs.getInt(2));
				comment.setContent(rs.getString(3));
				comment.setTime(rs.getTimestamp(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return comment;
	}

	public List<Comment> getCommentByUser(int userID) throws Exception {
		LinkedList<Comment> listComment = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM COMMENT WHERE ID_OWNER = ?";

		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, userID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt(1));
				comment.setAccountOwnerId(rs.getInt(2));
				comment.setContent(rs.getString(3));
				comment.setTime(rs.getTimestamp(4));
				listComment.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return listComment;
	}

	public void updateComment(Comment comment) throws Exception {
		PreparedStatement stmt = null;
		String query = "UPDATE COMMENT SET CONTENT= ? WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, comment.getContent());
			stmt.setInt(2, comment.getId());
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Update comment completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteCommentByID(int commentID) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE * FROM COMMENT WHERE ID = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, commentID);
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Delete comment completed!");
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		} finally {
			cnn.setAutoCommit(true);
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteVolByUser(int userID) throws Exception {
		PreparedStatement stmt = null;
		String query = "DELETE FROM VOL WHERE ID_OWNER = ?";

		try {
			cnn.setAutoCommit(false);
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, userID);
			stmt.executeUpdate();
			cnn.commit();
			System.out.println("Delete comment completed!");
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
