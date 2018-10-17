package t4novel.azurewebsites.net.models;

import java.util.Date;

public class Message {
	private int id;
	private String content;
	private Date time;
	private String url;
	private Account from;
	private Account to;

	public Message(int id, String content, Date time, String url, Account from, Account to) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.url = url;
		this.from = from;
		this.to = to;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
		this.to = to;
	}

}
