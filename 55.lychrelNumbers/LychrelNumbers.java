/*
	If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.

	Not all numbers produce palindromes so quickly. For example,

	349 + 943 = 1292,
	1292 + 2921 = 4213
	4213 + 3124 = 7337

	That is, 349 took three iterations to arrive at a palindrome.

	Although no one has proved it yet, it is thought that some numbers, 
	like 196, never produce a palindrome. A number that never forms a 
	palindrome through the reverse and add process is called a Lychrel number. 
	Due to the theoretical nature of these numbers, and for the purpose of this 
	problem, we shall assume that a number is Lychrel until proven 
	otherwise. In addition you are given that for every number below 
	ten-thousand, it will either (i) become a palindrome in less than 
	fifty iterations, or, (ii) no one, with all the computing power that 
	exists, has managed so far to map it to a palindrome. In fact, 10677 is 
	the first number to be shown to require over fifty iterations before 
	producing a palindrome: 4668731596684224866951378664 (53 iterations, 
	28-digits).

	Surprisingly, there are palindromic numbers that are themselves 
	Lychrel numbers; the first example is 4994.

	How many Lychrel numbers are there below ten-thousand?

	NOTE: Wording was modified slightly on 24 April 2007 to emphasise 
	the theoretical nature of Lychrel numbers.

	-------
	Solved it, kind of a mess though. First one in a while...
*/

import java.math.BigInteger;
import java.util.ArrayList;

public class LychrelNumbers {
	// Need a method to reverse a number and add it, returns the resulting sum
	private static boolean lychrelTest(BigInteger num) {
		boolean isLychrel = true;
		
		BigInteger reverseNum = new BigInteger("0");
		reverseNum = reverseNumber(num);

		for (int counter = 0; counter < 48; counter++) {
			num = num.add(reverseNum);
			if (isPalindrome(num)) {
				isLychrel = false;
				break;
			}
			reverseNum = reverseNumber(num);
		}
		return isLychrel;
	}
	// Reverse number method
	private static BigInteger reverseNumber(BigInteger num) {
		ArrayList<String> reverseDigits = new ArrayList<String>();

		BigInteger modConstant = new BigInteger("10");
		BigInteger digit = new BigInteger("0");
		BigInteger result = new BigInteger("0");
		final BigInteger zero = new BigInteger("0");

		String reverseString = "";

		while(!num.equals(zero)) {
			digit = num.mod(modConstant);
			num = num.divide(modConstant);
			reverseDigits.add(digit.toString());
		}

		for (String x : reverseDigits) {
			reverseString = reverseString.concat(x);
		}
		BigInteger reverseNum = new BigInteger(reverseString);
		return reverseNum;
	}
	// Need a method that tests to see if it's a palindrome
	// I really only need to pass two arraylists and compare,but don't feel like
	// changing it right now
	private static boolean isPalindrome(BigInteger num) {
		ArrayList<String> digits = new ArrayList<String>();
		ArrayList<String> reverseDigits = new ArrayList<String>();

		BigInteger modConstant = new BigInteger("10");
		BigInteger digit = new BigInteger("0");
		BigInteger result = new BigInteger("0");
		final BigInteger zero = new BigInteger("0");

		boolean isPalindrome = true;

		while(!num.equals(zero)) {
			digit = num.mod(modConstant);
			num = num.divide(modConstant);
			reverseDigits.add(digit.toString());
			digits.add(0, digit.toString());
		}

		for (int index = 0; index < digits.size(); index++) {
			if (!digits.get(index).equals(reverseDigits.get(index))) {
				isPalindrome = false;
				break;
			}
		}

		return isPalindrome;
	}
	// main method
	public static void main(String[] args) {
		// BigInteger test = new BigInteger("4994");
		// BigInteger zero = new BigInteger("0");
		// System.out.println(test.equals(zero));
		// BigInteger ten = new BigInteger("10");
		// long x = 124324345125231321L;
		// System.out.println(isPalindrome(test));
		// System.out.println(reverseNumber(test));
		// System.out.println(lychrelTest(test));

		// System.out.println(test.toString().length());
		// System.out.println(x);

		int counter = 0;
		for (int i = 1; i < 10000; i++) {
			BigInteger x = new BigInteger(Integer.toString(i));
			if (lychrelTest(x)) {
				counter++;
			}
		}
		System.out.println(counter);
	}
}