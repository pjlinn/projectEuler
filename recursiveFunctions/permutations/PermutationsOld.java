/*
	Permutation method:

	Kind of works, but not really. It's ridiculously clunky and doesn't work
	when there are zeros in the starting number amongst other problems.
*/

import java.util.ArrayList;

public class Permutations {
	/*
		Permutation method
		Returns an ArrayList of possible permutations

		Doesn't work with integer > 3 in length
	*/
	static public ArrayList<Integer> permutationGenerator(ArrayList<Integer> digits) {
		ArrayList<Integer> permutations = new ArrayList<Integer>();
		int holder1, holder2;
		int size = digits.size();
		int counter = 0;
		int iterations = factorial(size);
		String concatenatedNum = "";

		if (size == 1) {
			permutations.add(digits.get(0));
			return permutations;
		}

		while(counter < iterations) {
			size = digits.size();
			while(size - 2 >= 0) {
				holder1 = digits.get(size - 1);
				holder2 = digits.get(size - 2);

				digits.set(size - 1, holder2);
				digits.set(size - 2, holder1);

				for (Integer digit : digits) {
					concatenatedNum = concatenatedNum + "" + digit;
				}

				permutations.add(Integer.parseInt(concatenatedNum));

				size--;
				counter++;
				concatenatedNum = "";
			}
		}
		return permutations;
	}
	/*
		Int length method
			Takes an int as an argument
			Returns an ArrayList with each digit of the int in its own
				index from 0 to n
			This ArrayList will be passed into the permutationGenerator
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
		// A quick loop to break off each digit and add it to the ArrayList
		while(divisor >= 1) {
			newNum = newNum % divisor;
			digit = num / divisor;
			digits.add(digit);
			divisor = divisor / 10;
			num = newNum;
		}
		return digits;
	}
	/*
		Factorial method

		Passed the length of the number to calculate the number of permutations
	*/
	static public int factorial(int length) {
		if (length == 0) {
			return 1;
		} else {
			return length * factorial(length - 1);
		}
	}
	/*
		Main method
	*/
	public static void main(String[] args) {
		System.out.println(permutationGenerator(intLength(997793)));
	}
}