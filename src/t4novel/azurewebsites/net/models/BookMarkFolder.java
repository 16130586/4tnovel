package t4novel.azurewebsites.net.models;

import java.util.List;

public class BookMarkFolder {
	private int id;
	private List<BookMark> bookMarks;
	private String title;

	public BookMarkFolder(int id, List<BookMark> bookMarks, String title) {
		super();
		this.id = id;
		this.bookMarks = bookMarks;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
