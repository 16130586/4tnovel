package t4novel.azurewebsites.net.models;

import java.util.Date;
import java.util.List;

public class Novel {
	private int id;
	private String name;
	private String description;
	private List<Vol> vols;
	private Date dateUp;
	private int view;
	private Account owner;
	private int like;
	private List<Comment> comments;
	private List<Account> follows;
	private List<NovelGenre> genres;
	private NovelStatus status;
	private NovelKind kind;
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Novel() {
	}

	public NovelKind getKind() {
		return kind;
	}

	public void setKind(NovelKind kind) {
		this.kind = kind;
	}

	public List<NovelGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<NovelGenre> genres) {
		this.genres = genres;
	}

	public NovelStatus getStatus() {
		return status;
	}

	public void setStatus(NovelStatus status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Vol> getVols() {
		return vols;
	}

	public void setVols(List<Vol> vols) {
		this.vols = vols;
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

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
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

	public List<Account> getFollows() {
		return follows;
	}

	public void setFollows(List<Account> follows) {
		this.follows = follows;
	}

	public Novel(int id, String name, String description, List<Vol> vols, Date dateUp, int view, Account owner,
			int like, List<Comment> comments, List<Account> follows) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.vols = vols;
		this.dateUp = dateUp;
		this.view = view;
		this.owner = owner;
		this.like = like;
		this.comments = comments;
		this.follows = follows;
	}
}
