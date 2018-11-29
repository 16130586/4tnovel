package t4novel.azurewebsites.net.models;

public enum NovelKind {
	COMPOSE(), TRANSLATE();

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
}
