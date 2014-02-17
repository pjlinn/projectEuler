/*
	The prime 41, can be written as the sum of six consecutive primes:

	41 = 2 + 3 + 5 + 7 + 11 + 13
	This is the longest sum of consecutive primes that adds to a prime
	 below one-hundred.

	The longest sum of consecutive primes below one-thousand that adds to a 
	prime, contains 21 terms, and is equal to 953.

	Which prime, below one-million, can be written as the sum of the most 
	consecutive primes?

	-------------
	Reused some previous code. Generated a sorted ArrayList of primes, and 
	copied them over to a HashSet for lookup. Brute force looped through
	each possibility, breaking out of the loop once the resulting prime
	was greater then the limit of 1,000,000. 

	Could modify my prime generation to stop once the cumulative sum 
	is > 1,000,000
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ConsecutivePrimeSum {
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
		if (num == 1 || num == 0) { return false; }
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

	public static void main(String[] args) {
		ArrayList<Integer> primeList = new ArrayList<Integer>(generatePrimeList(0, 1000000));		
		HashSet<Integer> primeSet = new HashSet<Integer>(generatePrimeSet(primeList));

		int result = 0;
		int counter = 1;
		int max = 0;
		int answer = 0;

		for (int i = 0; i < primeList.size(); i++) {
			result = primeList.get(i);
			for (int j = i + 1; j < primeList.size(); j++) {
				result += primeList.get(j);
				// upper limit
				if (result > 1000000) {
					break;
				}
				counter++;
				if (primeSet.contains(result)) {
					if (counter > max) {
						max = counter;
						answer = result;
					}
				}
			}
			counter = 1;
		}

		System.out.println("The prime: " + answer + " is made up of " + max + 
			" consecutive primes.");


	}
}