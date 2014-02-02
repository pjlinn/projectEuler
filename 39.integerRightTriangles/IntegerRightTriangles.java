/*
	If p is the perimeter of a right angle triangle with integral length 
	sides, {a,b,c}, there are exactly three solutions for p = 120.

	{20,48,52}, {24,45,51}, {30,40,50}

	For which value of p ≤ 1000, is the number of solutions maximised?

	--------
	Little slow, basically just brute forced it, should re-look at, maybe
	make the loop recursive and figure out some bounds so I don't have
	to try every single number.
*/

import java.util.ArrayList;

public class IntegerRightTriangles {

	// private static ArrayList<Integer> factors(int perimeter) {
	// 	ArrayList<Integer> factors = new ArrayList<Integer>();

	// 	for (int i = 1; i <= Math.sqrt(perimeter); i++) {
			
	// 	}
	// }

	private static boolean isPythagorean(int a, int b, int c) {
		boolean isPythagorean = false;

		if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
			isPythagorean = true;
		}
		return isPythagorean;
	}

	public static void main(String[] args) {

		int perimeter = 120;
		int a = 1;
		int b = 0;
		int c = 0;

		// while(a < perimeter) {
		// 	b = perimeter - a;
		// 	c = 
		// 	if (isPythagorean(a, b, )) {
				
		// 	}
		// }

		int result = Integer.MIN_VALUE;
		int count = 0;

		for (perimeter = 1; perimeter < 1001; perimeter++) {
			for (int i = 1; i < perimeter / 3; i++) {
				for (int j = 1; j < perimeter / 2; j++) {
					int k = perimeter - i - j;
					if (isPythagorean(i, j, k)) {
						count++;
					}
				}
			}
				// result = (count > result) ? count : result;
				if (result < count) {
					result = count;
					System.out.println(count + " " + perimeter);
				}
				count = 0;
		}
	}
}