package utils;

import java.util.Random;

public class Rand {
	public static Integer range(Integer bottom, Integer upper) {
		Random rand = new Random(); // instance of random class
		Integer upperbound = upper;
		// generate random values from bottom-upper
		Integer result = bottom + rand.nextInt(upperbound);

		return result;
	}
}
