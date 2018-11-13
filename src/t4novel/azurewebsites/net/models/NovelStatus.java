package t4novel.azurewebsites.net.models;

public enum NovelStatus {
	COMPLETE(0, "Complete"), INPROCESS(1, "In process"), PAUSE(2, "Pause");
	private int value;
	private String text;

	private NovelStatus(int value, String textDisplayed) {
		this.value = value;
		this.text = textDisplayed;
	}

	public int getValue() {
		return this.value;
	}

	public NovelStatus toEnum(int value) {
		if (value < NovelStatus.values().length)
			return NovelStatus.values()[value];
		throw new IllegalArgumentException(
				"The value that you pass to toEnum(val) is not valid, out of array bound in NovelStatus Enum class");
	}

	public String toText() {
		return this.text;
	}
}
