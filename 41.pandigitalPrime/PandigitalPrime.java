/*
	We shall say that an n-digit number is pandigital if it makes use of 
	all the digits 1 to n exactly once. For example, 2143 is a 
	4-digit pandigital and is also prime.

	What is the largest n-digit pandigital prime that exists?

	----------
	Generate a list of pandigitals under 1,000,000 since you can't
	have a pandigital with more than 9 digits. Start from the top and
	return the first that is prime, if any. If there is a 9-digit prime
	then I'll just generate a list of all 8 digit primes and do it again
	until I find one.

	Worked, but with some thought I could have avoided starting at 9,
	Divisiblity of 3 rule.
*/

import java.util.ArrayList;

public class PandigitalPrime {

	private static ArrayList<String> permutations = new ArrayList<String>();

		private static ArrayList<String> 
			pandigitalGenerator(ArrayList<String> digits, String output) {
		if (digits.size() == 0) {
			if (Integer.parseInt(output) >= 0) {
				permutations.add(output);	
			}
			return permutations;
		}

		for (int i = digits.size() - 1; i >= 0 ; i--) {
			String temp = digits.get(i);
			String tempOutput = output;
			output += temp;
			digits.remove(i);
			pandigitalGenerator(digits, output);
			digits.add(i, temp);
			output = tempOutput;
		}
		return permutations;
	}

	private static boolean isPrime(int num) {
		boolean isPrime = true;

		if (num == 2 || num == 3) {
			return true;
		}

		for (int i = 2; i < Math.sqrt(num) + 1; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return isPrime;
	}

	public static void main(String[] args) {
		ArrayList<String> digits = new ArrayList<String>();

		for (int i = 1; i < 8; i++) {
			digits.add(Integer.toString(i));
		}
		System.out.println(digits);
		pandigitalGenerator(digits, "");
		int max = Integer.MIN_VALUE;
		for (String pandigital : permutations) {
			if (isPrime(Integer.parseInt(pandigital)) == true) {
				max = (Integer.parseInt(pandigital) > max) ? Integer.parseInt(pandigital) : max;
			}
		}
		System.out.println(max);
	}
}