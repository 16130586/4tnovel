package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Novel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4535349598603561651L;
	private int id, accountOwnerId;
	private String name;
	private String description, coverImg;
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
	private Group group;
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
		this.groupId = group.getId();
	}
	private String encodeImg;
	
	public Novel() {
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

	@Override
	public String toString() {
		return name + ", " + description + ", " + status.toString() + ", " + kind.toString() + ", " + groupId + ", "
				+ genres.toString();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getAccountOwnerId() {
		return accountOwnerId;
	}

	public void setAccountOwnerId(int accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
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

	// parse from string values to list genre > set
	public static List<NovelGenre> parseStringToGenres(String inp) {
		StringTokenizer st = new StringTokenizer(inp, ",");
		List<NovelGenre> genres = new LinkedList<>();
		while (st.hasMoreElements()) {
			genres.add(NovelGenre.getGenre(Integer.parseInt(st.nextToken())));
		}
		return genres;
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
		for(Vol v : vols ) {
			v.setOwnerNovel(this);
		}
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
		this.accountOwnerId = owner.getId();
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

	public String genreToString() {
		StringBuffer sb = new StringBuffer();
		for (NovelGenre genre : genres) {
			sb.append(genre.getValue() + ",");
		}
		return sb.toString();
	}

	public String getEncodeImg() {
		return encodeImg;
	}

	public void setEncodeImg(String encodeImg) {
		this.encodeImg = encodeImg;
	}
	
	public void addNewVol(Vol vol) {
		if(!this.vols.contains(vol)) this.vols.add(vol);
	}
	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || ! (obj instanceof Novel)) return false;
		Novel other = (Novel) obj;
		return this.id == other.id;
	}

}
