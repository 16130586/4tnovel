package t4novel.azurewebsites.net.models;

import java.util.List;

public class BookMarkFolder {
	private int id, accountOnwerID;
	private List<BookMark> bookMarks;
	private String title;

	public BookMarkFolder() {
		
	}
	
	public BookMarkFolder(int id, int accountOnwerId, List<BookMark> bookMarks, String title) {
		super();
		this.id = id;
		this.bookMarks = bookMarks;
		this.title = title;
		this.accountOnwerID = accountOnwerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAccountOnwerID() {
		return accountOnwerID;
	}

	public void setAccountOnwerID(int accountOnwerID) {
		this.accountOnwerID = accountOnwerID;
	}

	public List<BookMark> getBookMarks() {
		return bookMarks;
	}

	public void setBookMarks(List<BookMark> bookMarks) {
		this.bookMarks = bookMarks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
