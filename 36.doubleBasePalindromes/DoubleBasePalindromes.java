/*
	The decimal number, 585 = 10010010012 (binary), is palindromic in both 
	bases.

	Find the sum of all numbers, less than one million, which are 
	palindromic in base 10 and base 2.

	(Please note that the palindromic number, in either base, may 
	not include leading zeros.)

	---------

	Basically I find all base 10 palidrome numbers, and then convert
	them to base2 and check if they are palindromes. 

	Created my own base2 converter, but you can also use:
		Integer.toString(int i, int radix)

	Probably also a reverse method
*/

import java.util.List;
import java.util.ArrayList;

public class DoubleBasePalindromes {
	/*
		Takes a base 10 integer and returns it's base 2 equivalent
	*/
	private static List<Double> base2Converter(double base10Num) {
		final double two = 2;
		final double one = 1;
		final double zero = 0;
		List<Double> digits = new ArrayList<Double>();
		double index = 0;
		double divisor = Math.pow(two, index);
		double remainder = 0;
		String result = "";

		while(base10Num % divisor != base10Num) {
			index++;
			divisor = Math.pow(two, index);
		}
		
		index--;

		while(index >= 0) {
			double mod = Math.pow(two, index);
			
			if (base10Num % mod == base10Num) { digits.add(zero); }
			else if(base10Num % mod == 0) { digits.add(one); }
			else { digits.add(one); } 

			base10Num = base10Num % mod;
			index--;
		}
		return digits;
	}
	/*
		Takes a list as an argument, either base 2 or base 10, and
		returns whether or not it's a palindrome.
	*/
	private static boolean palindromeCheck(List<Double> numList) {
		boolean isPalindrome = false;
		String forwards = "";
		String backwards = "";

		for (double num : numList) {
			forwards += Double.toString(num);
		}
		for (int i = numList.size() - 1; i >= 0; i--) {
			backwards += Double.toString(numList.get(i));
		}

		isPalindrome = (forwards.equals(backwards)) ? true : false;

		return isPalindrome;
	}
	/*
		intToList takes an integer and puts each digit into it's own index.

		***
		Actually this will only work with base10 numbers, I should look
		into making it more dynamic by possibly adding a second argument
		that will indicate what base the num is in
	*/
	private static List<Double> intToList(int num) {
		List<Double> intList = new ArrayList<Double>();

		int divisor = 1;
		int digit;
		int result = num;
		// Determines starting divisor
		while(num % divisor != num) {
			divisor *= 10;
		}
		divisor /= 10;
		// A loop to break off each digit and add it individually to the lsit
		while(divisor >= 1) {
			result %= divisor; // number with the leftmost digit broken off
			digit = num / divisor; // leftmost digit
			intList.add((double)digit);
			divisor = divisor / 10;
			num = result;
		}
		return intList;
	}
	/*
		Main method
	*/
	public static void main(String[] args) {
		List<Double> base10Palindromes = new ArrayList<Double>();
		int sum = 0;
		for (int i = 1; i < 1000000; i++) {
			if (palindromeCheck(intToList(i))) {
				base10Palindromes.add((double)i);
			}
		}
		for (Double base10Palindrome : base10Palindromes) {
			if(palindromeCheck(base2Converter(base10Palindrome))) {
				// System.out.println(base10Palindrome);
				sum += base10Palindrome;
			}
		}
		System.out.println(sum);
		// System.out.println(intToList(585));
		// System.out.println(base2Converter(585));
		// System.out.println(palindromeCheck(base2Converter(585)));
	}
}