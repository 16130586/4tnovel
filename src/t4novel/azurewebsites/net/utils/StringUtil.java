package t4novel.azurewebsites.net.utils;

public class StringUtil {
	public static boolean isAllSpace(String string) {
		String[] split = string.split("\\s+");
		if (split.length == 0) {
			return true;
		}
		return false;
	}

	public static String toAsterisk(String userName) {
		StringBuilder bd = new StringBuilder();
		bd.append(userName.charAt(0) + ""  + userName.charAt(1));
		for(int i = 2 ; i < userName.length() ; i ++) {
			bd.append("*");
		}
		return bd.toString();
	}
}
