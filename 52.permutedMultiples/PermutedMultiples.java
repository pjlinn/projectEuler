/*
	It can be seen that the number, 125874, and its double, 251748, 
	contain exactly the same digits, but in a different order.

	Find the smallest positive integer, x, such that 2x, 3x, 4x, 
	5x, and 6x, contain the same digits.	
*/

import java.util.Collections;
import java.util.ArrayList;

public class PermutedMultiples {
	// Method taks a num as an argument, and returns its digits
	private static ArrayList<Integer> returnDigits(int num) {
		ArrayList<Integer> digits = new ArrayList<Integer>();

		int base = 1;
		int modulusRemainder = num % base;
		int digit = Integer.MIN_VALUE;

		while(modulusRemainder != num) {
			base *= 10;
			modulusRemainder = num % base;
		}

		base = base / 10;

		while(base > 0) {
			digit = num / base;
			num = num % base;
			base = base / 10;
			digits.add(digit);
		}

		Collections.sort(digits);

		return digits;
	}

	public static void main(String[] args) {
		int counter = 0;
		int testNum = 0;
		int nextTestNum = 0;

		outerloop:
		for (int i = 100000; i < 1000000; i++) {
			for (int j = 1; j < 6; j++) {
				testNum = i * j;
				nextTestNum = i * (j +1);
				if (returnDigits(testNum).equals(returnDigits(nextTestNum))) {
					counter++;
				} else {
					break;
				}
				if (counter == 6) {
					System.out.println("Success! " + i);
					break outerloop; 
				}
			}			
		}


		// System.out.println(returnDigits(125874).equals(returnDigits(251748)));
	}
}