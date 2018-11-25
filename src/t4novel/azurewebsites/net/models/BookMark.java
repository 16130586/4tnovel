package t4novel.azurewebsites.net.models;

import java.util.Date;

public class BookMark {
	private int id, bookmarkFolderId;
	private String description;
	private String url;
	private Date time;
	private String title;

	public BookMark() {
		
	}
	
	public BookMark(String title) {
		super();
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookmarkFolderId() {
		return bookmarkFolderId;
	}

	public void setBookmarkFolderId(int bookmarkFolderId) {
		this.bookmarkFolderId = bookmarkFolderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
