package t4novel.azurewebsites.net.utils;

import java.util.Random;

public class TokenGenrator {
	private static final int[] BASE_NUMBER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static String genrateVerifyCode(int length) {
		Random rd = new Random();
		StringBuilder token = new StringBuilder();
		for (int curNumber = 1; curNumber <= length; curNumber++) {
			token.append(BASE_NUMBER[rd.nextInt(BASE_NUMBER.length)]);
		}
		return token.toString();
	}

}
