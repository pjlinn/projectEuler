/*
	Surprisingly there are only three numbers that can be written as the 
	sum of fourth powers of their digits:

	1634 = 14 + 64 + 34 + 44
	8208 = 84 + 24 + 04 + 84
	9474 = 94 + 44 + 74 + 44
	As 1 = 14 is not a sum it is not included.

	The sum of these numbers is 1634 + 8208 + 9474 = 19316.

	Find the sum of all the numbers that can be written as the sum of 
	fifth powers of their digits.

	------------
	Things to note:
	- The greatest feasible fifth power sum is 354,294 or (9^5 * 6)
	- The starting position can be higher:
		1^5: 1.0
		2^5: 32.0
		3^5: 243.0
		4^5: 1024.0
		5^5: 3125.0
		6^5: 7776.0
		7^5: 16807.0
		8^5: 32768.0
		9^5: 59049.0
	- A guy on the forums also mentioned that each permutation of the same 
		numbers will give you the same power sum (112233 == 221133) so you
		could create that set of numbers and then just check the equality.
		This would be far less checks than the ~354,294 I did...
*/

import java.lang.Math;

public class DigitFifthPowers {
	public static void main(String[] args) {
		
		int sum = 0;
		int answer = 0;

		for (int i = 300; i < 354294; i++) {
			String num = Integer.toString(i);
			for (int j = 0; j < num.length(); j++) {
				sum += Math.pow(Character.getNumericValue(num.charAt(j)), 5);
			}
			if (sum == i) {
				System.out.println(i);
				answer += i;
			}
			sum = 0;			
		}

		for (int i = 1; i < 10; i++) {
			System.out.println(i + "^5: " + Math.pow(i,5));	
		}

		System.out.println(answer);
	}
}