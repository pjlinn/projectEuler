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
*/

import java.util.Collections;
import java.util.ArrayList;

public class TotientPermutation {
	/*
		
	*/
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
	/*
		Calculates a list of primes up to a certain limit. This list
		is passed to the primeFactors method
	*/
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
	/*
		Want to try and write a prime factorizaion method
		--------------
		It works, should consider storing the prime factors as a hashset
		for instant lookup as well as only storing unique values.

		Maybe this is fast enough to put together a map of every number
		in my range and their prime factors that I can use for comparison
		to determine phi(n). Probably not fast enough though, still need
		to get the iterations down or think of a math trick...
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
			Would work better wish a hashset, obviously
		*/
		if (listOfPrimes.contains(n)) {
			primeFactors.add(1); primeFactors.add(n);
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
		Main function
	*/
	public static void main(String[] args) {
		
		int limit = 10000;
		ArrayList<Integer> listOfPrimes = calculatePrimeList(limit);
		ArrayList<Integer> emptyList = new ArrayList<Integer>();
		System.out.println(primeFactorization(listOfPrimes, 27, emptyList));
		// System.out.println(calculatePrimeList(9));
		// System.out.println(primeFactors(9, listOfPrimes));
		// for (int i = 2; i <= limit; i++) {
		// 	primeFactors(i, listOfPrimes);
		// }
	}
}