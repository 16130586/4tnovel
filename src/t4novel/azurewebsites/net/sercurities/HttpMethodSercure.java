package t4novel.azurewebsites.net.sercurities;

import java.util.List;

public class HttpMethodSercure {
	private String name;
	private boolean loginNeeded, dbConnectionNeeded;
	private List<Role> roles;

	public HttpMethodSercure(String name, boolean loginNeeded, boolean dbConnectionNeeded, List<Role> roles) {
		super();
		this.name = name;
		this.loginNeeded = loginNeeded;
		this.dbConnectionNeeded = dbConnectionNeeded;
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLoginNeeded() {
		return loginNeeded;
	}

	public void setLoginNeeded(boolean loginNeeded) {
		this.loginNeeded = loginNeeded;
	}

	public boolean isDbConnectionNeeded() {
		return dbConnectionNeeded;
	}

	public void setDbConnectionNeeded(boolean dbConnectionNeeded) {
		this.dbConnectionNeeded = dbConnectionNeeded;
	}

	@Override
	public String toString() {
		return name + ", " + " login? " + loginNeeded + " ,connect? " + dbConnectionNeeded + roles;
	}
}
