/*
	The decimal number, 585 = 10010010012 (binary), is palindromic in both 
	bases.

	Find the sum of all numbers, less than one million, which are 
	palindromic in base 10 and base 2.

	(Please note that the palindromic number, in either base, may 
	not include leading zeros.)
*/

import java.util.List;
import java.util.ArrayList;

public class DoubleBasePalindromes {
	/*
		Takes a base 10 integer and returns it's base 2 equivalent
	*/
	private static void base2Converter(int num) {

	}
	/*
		Takes a list as an argument, either base 2 or base 10, and
		returns whether or not it's a palindrome.
	*/
	private static boolean palindromeCheck(List<Integer> numList) {
		boolean isPalindrome = false;
		String forwards = "";
		String backwards = "";

		for (Integer num : numList) {
			forwards += Integer.toString(num);
		}
		for (int i = numList.size() - 1; i >= 0; i--) {
			backwards += Integer.toString(numList.get(i));
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
	private static List<Integer> intToList(int num) {
		List<Integer> intList = new ArrayList<Integer>();

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
			intList.add(digit);
			divisor = divisor / 10;
			num = result;
		}
		return intList;
	}
	/*
		Main method
	*/
	public static void main(String[] args) {
		System.out.println(palindromeCheck(intToList(585)));
	}
}