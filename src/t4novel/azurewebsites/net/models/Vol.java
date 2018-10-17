package t4novel.azurewebsites.net.models;

import java.util.Date;
import java.util.List;

public class Vol {
	private int id;
	private List<Chap> chaps;
	private Date dateUp;
	private String description;
	private Novel owner;
	public Vol(int id, List<Chap> chaps, Date dateUp, String description, Novel owner) {
		super();
		this.id = id;
		this.chaps = chaps;
		this.dateUp = dateUp;
		this.description = description;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Chap> getChaps() {
		return chaps;
	}
	public void setChaps(List<Chap> chaps) {
		this.chaps = chaps;
	}
	public Date getDateUp() {
		return dateUp;
	}
	public void setDateUp(Date dateUp) {
		this.dateUp = dateUp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Novel getOwner() {
		return owner;
	}
	public void setOwner(Novel owner) {
		this.owner = owner;
	}
	
}
