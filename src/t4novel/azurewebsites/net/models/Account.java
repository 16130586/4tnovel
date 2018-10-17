package t4novel.azurewebsites.net.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
	private int id;
	private Date dateCreate;
	private String name;
	private List<Message> messages;
	private List<BookMarkFolder> bookMarkFolders;
	private int level;
	private boolean isAutoPassPushlishment;
	private List<Thread> threads;
	private List<Novel> novels;
	private List<Comment> comments;
	private List<Novel> follows;
	private boolean isBanned;
	public Account(int id, Date dateCreate, String name,
			int level, boolean isAutoPassPushlishment, boolean isBanned) {
		super();
		this.id = id;
		this.dateCreate = dateCreate;
		this.name = name;
		this.messages = new ArrayList<>();
		this.bookMarkFolders = new ArrayList<>();
		this.level = level;
		this.isAutoPassPushlishment = isAutoPassPushlishment;
		this.threads = new ArrayList<>();
		this.novels = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.follows = new ArrayList<>();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public List<Novel> getNovels() {
		return novels;
	}
	public void setNovels(List<Novel> novels) {
		this.novels = novels;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Novel> getFollows() {
		return follows;
	}
	public void setFollows(List<Novel> follows) {
		this.follows = follows;
	}
	public boolean isBanned() {
		return isBanned;
	}
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	
}
