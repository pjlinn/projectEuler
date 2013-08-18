/*
	A class to test Java functionality I'm not sure of.
*/

import java.lang.Math;
import java.util.*;
// import static org.junit.Assert.*;

public class TestClass {

	// public static double sumDigits(double num) {
	// 	if (num == 0) {
	// 		return 0;
	// 	} else {
	// 		// System.out.println(num / (Math.pow(10, Math.floor(Math.log10(num)))));
	// 		return Math.floor(num / (Math.pow(10, Math.floor(Math.log10(num))))) + sumDigits(num % (Math.pow(10, Math.floor(Math.log10(num)))));
	// 	}
	// }

	public static void main(String[] args) {
		// 600,851,475,143
		// long x, y;

		// long x = 600851475143L;

		// long y = (x / 2);

		// int num = 1331;
		// int mod = Math.round(Math.log10(num));
		// String sNum = Integer.toString(num);
		// System.out.println(sNum[0]);
		// System.out.println(Math.round(Math.log10(1331)));
		// System.out.println(sumDigits(0));
		// System.out.println()
		// assertEquals(0, sumDigits(0));
		// assertEquals(8, sumDigits(1331));
		// assertEquals(16, sumDigits(14641));
		// assertEquals(29, sumDigits(1467821));

		// List<int[]> values = new ArrayList<>();
		// int[] anArray = new int[1];
		// anArray[0] = 100000;
		// values.add(anArray);
		// System.out.println(values.get(0));

		// int[] testArray = new int[1];
		// testArray[0] = 100000;
		// System.out.println(testArray[0]);

		String i = Integer.toString(9009);
		boolean equals = (i.charAt(0) == i.charAt(i.length() - 1));
		System.out.println(equals);

	}


}