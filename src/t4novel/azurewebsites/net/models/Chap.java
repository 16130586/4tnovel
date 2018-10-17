package t4novel.azurewebsites.net.models;

import java.util.Date;
import java.util.List;

public class Chap {
	private int id;
	private String content;
	private Date dateUp;
	private int view;
	private int like;
	private List<Comment> comments;
	private Vol owner;

	public Chap(int id, String content, Date dateUp, int view, int like, List<Comment> comments, Vol owner) {
		super();
		this.id = id;
		this.content = content;
		this.dateUp = dateUp;
		this.view = view;
		this.like = like;
		this.comments = comments;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateUp() {
		return dateUp;
	}

	public void setDateUp(Date dateUp) {
		this.dateUp = dateUp;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Vol getOwner() {
		return owner;
	}

	public void setOwner(Vol owner) {
		this.owner = owner;
	}

}
