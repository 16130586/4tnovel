package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Chap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6108699031868555694L;
	private int id;
	@Expose
	private String content;
	private String title;
	private Date dateUp;
	@Expose
	private int view;
	@Expose
	private int like;
	@Expose
	private List<Comment> comments;
	@Expose
	private Vol volOwner;
	@Expose
	private int volOwnerId;
	@Expose
	private int novelOwnerId;
	private Novel novelOwner;

	public Chap(int id, String content, Date dateUp, int view, int like, List<Comment> comments, Vol owner) {
		super();
		this.id = id;
		this.content = content;
		this.dateUp = dateUp;
		this.view = view;
		this.like = like;
		this.comments = comments;
		this.volOwner = owner;
	}

	public Chap() {

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Vol getVolOwner() {
		return volOwner;
	}

	public void setVolOwner(Vol volOwner) {
		this.volOwner = volOwner;
	}

	public int getVolOwnerId() {
		return volOwnerId;
	}

	public void setVolOwnerId(int volOwnerId) {
		this.volOwnerId = volOwnerId;
	}

	public int getNovelOwnerId() {
		return novelOwnerId;
	}

	public void setNovelOwnerId(int novelOwnerId) {
		this.novelOwnerId = novelOwnerId;
	}

	public Novel getNovelOwner() {
		return novelOwner;
	}

	public void setNovelOwner(Novel novelOwner) {
		this.novelOwner = novelOwner;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Chap))
			return false;
		Chap other = (Chap) obj;
		return this.id == other.id;
	}
}
