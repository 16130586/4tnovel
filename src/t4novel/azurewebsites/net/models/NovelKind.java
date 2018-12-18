package t4novel.azurewebsites.net.models;

public enum NovelKind {
	COMPOSE("Sáng tác"), TRANSLATE("Truyện dịch");
	private String name;

	private NovelKind(String name) {
		this.name = name;
	}

	public static NovelKind getNovelKind(String name) {
		for (NovelKind kind : NovelKind.values()) {
			if (kind.toText().equalsIgnoreCase(name))
				return kind;
		}
		return null;
	}

	public String toText() {
		return this.name();
	}

	public String getDisplayedName() {
		return this.name;
	}
}
