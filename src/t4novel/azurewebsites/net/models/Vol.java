package t4novel.azurewebsites.net.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Vol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1862228958870004742L;
	private int id, accountOwnerId, novelOwnerId;
	private List<Chap> chaps;
	private Timestamp dateUp;
	private String description, title;
	private Novel ownerNovel;

	public Vol(int id, List<Chap> chaps, Timestamp dateUp, String description, Novel owner) {
		super();
		this.id = id;
		this.chaps = chaps;
		this.dateUp = dateUp;
		this.description = description;
		this.ownerNovel = owner;
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
		for (Chap chap : chaps) {
			chap.setVolOwner(this);
			chap.setNovelOwner(this.getOwnerNovel());
		}
	}

	public Date getDateUp() {
		return dateUp;
	}

	public void setDateUp(Timestamp dateUp) {
		this.dateUp = dateUp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Novel getOwnerNovel() {
		return ownerNovel;
	}

	public void setOwnerNovel(Novel owner) {
		this.ownerNovel = owner;
		setNovelOwnerId(owner.getId());
	}

	public void addNewChappter(Chap chap) {
		if (!this.chaps.contains(chap))
			this.chaps.add(chap);
	}

	public void deleteChapter(int chapID) {
		for (int i = 0; i < chaps.size(); i++) {
			if (chaps.get(i).getId() == chapID) {
				chaps.remove(i);
				break;
			}
		}
	}

	public Chap getPreviousChap(int chapId) {
		Chap rs = null;
		int indexOfChap = -1;
		for (int i = 0; i < chaps.size(); i++) {
			if (chapId == chaps.get(i).getId()) {
				indexOfChap = i;
				break;
			}
		}
		if (indexOfChap > 0)
			rs = chaps.get(indexOfChap - 1);
		return rs;
	}

	public Chap getNextChap(int chapId) {
		Chap rs = null;
		int indexOfChap = -1;
		for (int i = 0; i < chaps.size(); i++) {
			if (chapId == chaps.get(i).getId()) {
				indexOfChap = i;
				break;
			}
		}
		if (indexOfChap < chaps.size() - 1)
			rs = chaps.get(indexOfChap + 1);
		return rs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Vol))
			return false;
		Vol other = (Vol) obj;
		return this.id == other.id;
	}
}
