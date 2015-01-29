/*
	Euler's Totient function, φ(n) [sometimes called the phi function], is 
	used to determine the number of positive numbers less than or equal to 
	n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 
	8, are all less than nine and relatively prime to nine, φ(9)=6.
	The number 1 is considered to be relatively prime to every positive 
	number, so φ(1)=1.

	Interestingly, φ(87109)=79180, and it can be seen that 87109 is a 
	permutation of 79180.

	Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation 
	of n and the ratio n/φ(n) produces a minimum.

	--------------------------------------------------

	Two integers are relatively prime if they share no common 
	positive factors (divisors) except 1. Using the notation 
	(m,n) to denote the greatest common divisor, two integers  m 
	and n are relatively prime if (m,n)=1. Relatively prime integers 
	are sometimes also called strangers or coprime and are denoted 
	m_|_n. The plot above plots m and n along the two axes and colors 
	a square black if (m,n)=1 and white otherwise (left figure) and 
	simply colored according to (m,n) (right figure).

	--------------------------------------------------

	Euclid's Algorithm

	What do you do if you want to find the GCF of more than 2 very large 
	numbers such as 182664, 154875 and 137688? If you have a calculator 
	for factoring or a calculator for prime factorization or even this 
	GCF calculator it would be easy but if you needed to solve the problem 
	by hand it could become arduous.

	Euclid's algorithm gives us a process for finding the GCF of 2 numbers. 
	From the larger number, subtract the smaller number as many times as you 
	can until you have a number that is smaller than the small number. (or 
	without getting a negative answer) Now, using the original small number 
	and the result, a smaller number, repeat the process. Repeat this until 
	the last result is zero, and the GCF is the next-to-last small number r
	esult. Also see our Euclid's Algorithm Calculator.
	
	--------------------------------------------------

	Prime Factorization

	To find the GCF by prime factorization, we list out all of the prime 
	factors of each number or get them with a prime factor calculator.  
	We then list the common prime factors for each occurrence of each 
	nd multiply these together to get the GCF .

	You will see that as our numbers to factor get larger, this prime 
	factorization method may be easier than straight factoring.

	------------------------------------------------

	It seems more and more unlikely that I can somehow generate a 
	complete list of the phi(n) for every number up to 10,000,000.
	I need to think of something else to narrow down the search. 
	Prime numbers have the greatest phi(n), but they will never 
	be permutation I don't think.


	----------------
	I guess I'll try to limit the number of possibilities in the 
	set? Prime numbers will give the ratio closest to 1, but 
	will never be permutation. I also think numbers divisible
	by 2 will be too high a ratio and maybe I can expand that
	logic to 3, 4, etc. until I have a workable list.

*/

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class TotientPermutation {
	
	HashSet<Integer> setOfPrimes = new HashSet<Integer>();
	ArrayList<Integer> listOfPrimes = new ArrayList<Integer>();


	public TotientPermutation() {
	}
	/*
		Calculates a list and hashset of primes up to a certain limit
		for reference
	*/
	private ArrayList<Integer> calculatePrimeList(int limit) {
		
		listOfPrimes.add(2);
		setOfPrimes.add(2);

		boolean isPrime = true;

		for (int i = 3; i <= limit; i++) {
			isPrime = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				listOfPrimes.add(i);
				setOfPrimes.add(i);
			}
		}
		return listOfPrimes;
	}
	/*
		Returns an ArrayList of primeFactors of a given number. Not useful
		as I want only distinct which is easier in a HashSet.

		Recursive though
	*/
	private static ArrayList<Integer> primeFactorization(ArrayList<Integer> listOfPrimes,
		Integer n, ArrayList<Integer> primeFactors){
		/*
			Trivial case for recursion
		*/
		if (n == 1) {
			Collections.sort(primeFactors);
			return primeFactors;
		}
		/*
			If the number is prime, then return 1 and n
			Would work better with a hashset, obviously
		*/
		if (listOfPrimes.contains(n)) {
			// primeFactors.add(1); primeFactors.add(n);
			Collections.sort(primeFactors);
			return primeFactors;
		}
		/*
			Starting from the bottom, divide n by a prime other than 1. It
			shouldn't ever be greater since if it can't be divided by a prime
			it is prime and thus should be taken care of above.
		*/
		for (Integer prime : listOfPrimes) {
			if (prime == 1) { continue;	}

			if (prime > n) { break; }
			else {
				if (n % prime == 0) {
					primeFactors.add(prime);
					n = n / prime;
					return primeFactorization(listOfPrimes, n, primeFactors);
				}
			}
		}

		// Shouldn't reach here.
		primeFactors.add(-999999);
		return	primeFactors;
	}
	/*
		HashSet prime factorizaion method -- basically a copy of the
		ArrayList but it returns a HashSet
		--------------
	*/
	private static HashSet<Integer> primeFactorization(ArrayList<Integer> listOfPrimes,
		Integer n, HashSet<Integer> primeFactors, HashSet<Integer> setOfPrimes){
		/*
			Trivial case for recursion
		*/
		if (n == 1) {
			// Collections.sort(primeFactors);
			return primeFactors;
		}
		/*
			If the number is prime, then return 1 and n
			Would work better with a hashset, obviously
		*/
		if (setOfPrimes.contains(n)) {
			// primeFactors.add(1); 
			// primeFactors.add(n);
			return primeFactors;
		}
		/*
			Starting from the bottom, divide n by a prime other than 1. It
			shouldn't ever be greater since if it can't be divided by a prime
			it is prime and thus should be taken care of above.
		*/
		for (Integer prime : listOfPrimes) {
			if (prime == 1) { continue;	}

			if (prime > n) { break; }
			else {
				if (n % prime == 0) {
					primeFactors.add(prime);
					n = n / prime;
					if (setOfPrimes.contains(n)) {
						primeFactors.add(n);
					}
					return primeFactorization(listOfPrimes, n, primeFactors, setOfPrimes);
				}
			}
		}

		// Shouldn't reach here.
		primeFactors.add(-999999);
		return	primeFactors;

	}

	/*
		Returns true if both are relatively prime, false otherwise
		by comparing their prime factors.
		---------
		Took this from my last problem code 69, but this is a super
		crappy way of brute force calculating what numbers are 
		prime factors of another
	*/
	private static boolean relativePrime (HashSet<Integer> primeFactorsM, 
		HashSet<Integer> primeFactorsN) {

		for (Integer primeFactor : primeFactorsM) {
			if (primeFactorsN.contains(primeFactor)) { return false;}
		}
		return true;
	}
	/*
		Calculate Phi N using an approach from project euler solution to
		the previous problem (69) - Way faster
	*/
	private static Double calculatePhiN (Integer n, HashSet<Integer> primeFactors){
		Double phiN = (double)n;

		for (Integer primeFactor : primeFactors) {
			phiN *= 1.0 - (1.0 / primeFactor);
		}

		return phiN;
	}
	/*
		Checks to see if the 2 integers are permutations
	*/
	private static boolean permutationCheck(Integer n, Integer phiN) {
		List<Integer> nList = new ArrayList<Integer>();
		List<Integer> phiNList = new ArrayList<Integer>();		

		String sN = String.valueOf(n);
		String sPhiN = String.valueOf(phiN);

		Integer sNLength = sN.length();
		Integer sPhiNLength = sPhiN.length();

		// if they aren't the same size they can't be permutations
		if (sNLength != sPhiNLength) { return false; }

		for (int i = 0; i < sNLength; i++) {
			nList.add(Integer.parseInt(sN.substring(i, i + 1)));
			phiNList.add(Integer.parseInt(sPhiN.substring(i, i + 1)));
		}

		Collections.sort(nList);
		Collections.sort(phiNList);

		for (int i = 0; i < sNLength; i++) {
			if (nList.get(i) != phiNList.get(i)) {
				return false;
			}
		}

		return true;
	}

	/*
		Main function
	*/
	public static void main(String[] args) {
		long startTime = System.nanoTime();

		int limit = 10000000;

		TotientPermutation object = new TotientPermutation();
		ArrayList<Integer> setOfNumbers = new ArrayList<Integer>();
		ArrayList<Double> listOfRatios = new ArrayList<Double>();
		/*
			Create an initial set of candidate numbers, 
			unnecessary now that I have to just calculate all of them
		*/
		for (int i = 2; i <= limit; i++) {
			setOfNumbers.add(i);
		}	
		/*
			Create an ArrayList and HashSet of prime numbers up to the limit
		*/
		ArrayList<Integer> listOfPrimes = object.calculatePrimeList(limit);
		HashSet<Integer> setOfPrimes = object.setOfPrimes;
		/*
			Create a HashMap to hold each Integer from the candidate list
			and its HashMap of unique prime factors
		*/
		HashMap<Integer, HashSet<Integer>> primeFactorMap = new HashMap<Integer, HashSet<Integer>>();
		HashMap<Double, Double[]> ratioLookUp = new HashMap<Double, Double[]>();
		/*
			Empty ArrayList and HashSet
		*/
		ArrayList<Integer> emptyList = new ArrayList<Integer>();
		HashSet<Integer> emptySet = new HashSet<Integer>();

		/*
			Trying to make the set of candidate numbers more managable 
			while still having a large enough ratio to produce a minimum.
		*/
		ArrayList<Integer> newSetOfNumbers = new ArrayList<Integer>();
		for (Integer number : setOfNumbers) {
			// Primes are never permutations so never possible answers
			if (setOfPrimes.contains(number)) { continue; } else if (
				   number % 2 == 0 
				|| number % 3 == 0 
				|| number % 5 == 0 
				|| number % 7 == 0
				) {
				continue;
			} else { newSetOfNumbers.add(number); }
		}
		/*
			Fill the map with each number in the candidate set and it's
			associated HashSet of prime factors

			Have to use every number in the set, otherwise I won't get an 
			accurate count of relatively prime numbers
		*/
		for (Integer i : newSetOfNumbers) {
			primeFactorMap.put(i, new HashSet<Integer>(primeFactorization(listOfPrimes, i, emptySet, setOfPrimes)));
			emptySet.clear(); // Not sure why I have to do this, but if I don't the map just appends the list
		}
		/*
			Test the smaller subset of numbers to calculate phiN,
			check permutation, and then check the ratio
		*/
		Double min = 10.0;
		for (Integer number : newSetOfNumbers) {
			Double phiN = 0.0;
			phiN = calculatePhiN(number, primeFactorMap.get(number));
			if (permutationCheck(number, (int)Math.round(phiN))) {
				Double ratio = number / phiN;
					if (ratio < min ) {
						min = ratio;
						System.out.println("N: " + number + " PhiN: " + phiN + " Ratio: " + ratio);		
					}
			}
		}
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime) / 1000000);
	}
}