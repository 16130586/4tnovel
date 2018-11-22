package t4novel.azurewebsites.net.sercurities;

import java.util.LinkedList;
import java.util.List;

public enum Role {

	GUESS(), USER(), ADMINISTRATOR();

	private List<String> allowedUris;

	public static Role getRole(int role) {
		return Role.values()[role];
	}

	public static Role getRole(String role) {
		Role rs = null;
		for (Role r : Role.values()) {
			if (r.name().equalsIgnoreCase(role)) {
				rs = r;
				break;
			}
		}
		return rs;
	}

	public List<String> getAllowedUris() {
		return allowedUris;
	}
	public void addAllowedUri(String uri){
		if(this.allowedUris == null)
			this.allowedUris = new LinkedList<>();
		if(!this.allowedUris.contains(uri))
			this.allowedUris.add(uri);
	}

	public void setAllowedUris(List<String> allowedUris) {
		this.allowedUris = allowedUris;
	}
	@Override
	public String toString() {
		return this.name();
	}
}
