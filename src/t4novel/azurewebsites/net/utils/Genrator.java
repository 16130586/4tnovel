package t4novel.azurewebsites.net.utils;

public class Genrator {
	private static Genrator instance;
	private int lastGenrated;
	private Genrator() {}
	
	public synchronized static Genrator getInstance() {
		if(instance == null)
			instance = new Genrator();
		return instance;
	}
	public synchronized int nextInt() {
		this.lastGenrated = this.lastGenrated + 1;
		return this.lastGenrated;
	}
	public static synchronized void loadLastGenratedValueFromDatabase() {
		// Qua trinh goi ham nay o starting-time cua life-circle
		// TODO tao 1 bang trong database de luu gia tri autoincreament.... bao gom (id , value , lastDate)
		// TODO sau do load tu database len -> dua vao lastGenrated 
		
	}
	public static synchronized void saveLastGenratedValueToDatabase() {
		// qua trinh goi ham nay o shutdown-time cua life-circle
		// TODO tao query luu vao database
	}
}
