package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7773057377763278100L;
	private int id;
	private String content;
	private Timestamp time;
	@Expose
	private Account owner;
	public Message() {
		
	}
	public Message(int id, String content, Timestamp time, Account owner) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.owner = owner;
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Account getOwner() {
		return owner;
	}
	public void setOwner(Account from) {
		this.owner = from;
	}
	

}
