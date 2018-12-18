package t4novel.azurewebsites.net.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Comment {
	private int id, accountOwnerId;
	private Account owner;
	private String content;
	private Timestamp time;
	private List<Comment> replyComments;
	
	public Comment() {
		
	}
	
	public Comment(int id, Account owner, String content, Timestamp time, List<Comment> replyComments) {
		super();
		this.id = id;
		this.owner = owner;
		this.content = content;
		this.time = time;
		this.replyComments = replyComments;
	}

	public int getAccountOwnerId() {
		return accountOwnerId;
	}

	public void setAccountOwnerId(int accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public List<Comment> getReplyComments() {
		return replyComments;
	}

	public void setReplyComments(List<Comment> replyComments) {
		this.replyComments = replyComments;
	}

}
