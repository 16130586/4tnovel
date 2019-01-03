package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookMark implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7768180560413700320L;
	private int id, bookmarkFolderId;
	private String detail;
	private String url;
	private Timestamp time;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
