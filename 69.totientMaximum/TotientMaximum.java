/*
	Euler's Totient function, φ(n) [sometimes called the phi function], 
	is used to determine the number of numbers less than n which are 
	relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all 
	less than nine and relatively prime to nine, φ(9)=6.

	n	Relatively Prime	φ(n)	n/φ(n)
	2	1	1	2
	3	1,2	2	1.5
	4	1,3	2	2
	5	1,2,3,4	4	1.25
	6	1,5	2	3
	7	1,2,3,4,5,6	6	1.1666...
	8	1,3,5,7	4	2
	9	1,2,4,5,7,8	6	1.5
	10	1,3,7,9	4	2.5
	It can be seen that n=6 produces a maximum n/φ(n) for n ≤ 10.

	Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum.
	-------------------------------------------------------------

	Two integers are relatively prime if they share no common 
	positive factors (divisors) except 1. Using the notation 
	(m,n) to denote the greatest common divisor, two integers  m 
	and n are relatively prime if (m,n)=1. Relatively prime integers 
	are sometimes also called strangers or coprime and are denoted 
	m_|_n. The plot above plots m and n along the two axes and colors 
	a square black if (m,n)=1 and white otherwise (left figure) and 
	simply colored according to (m,n) (right figure).



	---------------

	It seems like you just need to test primes and it will always
	include the n-1 value for some reason. Need to figure out more 
	about what numbers I need to try. I know I can skip all primes
	because they have too many relative primes.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class TotientMaximum {

	ArrayList<Integer> listOfPrimes = new ArrayList<Integer>();

	public TotientMaximum () {
		listOfPrimes.add(2);
		boolean isPrime = true;

		for (int i = 3; i <= 1000000; i += 2) {
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
	}


	// Returns the greatest common positive factor between 2 numbers
	// If it's one, m is a relative prime of n
	public static int greatestCommonPositiveFactor (int m, int n) {
		HashSet<Integer> nFactors = new HashSet<Integer>();
		// ArrayList<Integer> nFactors = new ArrayList<Integer>();
		ArrayList<Integer> mFactors = new ArrayList<Integer>();
		for (int i = 1; i <= Math.sqrt(n); i++ ) {
			if (n % i == 0) {
				int divisor = i;
				int result = n / i;
				if (i != 1) { nFactors.add(i); }
				nFactors.add(result);
			}
		}

		for (int i = 1; i <= Math.sqrt(m); i++) {
			if (m % i == 0) {
				int divisor = i;
				int result = m / i;
				if (i != 1) { mFactors.add(i); }
				mFactors.add(result);
			}
		}

		// Collections.sort(nFactors);
		Collections.sort(mFactors);
		// Collections.reverse(nFactors);
		Collections.reverse(mFactors);

		// System.out.println(nFactors);

		for (Integer factor : mFactors) {
			if (nFactors.contains(factor)) {
				// System.out.println("greatest common divisor: " + factor);
				return factor;
			} // else if (nFactors.contains(factor) && factor == 1) {
				// System.out.println("greatest common divisor: " + factor);
			//	return factor;
			// }
		}

		// System.out.println(nFactors + "\n" + mFactors);
		// Shouldn't get here
		return 1;
	}

	public static void main(String[] args) {

		TotientMaximum testX = new TotientMaximum();

		double maxPhiN = 0; 						// The max phi / n we are looking for
		double maxN = 0; 							// The number with the max phi n

		// start at n = 2 and try them up to a limit
		for (int n = 2; n <= 30; n++) {
			
			int m = n - 1; 							// checking to see if m is a relative prime
			double counter = 0; 					// counter is the number of relative primes < n
			double stoppingNumber = n / maxPhiN; 	// finding a stopping criteria based on the current max
			
			// System.out.println("stopping number = " + stoppingNumber);
			
			// test and count relative primes
			outerWhile:
			while (m >= 1) {
				if (greatestCommonPositiveFactor(m, n) == 1) {
					counter++;

					if (counter > stoppingNumber) {
						 // counter = n;
						 // break outerWhile;
					}
				}
				m--;
			}

			double phiN = n / counter; // the number of numbers less than n which are relatively prime to n

			if (phiN > maxPhiN) {
				maxPhiN = phiN;
				maxN = n;
			}

			// maxN = (phiN > maxPhiN) ? n : maxN;
			// maxPhiN = (phiN > maxPhiN) ? phiN : maxPhiN; 

			System.out.println("n: " + n + " | # of relative prime: " + 
				counter +" | n / phi(n): " + n / counter);

		}

		System.out.println("Max n / phi(n): " + maxPhiN + " | n = " + maxN);
		// System.out.println(greatestCommonPositiveFactor(2,4));
	}
}