/*
	Project Euler Largest Palindrome Product (Problem 4):
	
	A palindromic number reads the same both ways. The largest 
	palindrome made from the product of two 2-digit numbers 
	is 9009 = 91 × 99.

	Find the largest palindrome made from the product of
	two 3-digit numbers.
*/

import java.util.List;
import java.util.ArrayList;
import java.lang.Math.*;

/*
	The possible value range from multiplying 3 digit numbers is
	[100,000 - 998,001]. So I should probably make a list of all 
	integers in this range and then remove ones that aren't 
	palindromes. Then just return the largest. 
*/

public class LargestPalProd {

	// List<Double> value = new ArrayList<>();
	/*
		Simple method that generates an ArrayList of the possible
		values in the range. These are what I will check to see 
		if they are palindromic.
	*/
	// public static List valueRange() {

	// 	List<int[]> values = new ArrayList<>();

	// 	counter = 0;

	// 	for (int i = 998001; i >= 100000; i--) {

	// 	}

	// 	return values;
	// }

	// public static int largestPalindrome() {
	// 	boolean palindrome = false;
	// 	List<int> values = new ArrayList<>();

	// 	while (!palindrome) {
	// 		for (int i = 998001; int >= 100000; i--) {
	// 			values.add
	// 		}
	// 	}
	// }

	// public static boolean largest(int num) {
	// 	if (num % 2) {
			
	// 	}
	// 	double i = Math.floor(num / Math.pow(10,Math.floor(Math.log10(num))));
	// 	double j = Math.floor(num % Math.pow(10,Math.floor(Math.log10(num))));
	// 	if (i == j) {
	// 		return true;
	// 	} 

	// 	return false;
	// }

	public static boolean largestPalindrome(int num) {
		String x = Integer.toString(num);

		if (x.length() % 2 == 0) {
			if (x.charAt(0) == x.charAt(x.length() - 1)) {
				if (x.charAt(1) == x.charAt(x.length() - 2)) {
					if (x.charAt(2) == x.charAt(x.length() - 3)) {
						return true;
					}
				} 
			}
			return false;
		} else {
			if (x.charAt(0) == x.charAt(x.length() - 1)) {
				if (x.charAt(1) == x.charAt(x.length() - 2)) {
					return true;
				}
			}
			return false;
		}
	}

	public static void main(String[] args) {
		// List<Integer> possibleNum = new ArrayList<>();
		// possibleNum = valueRange();

		// System.out.println(possibleNum.get(0));

		// List<Double> num = new ArrayList<>();
		// num.add(Math.floor(654321 / Math.pow(10,Math.floor(Math.log10(654321)))));
		// int i = num.size();

		boolean isPalindrome = false;

		int counter = 998001;

		while (!isPalindrome) {
			if (largestPalindrome(counter)) {
				for (int i = 999; i >= 999/2; i--) {
					if (counter % i == 0) {
						if ((counter / i) < 1000) {
							System.out.println(counter);
							System.out.println(i);
							isPalindrome = true;
							break;	
						}
					}
				}
			}
			counter -= 1;
		}
	}
}