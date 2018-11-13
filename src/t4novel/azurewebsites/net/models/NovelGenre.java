package t4novel.azurewebsites.net.models;


public enum NovelGenre {
	ACTION(0, "Action"), DRAMA(1, "Drama"), ISEKAI(2, "Isekai"), 
	MATURE(3, "Mature"), SCHOOL_LIFE(4, "School Life"),SLICE_OF_LIFE(5, "Slice of Life"), 
	SUPER_POWER(6, "Super Power"), WEB_NOVEL(7, "Web Novel"), ADULT(8, "Adult"),
	ECCHI(9, "Ecchi"), INCEST(10, "Incest"), MECHA(11, "Mechanic"), 
	SHOUJO_AI(12, "Shoujo ai"), SPORTS(13, "Sports"),SUPER_NATURAL(14, "Supernatural"), 
	ADVENTURE(15, "Adventure"), FANTASY(16, "Fantasy"), HORROR(17, "Horror"),
	MYSTERY(18, "Mystery"), SHOUJO(19, "Shoujo"), SHOUNEN(20, "Shounen"), 
	SUSPENSE(21, "Suspense"),COMEDY(22, "Comedy"), GENDER_BENDER(23, "Gender Bender"), 
	JOSEI(24, "Josei"), ROMANCE(25, "Romance"),SEINEN(26, "Seinen"), 
	SHOUNEN_AI(27, "Shounen ai"), TRAGEDY(28, "Tragedy");
	private int value;
	private String name;

	private NovelGenre(int value, String name) {
		this.value = value;
		this.name = name;
	}
	public static NovelGenre getGenre(int val) {
		if(val < NovelGenre.values().length)
			return NovelGenre.values()[val];
		throw new IllegalArgumentException("The value that you pass to getGenre(val) is not valid, out of array bound in NovelGenre Enum class");
	}
	public String getDisplayName() {
		return this.name;
	}
	public int getValue() {
		return this.value;
	}

}
