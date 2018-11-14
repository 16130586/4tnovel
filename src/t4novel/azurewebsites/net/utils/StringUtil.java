package t4novel.azurewebsites.net.utils;

public class StringUtil {
	public static boolean isAllSpace(String string) {
		String[] split = string.split("\\s+");
		if (split.length == 0) {
			return true;
		}
		return false;
	}
}
