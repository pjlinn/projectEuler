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
	Okay, the implementation is a COMPLETE DISASTER, however
	I determined a process to do it and that process
	seems to be consisten with the other on PE. 

	Basically generate a list of primes, discard ones that
	won't work and test the ones that will until you 
	get a family of 8 and return the prime, since I went in order
	from lowest to highest.

	Need to re-code, but I got it.
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
	private static HashSet<String> generatePrimeSet(int start, int ceiling) {
		HashSet<String> primeSet = new HashSet<String>();
		for (int i = start; i <= ceiling; i++) {
			if (isPrime(i)) {
				primeSet.add(Integer.toString(i));
			}
		}
		return primeSet;
	}
	// Generates a ArrayList of primes for lookup up to a certain value
	private static ArrayList<String> generatePrimeList(int start, int ceiling) {
		ArrayList<String> primeSet = new ArrayList<String>();
		for (int i = start; i <= ceiling; i++) {
			if (isPrime(i)) {
				primeSet.add(Integer.toString(i));
			}
		}
		return primeSet;
	}
	// If the prime passed doesn't have 2 of the same digit, return true
	private static boolean eliminatePrime(String prime, HashSet<String> primeSet) {
		char[] primeArray = prime.toCharArray();
		int primeLength = prime.length();

		for (int index = 0; index < primeLength; index++) {
			for (int nextIndex = index + 1; nextIndex < primeLength; nextIndex++) {
				char tempIndex = primeArray[index];
				char tempNextIndex = primeArray[nextIndex];
				if (tempIndex == tempNextIndex) {
					int counter = primeFamilyCounter(prime, index, nextIndex, primeSet);
					if (counter == 8) {
						System.out.println(prime);
						return true;
					}
				}
			}
		}

		return false;
	}
	// determine if this prime is in a family of 8
	private static int primeFamilyCounter(String prime, int index, int nextIndex, HashSet<String> primeSet) {
		int counter = 0;
		String result = "";
		char[] primeArray = prime.toCharArray();

		for (int replacementDigit = 0; replacementDigit < 10; replacementDigit++) {
			primeArray[index] = Integer.toString(replacementDigit).charAt(0);
			primeArray[nextIndex] = Integer.toString(replacementDigit).charAt(0);
			for (char digit : primeArray) {
				result += digit;
			}
			if (primeSet.contains(result)) {
				counter++;
			}
			result = "";
		}
		return counter;
	}


	// If the prime passed doesn't have 3 of the same digit, return true
	private static boolean eliminatePrime3(String prime, HashSet<String> primeSet) {
		char[] primeArray = prime.toCharArray();
		int primeLength = prime.length();

		for (int index = 0; index < primeLength; index++) {
			for (int nextIndex = index + 1; nextIndex < primeLength; nextIndex++) {
				for (int nextNextIndex = nextIndex + 1; nextNextIndex < primeLength; nextNextIndex++) {
					char tempIndex = primeArray[index];
					char tempNextIndex = primeArray[nextIndex];	
					char tempNextNextIndex = primeArray[nextNextIndex];
					if (tempIndex == tempNextIndex && tempIndex == tempNextNextIndex) {
						int counter = primeFamilyCounter3(prime, index, nextIndex, nextNextIndex, primeSet);
						if (counter == 8) {
							System.out.println(prime);
							return true;
						}
					}					
				}
			}
		}

		return false;
	}
	// determine if this prime is in a family of 8
	private static int primeFamilyCounter3(String prime, int index, 
		int nextIndex, int nextNextIndex, HashSet<String> primeSet) {
		int counter = 0;
		String result = "";
		char[] primeArray = prime.toCharArray();

		for (int replacementDigit = 0; replacementDigit < 10; replacementDigit++) {
			primeArray[index] = Integer.toString(replacementDigit).charAt(0);
			primeArray[nextIndex] = Integer.toString(replacementDigit).charAt(0);
			primeArray[nextNextIndex] = Integer.toString(replacementDigit).charAt(0);
			for (char digit : primeArray) {
				result += digit;
			}
			if (primeSet.contains(result)) {
				counter++;
			}
			result = "";
		}
		return counter;
	}







	public static void main(String[] args) {
		final int start = 3;
		final int ceiling = 1000000;
		HashSet<String> primeSet = new HashSet<String>(generatePrimeSet(start, ceiling));
		ArrayList<String> primeList = new ArrayList<String>(generatePrimeList(start, ceiling));

		// System.out.println(primeList.contains("56003"));

		for (String prime : primeList) {
			if (eliminatePrime3(prime, primeSet)) {
				System.out.println("Ending...");
				break;
			}
		}

		// System.out.println("primeSet Size: " + primeSet.size() + "\nprimeList Size: " + primeList.size());

		// ArrayList<String> primeFamily = new ArrayList<String>();
		// ArrayList<String> finalPrimeFamily = new ArrayList<String>();



		/*
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
		*/
	}
}