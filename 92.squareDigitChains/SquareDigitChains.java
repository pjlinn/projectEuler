/*
	A number chain is created by continuously adding the square of 
	the digits in a number to form a new number until it has been 
	seen before.

	For example,

	44 → 32 → 13 → 10 → 1 → 1
	85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89

	Therefore any chain that arrives at 1 or 89 will become stuck 
	in an endless loop. What is most amazing is that EVERY starting 
	number will eventually arrive at 1 or 89.

	How many starting numbers below ten million will arrive at 89?

	-----------
	So I don't use any optimizations, but I found a straightforward
	solution. Basically, I just wrote a recursive function to 
	calculate the square digit value and kept calculating until I
	got to 89 or 1.

	I used a HashSet because at first I thought I was just suppose 
	to count the values that got to 89 first. So I kept track of all
	numbers calculated and quit after I got to a repeated one. This
	gave the wrong answer however, so I re-factored my algorithm and
	checked by making sure the number of 89's + the number of 1's 
	equal the 9,999,999 starting number total.

	One optimization I could try is to have 2 HashSets, 1 to hold
	numbers that eventually become a 1 and the other to hold the
	numbers that eventually become 89. Then I can check each result
	to see if they are in the list and stop there.
*/

import java.util.HashSet;

public class SquareDigitChains {
	// method that will find the arrival number
	private static int arrivalNumber(int num, 
		HashSet<Integer> previousNumbers) {
		int result = 0;
		int modulus = 1;
		int remainder = num % modulus;
		int digit = 0;
		int tempNum = num;

		while(remainder != num) {
			modulus *= 10;
			remainder = num % modulus;
		}

		modulus = modulus / 10;

		while(num > 0) {
			
			remainder = num % modulus;
			digit = num / modulus;
			num = remainder;
			modulus = modulus / 10;

			result += digit * digit;
		}

		if (previousNumbers.contains(result)) {
			return result;
		} else if (result == 1 || result == 89) {
			previousNumbers.add(result);
			return arrivalNumber(result, previousNumbers);
		} else {
			return arrivalNumber(result, previousNumbers);
		}
	}

	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		int counter89 = 0;
		int counter01 = 0;

		for (int i = 1; i < 100000; i++) {
			int result = arrivalNumber(i, set);
			if (result == 89) {
				counter89++;
			} else if (result == 1) {
				counter01++;
			}
		}
		System.out.println(counter89 + " & " + counter01);
	}
}