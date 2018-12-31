package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.annotations.Expose;

public class Novel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4535349598603561651L;
	private int id;
	@Expose
	private int accountOwnerId;
	private String name;
	private String description;
	@Expose
	private String coverImg;
	@Expose
	private List<Vol> vols;
	@Expose
	private Timestamp dateUp;
	private int view, coverId;
	@Expose
	private Account owner;
	private int like;
	@Expose
	private List<Comment> comments;
	@Expose
	private List<Account> follows;
	private List<NovelGenre> genres;
	@Expose
	private NovelStatus status;
	@Expose
	private NovelKind kind;
	@Expose
	private int groupId;
	@Expose
	private Group group;
	@Expose
	private String encodeImg;

	public int getCoverId() {
		return coverId;
	}

	public void setCoverId(int coverId) {
		this.coverId = coverId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
		this.groupId = group.getId();
	}

	public Novel() {
	}

	public Novel(int id, String name, String description, List<Vol> vols, Timestamp dateUp, int view, Account owner,
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
		for (Vol v : vols) {
			v.setOwnerNovel(this);
		}
	}

	public Timestamp getDateUp() {
		return dateUp;
	}

	public void setDateUp(Timestamp dateUp) {
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
		if (!this.vols.contains(vol))
			this.vols.add(vol);
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Novel))
			return false;
		Novel other = (Novel) obj;
		return this.id == other.id;
	}

	public void update(Novel fixedNovel) {
		this.accountOwnerId = fixedNovel.getAccountOwnerId();
		this.comments = fixedNovel.getComments();
		this.coverImg = fixedNovel.getCoverImg();
		this.dateUp = fixedNovel.getDateUp();
		this.description = fixedNovel.getDescription();
		this.encodeImg = fixedNovel.getEncodeImg();
		this.follows = fixedNovel.getFollows();
		this.genres = fixedNovel.getGenres();
		this.group = fixedNovel.getGroup();
		this.groupId = fixedNovel.getGroupId();
		this.id = fixedNovel.getId();
		this.kind = fixedNovel.getKind();
		this.like = fixedNovel.getLike();
		this.name = fixedNovel.getName();
		this.owner = fixedNovel.getOwner();
		this.status = fixedNovel.getStatus();
		this.view = fixedNovel.getView();
		this.vols = fixedNovel.getVols();
		this.coverId = fixedNovel.getCoverId();

	}

	public int getTotalVols() {
		return this.vols.size();
	}

	public int getTotalChaps() {
		int total = 0;
		for (Vol v : vols) {
			total += v.getChaps() == null ? 0 : v.getChaps().size();
		}
		return total;
	}
}
