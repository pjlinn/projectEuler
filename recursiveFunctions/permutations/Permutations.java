/*
	Permutation methods
*/

import java.util.ArrayList;

public class Permutations {
	/*
		Permutation method
	*/
	static public ArrayList<Integer> permutationGenerator(int num) {
		ArrayList<Integer> permutations = new ArrayList<Integer>();
		String holder1, holder2;
		// int length = num.length();

		// if (length == 1) {
		// 	permutations.add(num);
		// 	return permutations;
		// }

		// while(!permutation.equal(num)) {
		// 	holder1 = num.substring(length);
		// 	holder2 = num.substring(length - 1, length);
		// 	num
		// }

		return permutations;
	}
	/*
		Int length method
			Takes an int as an argument
			Returns an Arraylist with each digit of the int in its own
				index from 0 to n
			This Arraylist will be passed into the permutationGenerator
				to generate all the possible permutation of this int
	*/
	static public ArrayList<Integer> intLength(int num) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int divisor = 1;
		int digit;
		int newNum = num;
		// Determines starting divisor for the passed int
		while(num % divisor != num) {
			divisor = divisor * 10;
		}
		divisor = divisor / 10;
		// A quick loop to break off each digit and add it to the Arraylist
		while(divisor >= 1) {
			newNum = newNum % divisor;
			digit = num / divisor;
			digits.add(digit);
			divisor = divisor / 10;
			num = newNum;
		}
		return digits;
	}
	public static void main(String[] args) {
		System.out.println(intLength(10002));
	}
}