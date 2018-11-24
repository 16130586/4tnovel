package t4novel.azurewebsites.net.models;

public enum NovelKind {
	COMPOSE(0, "Compose") , TRANSLATE(1, "Translate");
	private int val;
	private String text;
	private NovelKind(int val, String text) {
		this.val = val;
		this.text = text;
	}
	public static NovelKind getNovelKind(int val) {
		if(val < NovelKind.values().length)
			return NovelKind.values()[val];
		throw new IllegalArgumentException("The value that you pass to getNovelKind(val) is not valid, out of array bound in NovelKind Enum class");
	}
	public String toText() {
		return this.text;
	}
	public int getValue() {
		return this.val;
	}
}
