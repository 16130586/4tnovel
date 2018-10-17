package t4novel.azurewebsites.net.models;

import java.util.List;

public class Thread {
	private int id;
	private String title;
	private String content;
	private List<Comment> comments;
	private List<Account> blockAccounts;
	private List<Account> follows;
	private Account owner;

	public Thread(int id, String title, String content, List<Comment> comments, List<Account> blockAccounts,
			List<Account> follows, Account owner) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.comments = comments;
		this.blockAccounts = blockAccounts;
		this.follows = follows;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Account> getBlockAccounts() {
		return blockAccounts;
	}

	public void setBlockAccounts(List<Account> blockAccounts) {
		this.blockAccounts = blockAccounts;
	}

	public List<Account> getFollows() {
		return follows;
	}

	public void setFollows(List<Account> follows) {
		this.follows = follows;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

}
