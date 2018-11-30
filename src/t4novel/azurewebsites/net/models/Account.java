package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import t4novel.azurewebsites.net.sercurities.Role;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateCreate;
	private String displayedName, userName, gmail, password;
	private List<Message> messages;
	private List<BookMarkFolder> bookMarkFolders;
	private Role role;
	private boolean isAutoPassPushlishment;
	private List<Thread> threads;
	private List<Novel> ownNovels, follows;
	private List<Comment> comments;
	private List<Group> joinGroups;

	private boolean isBanned;

	public Account(int id) {
		this.id = id;
	};

	public Account() {
	};

	public Account(int id, Date dateCreate, String displayedName, String userName, String gmail, List<Message> messages,
			List<BookMarkFolder> bookMarkFolders, Role role, boolean isAutoPassPushlishment, List<Thread> threads,
			List<Novel> ownNovels, List<Novel> follows, List<Comment> comments, boolean isBanned) {
		super();
		this.id = id;
		this.dateCreate = dateCreate;
		this.displayedName = displayedName;
		this.userName = userName;
		this.gmail = gmail;
		this.messages = messages;
		this.bookMarkFolders = bookMarkFolders;
		this.role = role;
		this.isAutoPassPushlishment = isAutoPassPushlishment;
		this.threads = threads;
		this.ownNovels = ownNovels;
		this.follows = follows;
		this.comments = comments;
		this.isBanned = isBanned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getDisplayedName() {
		return displayedName;
	}

	public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<BookMarkFolder> getBookMarkFolders() {
		return bookMarkFolders;
	}

	public void setBookMarkFolders(List<BookMarkFolder> bookMarkFolders) {
		this.bookMarkFolders = bookMarkFolders;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isAutoPassPushlishment() {
		return isAutoPassPushlishment;
	}

	public void setAutoPassPushlishment(boolean isAutoPassPushlishment) {
		this.isAutoPassPushlishment = isAutoPassPushlishment;
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public List<Novel> getOwnNovels() {
		return ownNovels;
	}

	public void setOwnNovels(List<Novel> ownNovels) {
		this.ownNovels = ownNovels;
	}

	public List<Novel> getFollows() {
		return follows;
	}

	public void setFollows(List<Novel> follows) {
		this.follows = follows;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Group> getJoinGroup() {
		return joinGroups;
	}

	public void setJoinGroup(List<Group> jointGroup) {
		this.joinGroups = jointGroup;
		System.out.println("is joint group null ? " + (jointGroup == null ));
	}

	public Group getGroup(int rootGroupId) {
		Group rs = null;
		for (Group gr : joinGroups) {
			if (rootGroupId == gr.getId()) {
				rs = gr;
				break;
			}
		}
		return rs;
	}

	public List<Group> getOwnerGroups() {
		List<Group> rs = new ArrayList<>(joinGroups.size());
		for(Group gr : joinGroups) {
			if(gr.getOwner().getId() == this.id) {
				rs.add(gr);
			}
		}
		return rs;
	}
	public void addJoinGroup(Group g) {
		if(!this.joinGroups.contains(g))
			this.joinGroups.add(g);
	}
}
