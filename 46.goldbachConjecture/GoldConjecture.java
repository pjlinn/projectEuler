/*
	It was proposed by Christian Goldbach that every odd composite number
	 can be written as the sum of a prime and twice a square.

	9 = 7 + 2×12
	15 = 7 + 2×22
	21 = 3 + 2×32
	25 = 7 + 2×32
	27 = 19 + 2×22
	33 = 31 + 2×12

	It turns out that the conjecture was false.

	What is the smallest odd composite that cannot be written as the sum 
	of a prime and twice a square?
	-----------

	Complete hack job, generate an arbitrary list of primes and then use 
	those primes to brute force test odd composite numbers.
*/

import java.util.ArrayList;

public class GoldConjecture {
	// Checks to see if the number passed is prime
	private static boolean isPrime(int num) {
		boolean isPrime = true;
		if (num == 2 || num == 3) {
			isPrime = true;
			return isPrime;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				isPrime = false;
				return isPrime;
			}
		}
		return isPrime;
	}

	// Generates an ArrayList of primes up to a certain ceiling
	private static ArrayList<Integer> generatePrimeList(int ceiling) {
		ArrayList<Integer> primes = new ArrayList<Integer>();

		for (int i = 2; i <= ceiling; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		return primes;
	}
	// main method
	public static void main(String[] args) {
		ArrayList<Integer> primes = new ArrayList<Integer>(generatePrimeList(10000));

		boolean goldbachCheck = true;
		int oddCompositeNumber = 9;
		// Complete hack-job just brute forcing it...luckily it didn't take long to find
		while(goldbachCheck) {
			test:
			for (Integer prime : primes) {
				if (prime <= oddCompositeNumber) {
					double testNumRemainder = (oddCompositeNumber - prime) / 2;
					if (Math.sqrt(testNumRemainder) != Math.floor(Math.sqrt(testNumRemainder))) {
						goldbachCheck = false;
					} else {
						goldbachCheck = true;
						break test;
					}
				}
			}
			if (goldbachCheck == false) {
				System.out.println(oddCompositeNumber);
			}
			oddCompositeNumber += 2;
		}
	}
}