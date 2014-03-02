/*
	By replacing the 1st digit of the 2-digit number *3, it turns 
	out that six of the nine possible values: 13, 23, 43, 53, 73, 
	and 83, are all prime.

	By replacing the 3rd and 4th digits of 56**3 with the same 
	digit, this 5-digit number is the first example having seven 
	primes among the ten generated numbers, yielding the 
	family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. 
	Consequently 56003, being the first member of this family, is 
	the smallest prime with this property.

	Find the smallest prime which, by replacing part of the number 
	(not necessarily adjacent digits) with the same digit, is part 
	of an eight prime value family.

	---------
	First I will generate a hashset of prime numbers up to a certain
	limit to use to check against the numbers I generate. This is
	generally faster than checking if each number is prime.

	Next, I'll write a method to replace a single number then
	exten functionality from there.

	--------

	******
	I'm checking too many numbers. There are redundent numbers
	involved. 10 and 20 and 30 ... result in the same family of
	numbers. and 11, 12, 13 ... all result in the same when I pull
	the second digit. Need to exploit this. Need to think more
	about how I want to do this. I have a gut feeling recursion
	will be best.
*/

import java.util.HashSet;
import java.util.Collection;
import java.util.ArrayList;

public class PrimeDigitReplacements {
	// takes an int as input and returns a boolean whether it's a prime or not
	private static boolean isPrime(int num) {
		if (num == 2) {
			return true;
		} else if (num == 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	// Generates a hashset of primes for lookup up to a certain value
	private static HashSet<Integer> generatePrimeSet(int ceiling) {
		HashSet<Integer> primeSet = new HashSet<Integer>();
		for (int i = 1; i <= ceiling; i++) {
			if (isPrime(i)) {
				primeSet.add(i);
			}
		}
		return primeSet;
	}
	public static void main(String[] args) {
		final int ceiling = 100000;
		HashSet<Integer> primeSet = new HashSet<Integer>(generatePrimeSet(ceiling));
		ArrayList<String> primeFamily = new ArrayList<String>();
		ArrayList<String> finalPrimeFamily = new ArrayList<String>();

		int max = Integer.MIN_VALUE;
		int counter = 0;

		getOut:
		for (int i = 10; i < ceiling; i++) { // Cycle through the range of numbers
			String stringNum = Integer.toString(i); // cast the int as a String
			char[] stringArray = stringNum.toCharArray(); // cast the String as a char array
			int stringLength = stringNum.length(); // Get the length of the number
			for (int j = 0; j < stringLength; j++) { // Work through each digit
				for (int k = 0; k < 10; k++) { // Replace each digit with 0-9
					String tempString = Integer.toString(k); // Cast int k as a string
					char tempChar = tempString.charAt(0); // Cast the string digit as a char
					stringArray[j] = tempChar; // Replace digit in the char array
					String result = ""; // Resulting char array number
					for (char digit : stringArray) { // Builds the char array into the new number
						result += digit;
					}
					int iResult = Integer.parseInt(result);
					if (primeSet.contains(iResult) && iResult > 10) { // If the new number is a prime then 
						counter++; // increase the counter and add the prime to a new family
						primeFamily.add(result); // Add the result to the family
					}
				}
				if (counter > max) { // If the count is high enought set it as the output variables
					max = counter;
					finalPrimeFamily.clear(); // Clear old list
					for (String prime : primeFamily) { // Deep copy
						finalPrimeFamily.add(prime);
					}
				}
				if (counter == 6) { // We are only looking for 8 in the end, so we can use this to break out
					break getOut;
				}
				counter = 0; // Reset the counter
				primeFamily.clear(); // Clear the temp family
			}
		}
		System.out.println(max); // Output the family size
		System.out.println(finalPrimeFamily); // Output the family
	}
}