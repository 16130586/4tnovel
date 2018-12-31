package t4novel.azurewebsites.net.models;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private List<Account> accounts;
	private String name;
	private String description;
	private Timestamp dateCreate;
	private Account owner;
	private int ownerId;

	public Group(int id, List<Account> accounts, String name, String description, Timestamp dateCreate, Account owner) {
		super();
		this.id = id;
		this.accounts = accounts;
		this.name = name;
		this.description = description;
		this.dateCreate = dateCreate;
		this.owner = owner;
	}

	public Group() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
		this.ownerId = owner.getId();
	}

	public void addMember(Account ac) {
		if(!this.accounts.contains(ac))
			this.accounts.add(ac);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Group))
			return false;
		Group otherGroup = (Group) obj;
		return this.id == otherGroup.id;
	}

	public void setOwnerId(int accID) {
		this.ownerId  = accID;
	}
	public int getOwnerId() {
		return this.ownerId;
	}
	public int getTotalMemembers() {
		return this.accounts == null ? 0 : this.accounts.size();
	}
}