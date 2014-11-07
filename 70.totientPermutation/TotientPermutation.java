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
*/

import java.util.ArrayList;

public class TotientPermutation {
	
	private static ArrayList<Integer> primeFactors(int n, 
		ArrayList<Integer> listOfPrimes) {

		ArrayList<Integer> primeFactors = new ArrayList<Integer>();

		outerloop:
		for (Integer prime : listOfPrimes) {
			if (prime > n) {
				break;
			} else if (n % prime == 0) {
				primeFactors.add(prime);
			}
		}
		return primeFactors;
	}

	private static ArrayList<Integer> calculatePrimeList(int limit) {
		ArrayList<Integer> listOfPrimes = new ArrayList<Integer>();
		listOfPrimes.add(2);

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
			}
		}
		return listOfPrimes;
	}

	public static void main(String[] args) {
		
		int limit = 100000;
		ArrayList<Integer> listOfPrimes = calculatePrimeList(limit);
		// System.out.println(calculatePrimeList(9));
		// System.out.println(primeFactors(9, listOfPrimes));
		for (int i = 2; i <= limit; i++) {
			primeFactors(i, listOfPrimes);
		}
	}
}