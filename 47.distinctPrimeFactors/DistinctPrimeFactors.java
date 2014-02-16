/*
	The first two consecutive numbers to have two distinct prime factors are:

	14 = 2 × 7
	15 = 3 × 5

	The first three consecutive numbers to have three distinct prime factors are:

	644 = 2² × 7 × 23
	645 = 3 × 5 × 43
	646 = 2 × 17 × 19.

	Find the first four consecutive integers to have four distinct prime 
	factors. What is the first of these numbers?

	--------
	Solved, but uber clunky and just another complete hack job...Will try
	and go through and make a little bit cleaner.
*/

import java.util.ArrayList;
import java.util.HashSet;

public class DistinctPrimeFactors {
	// ArrayList of primes for an ordered list of primes
	ArrayList<Integer> primes = new ArrayList<Integer>();
	// A HashSet of primes for faster lookups
	HashSet<Integer> hashPrimes = new HashSet<Integer>();
	// A blank HashSet passed in methods
	HashSet<Integer> startingSet = new HashSet<Integer>();
	// Constructor, builds the list of primes
	private DistinctPrimeFactors(int num) {
		this.primes = generatePrimeList(num);
		this.hashPrimes = generatePrimeHashSet(this.primes);
	}
/*
	=========================================================
	Creating my lists of primes
	=========================================================
*/
	// Method returns if the argument passed is a prime number
	private boolean isPrime(int num) {
		if (num == 2 || num == 3) {
			return true;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	// Method to generate prime list
	private ArrayList<Integer> generatePrimeList(int ceiling) {
		for (int i = 2; i <= ceiling; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		return primes;
	}
	// Method to create HashSet of primes for faster 'contains' searches
	private HashSet<Integer> generatePrimeHashSet(ArrayList<Integer> primeList) {
		for (Integer prime : primeList) {
			hashPrimes.add(prime);
		}
		return hashPrimes;
	}

/*
	=========================================================
	Methods which do the work in finding the solution
	=========================================================
*/
	/*
		This returns a boolean if the number passed has the desired number of 
		prime factors, in this case 4 (hard coded). 

		You pass the number you want to test, an ArrayList of ordered primes,
		a HashSet of primes, and a HashSet of unique prime factors found so far
	*/
	private static boolean primeFactorTest(int num, ArrayList<Integer> primeList, 
		HashSet<Integer> primeSet, HashSet<Integer> factors) {
		// if the number passed is prime, then it can't be divisible anymore so this is
		// our terminating condition. We check to see how many unqiue prime factors it
		// has and return a boolean appropriately
		if (primeSet.contains(num)) {
			factors.add(num);
			if (factors.size() == 4) {
				factors.clear();
				return true;
			}
			// It was building on to this list so I had to clear it.
			factors.clear();
		}
		// For each prime in the list, check and see if it's a factor until you reach
		// a prime that is greater or equal to half the tested num. Ex. if we test 14
		// we try 2, 3, 5 and not 7 since we already found 7 when checkng 2
		for (Integer prime : primeList) {
			if (prime >= num / 2) {
				return false;
			} else if (num % prime == 0) {
				int remainder = num / prime;
				factors.add(prime);
				return primeFactorTest(remainder, primeList, primeSet, factors);
			}
		}
		return false;
	}
	// main method
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int temp = 1000000;
		int counter = 0;
		DistinctPrimeFactors x = new DistinctPrimeFactors(temp);

		for (int i = 0; i <= temp; i++) {
			counter = (x.primeFactorTest(i, x.primes, x.hashPrimes, x.startingSet)) ? counter + 1 : 0;

			if (counter == 4) {
				System.out.println(i - 3);
				break;
			}
		}
		System.out.println(" -> Time: " + (System.currentTimeMillis() - startTime) + 
			" ms");
	}
}