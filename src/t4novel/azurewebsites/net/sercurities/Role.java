package t4novel.azurewebsites.net.sercurities;

import java.util.List;

public enum Role {

	GUESS(false), USER(true), ADMINISTRATOR(true);

	private List<String> allowedUris;
	private boolean isNeedLoginFeature;

	Role(boolean isNeedLogin) {
		this.isNeedLoginFeature = isNeedLogin;
	}

	public boolean isNeedLoginFeature() {
		return isNeedLoginFeature;
	}

	public void setNeedLoginFeature(boolean isNeedLoginFeature) {
		this.isNeedLoginFeature = isNeedLoginFeature;
	}

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

	public void setAllowedUris(List<String> allowedUris) {
		this.allowedUris = allowedUris;
	}
}
