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
	[100,000 (100 x 100) - 998,001 (999 x 999)].  
*/

public class LargestPalProd {
	/*
		Method that tests whether or not the given input is a 
		palindrome. It is really simple and not at all dynamic.
		The length of possible inputs is hardcoded so it only
		works with 6 digit long numbers. 

		It takes the int passed and converts it to a String.
		Then it uses the charAt method to test if the first
		character == the last character and so on and so forth.
	*/
	public static boolean palindromeTest(int num) {
		String x = Integer.toString(num);

		if (x.charAt(0) == x.charAt(x.length() - 1)) {
			if (x.charAt(1) == x.charAt(x.length() - 2)) {
				if (x.charAt(2) == x.charAt(x.length() - 3)) {
					return true;
				}
			} 
		}
		return false;
	}

	public static void main(String[] args) {

		boolean isPalindrome = false;
		/*
			The counter is the int we pass to the palindromeTest
			in the while loop.
		*/
		int counter = 998001;
		/*
			While isPalindrome is false, we test the counter to
			see whether it is a palindrome. If it is we then
			test to see whether it is the product of 2 3 digit
			numbers. We do this by seeing if a 3 digit number
			evenly goes into the palindrome, and if so, whether
			the result of dividing the palindrome by the 3 digit
			number is another 3 digit number.

			Then we print out the palindrome and one of the 3
			digit products and break out.
		*/
		while (!isPalindrome) {
			if (palindromeTest(counter)) {
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