/*
	The arithmetic sequence, 1487, 4817, 8147, in which each of the terms 
	increases by 3330, is unusual in two ways: (i) each of the three terms 
	are prime, and, (ii) each of the 4-digit numbers are permutations of 
	one another.

	There are no arithmetic sequences made up of three 1-, 2-, or 3-digit 
	primes, exhibiting this property, but there is one other 4-digit 
	increasing sequence.

	What 12-digit number do you form by concatenating the three terms 
	in this sequence?

	-----------------

	This kind of works, narrows the possibilites down to ~6...I need
	a better isPermutation method

*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class PrimePermutations {
	
	// generate a list of primes between 1000 and 10,000
	private static ArrayList<Integer> generatePrimeList(int floor, int ceiling) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		for (int i = floor; i <= ceiling; i++) {
			if (isPrime(i)) {
				primeList.add(i);
			}
		}
		return primeList;
	}
	// checks if the number passed is prime, returns a boolean
	private static boolean isPrime(int num) {
		if (num == 2 ) { return true; }
		if (num == 1 ) { return false; }
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	// copy the prime list into a HashSet for faster lookups
	private static HashSet<Integer> generatePrimeSet(ArrayList<Integer> primeList) {
		HashSet<Integer> primeSet = new HashSet<Integer>();
		for (Integer prime : primeList) {
			primeSet.add(prime);
		}
		return primeSet;
	}
	// Checks if the 2 numbers passed are permutations of one another
	private static boolean isPermutation(String a, String b) {
		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < a.length(); i++) {
			sumA += Integer.parseInt(a.substring(i, i+1));
		}
		for (int i = 0; i < a.length(); i++) {
			sumB += Integer.parseInt(b.substring(i, i+1));
		}
		if (sumA == sumB) {
			return true;
		}
		return false;
	}
	// main method
	public static void main(String[] args) {
		ArrayList<Integer> primeList = new ArrayList<Integer>(generatePrimeList(1000,10000));
		HashSet<Integer> primeSet = new HashSet<Integer>(generatePrimeSet(primeList));
		ArrayList<Integer> possiblePrimes = new ArrayList<Integer>();
		int counter = 0;

		Iterator<Integer> it = primeSet.iterator();

		while (it.hasNext()) {
			int temp = it.next();
			int testPrime = temp + 3330;
			boolean print = true;
			counter = (primeSet.contains(testPrime)) ? counter + 1 : 0;
			if (counter == 1) {
				possiblePrimes.add(temp);
				possiblePrimes.add(testPrime);
				testPrime += 3330;
				counter = (primeSet.contains(testPrime)) ? counter + 1 : 0;
			}
			if (counter == 2) {
				possiblePrimes.add(testPrime);
				for (Integer prime : possiblePrimes) {
					if (!isPermutation(Integer.toString(temp), Integer.toString(prime))) {
						print = false;
					}
				}
				if (print == true) {
					System.out.println(possiblePrimes);
				}
			}
			possiblePrimes.clear();
		}
	}
}