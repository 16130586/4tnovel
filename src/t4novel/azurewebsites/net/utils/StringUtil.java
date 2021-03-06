package t4novel.azurewebsites.net.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		bd.append(userName.charAt(0) + "" + userName.charAt(1));
		for (int i = 2; i < userName.length(); i++) {
			bd.append("*");
		}
		return bd.toString();
	}

	public static boolean isValidEmail(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	public static String hashWith256(byte[] toSha256) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] byteOfTextToHash = toSha256;
		byte[] hashedByetArray = digest.digest(byteOfTextToHash);
		String encoded = Base64.getEncoder().encodeToString(hashedByetArray);
		return encoded;
	}
}
