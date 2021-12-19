package utils;

import java.util.Random;

public class Rand {
	public static int range(int bottom, int upper) {
		Random rand = new Random(); // instance of random class
		int upperbound = upper;
		// generate random values from bottom-upper
		int result = bottom + rand.nextInt(upperbound);

		return result;
	}
}
