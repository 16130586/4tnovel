package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Vol implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1862228958870004742L;
	private int id, accountOwnerId, novelOwnerId;
	private List<Chap> chaps;
	private Date dateUp;
	private String description, title;
	private Novel owner;

	public Vol(int id, List<Chap> chaps, Date dateUp, String description, Novel owner) {
		super();
		this.id = id;
		this.chaps = chaps;
		this.dateUp = dateUp;
		this.description = description;
		this.owner = owner;
	}

	public int getAccountOwnerId() {
		return accountOwnerId;
	}

	public void setAccountOwnerId(int accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNovelOwnerId() {
		return novelOwnerId;
	}

	public void setNovelOwnerId(int novelOwnerId) {
		this.novelOwnerId = novelOwnerId;
	}

	public Vol() {

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
		setNovelOwnerId(owner.getId());
	}
	public void addNewChappter(Chap chap) {
		if(!this.chaps.contains(chap)) this.chaps.add(chap);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || ! (obj instanceof Vol)) return false;
		Vol other = (Vol) obj;
		return this.id == other.id;
	}
}
