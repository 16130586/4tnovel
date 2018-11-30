package t4novel.azurewebsites.net.models;

import java.util.List;
import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private List<Account> accounts;
	private String name;
	private String description;
	private Date dateCreate;
	private Account owner;

	public Group(int id, List<Account> accounts, String name, String description, Date dateCreate, Account owner) {
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

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}
	public void addMember(Account ac) {
		this.accounts.add(ac);
	}

}