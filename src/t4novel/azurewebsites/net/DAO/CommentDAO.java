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
	
	public void insertComment(Comment comment) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO COMMENT (ID_OWNER, CONTENT, CREATETIME) VALUES (?, ?, ?)";
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, comment.getAccountOwnerId());
			stmt.setString(2, comment.getContent());
			stmt.setDate(3, (Date) comment.getTime());
			stmt.executeUpdate();
			
			System.out.println("Insert comment completed!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Comment getCommentByID(int commentID) {
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
				comment.setTime(rs.getDate(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return comment;
	}

	public List<Comment> getCommentByUser(int userID) {
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
				comment.setTime(rs.getDate(4));
				listComment.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listComment;
	}
	
	public void updateComment(Comment comment) {
		PreparedStatement stmt = null;
		String query = "UPDATE COMMENT SET CONTENT= ? WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setString(1, comment.getContent());
			stmt.setInt(2, comment.getId());
			stmt.executeUpdate();
			
			System.out.println("Update comment completed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteCommentByID(int commentID) {
		PreparedStatement stmt = null;
		String query = "DELETE * FROM COMMENT WHERE ID = ?";
		
		try {
			stmt = cnn.prepareStatement(query);
			stmt.setInt(1, commentID);
			stmt.executeUpdate();
			
			System.out.println("Delete comment completed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
