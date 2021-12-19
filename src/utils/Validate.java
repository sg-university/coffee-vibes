package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Validate {

	public static boolean range(Integer x, Integer a, Integer b) {
		if (x < a || x > b)
			return false;
		return true;
	}

	public static boolean range(Integer x, Double a, Double b) {
		if (x < a || x > b)
			return false;
		return true;
	}

	public static boolean range(Double x, Integer a, Integer b) {
		if (x < a || x > b)
			return false;
		return true;
	}

	public static boolean range(Double x, Double a, Double b) {
		if (x < a || x > b)
			return false;
		return true;
	}

	public static boolean range(String str, Integer a, Integer b) {
		int len = str.length();
		if (len < a || len > b)
			return false;
		return true;
	}

	public static boolean range(String str, Double a, Double b) {
		int len = str.length();
		if (len < a || len > b)
			return false;
		return true;
	}

	public static boolean isDouble(String str) {
		if (str.length() == 0)
			return false;

		try {
			Double.parseDouble(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean isInteger(String str) {
		if (str.length() == 0)
			return false;

		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean isnum(String str) {
		if (str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}

		return true;
	}

	public static boolean isalpha(String str) {
		if (str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isAlphabetic(str.charAt(i)))
				return false;
		}

		return true;
	}

	public static boolean isalnum(String str) {
		if (str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {
			if (!(Character.isAlphabetic(str.charAt(i)) || Character.isDigit(str.charAt(i))))
				return false;
		}

		return true;
	}

	public static boolean isDateValid(String dateStr, String dateFormat) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isalnumAll(String str) {
		if (str.length() == 0)
			return false;

		boolean isalnum = false, isalpha = false, isnum = false;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isAlphabetic(str.charAt(i)))
				isalpha = true;

			if (Character.isDigit(str.charAt(i)))
				isnum = true;

			isalnum = isalpha && isnum;
		}
		return isalnum;
	}

	public static boolean all(String str, String[] compare) {
		for (String strToCompare : compare) {
			if (!str.equals(strToCompare))
				return false;
		}
		return true;
	}

	public static boolean any(String str, String[] compare) {
		for (String strToCompare : compare) {
			if (str.equals(strToCompare))
				return true;
		}
		return false;
	}

	public static boolean any(Character c, Character[] compare) {
		for (Character charToCompare : compare) {
			if (c == charToCompare)
				return true;
		}
		return false;
	}

	public static boolean all(Character c, Character[] compare) {
		for (Character charToCompare : compare) {
			if (c != charToCompare)
				return false;
		}
		return true;
	}

	public static boolean all(int num, int[] compare) {
		for (int numToCompare : compare) {
			if (num != numToCompare)
				return false;
		}
		return true;
	}

	public static boolean any(int num, int[] compare) {
		for (int numToCompare : compare) {
			if (num == numToCompare)
				return true;
		}
		return false;
	}
}
