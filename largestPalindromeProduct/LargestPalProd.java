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

/*
	The possible value range from multiplying 3 digit numbers is
	[100,000 - 998,001]. So I should probably make a list of all 
	integers in this range and then remove ones that aren't 
	palindromes. Then just return the largest. 
*/

public class LargestPalProd {
	/*
		Simple method that generates an ArrayList of the possible
		values in the range. These are what I will check to see 
		if they are palindromic.
	*/
	public static List valueRange() {

		List<Integer> values = new ArrayList();

		for (int i = 100000; i <= 998001; i++) {
			values.add(i);
		}

		return values;
	}

	public static void main(String[] args) {
		System.out.println(valueRange());
	}
}