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

	Still can't get the worst case run-time down. There must be
	any easy way mathmatically, but for now I just know that I 
	shouldn't be recalculating factors for numbers I've already
	found. I need an array type map where the index is the number
	and the value is that numbers factors.

	----------------
	n: 510510.0 | # of relative prime: 92160.0 | n / phi(n): 5.539388020833333

	-------------
	Read the forums and this one was very easy. You want to minimize the
	number of relative primes. To do this you want a number who has prime
	factors. You just need to start multiplying the primes, starting with
	2 until you get a value > 1,000,000.

	2 * 3 = 6
	2 * 3 * 5 = 30
	2 * 3 * 5 * 7 = 210
	...
	2 * 3 * 5 * 7 * 11 * 13 * 17 = 510,510 
	
*/ 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;

public class TotientMaximum {

	ArrayList<Integer> listOfPrimes = new ArrayList<Integer>();
	HashSet<Integer> setOfPrimes = new HashSet<Integer>();
	HashMap<Integer, HashSet<Integer>> mapSetOfFactors = new HashMap<Integer, HashSet<Integer>>();
	HashMap<Integer, ArrayList<Integer>> mapListOfFactors = new HashMap<Integer, ArrayList<Integer>>();

	public TotientMaximum () {

		/*
			Create a map of a number and its list and set of factors
			 from 2 to 100000. Creates the list with the object and
			 caches it to avoid recalculating
		*/
		for (int i = 2; i <= 100000; i++) {
			ArrayList<Integer> listOfFactors = new ArrayList<Integer>();
			HashSet<Integer> setOfFactors = new HashSet<Integer>();
			for (int j = 1; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					int result = i / j;
					if (j != 1) { listOfFactors.add(j); setOfFactors.add(j); }
					listOfFactors.add(result); setOfFactors.add(result);
				}
			}
			Collections.sort(listOfFactors);
			mapSetOfFactors.put(i, setOfFactors);
			mapListOfFactors.put(i, listOfFactors);
		}
	}


	// Returns the greatest common positive factor between 2 numbers
	// If it's one, m is a relative prime of n
	public int greatestCommonPositiveFactor (int m, int n) {

		// if (mapOfNumbers.containsKey(m)) {
			// Do this later, if I've found the factors of
			// m already then just find the factors of n (if I need too)
			// and check for common factors...
			// Do I want two maps, one with value of HashSet and one with value ArrayList?
		// }

		// if (m % 2 == 0 && n % 2 == 0) {
		// 	return 2;
		// }

		HashSet<Integer> nFactors = new HashSet<Integer>();
		HashSet<Integer> mFactors = new HashSet<Integer>();
		// ArrayList<Integer> nFactors = new ArrayList<Integer>();
		// ArrayList<Integer> mFactors = new ArrayList<Integer>();
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
		// Collections.sort(mFactors);
		// Collections.reverse(nFactors);
		// Collections.reverse(mFactors);

		// System.out.println(nFactors);

		// for (Integer factor : mFactors) {
			// if (nFactors.contains(factor)) {
				// System.out.println("greatest common divisor: " + factor);
				// return factor;
			// } // else if (nFactors.contains(factor) && factor == 1) {
				// System.out.println("greatest common divisor: " + factor);
			//	return factor;
			// }
		// }

		// System.out.println(nFactors + "\n" + mFactors);
		// Shouldn't get here
		return 1;
	}

	private boolean relativePrime (ArrayList<Integer> listOfFactors, 
		HashSet<Integer> setOfFactors) {

		for (Integer factor : listOfFactors) {
			if (setOfFactors.contains(factor)) { return false;}
		}
		return true;
	}

	/*
		Main function
	*/
	public static void main(String[] args) {

		double maxPhiN = 0;
		double maxN = 0;
		double phiN;
		double maxCounter = 0;

		ArrayList<Integer> holderArrayList = new ArrayList<Integer>();
		HashSet<Integer> holderHashSet = new HashSet<Integer>();

		/*
			Create a class object, which caches the maps 
		*/
		TotientMaximum testX = new TotientMaximum();

		for (int n = 10; n <= 30; n+=10) {
			
			double stoppingNumber = n / maxPhiN;
			double counter = 1.;

			/*
				HashSet to hold the cached HashSet
			*/
			holderHashSet = testX.mapSetOfFactors.get(n);
			/*
				Test to see whether 2 numbers are relatively prime
			*/
			for (int m = 2; m < n; m++) {
				// Even number test since for some reason I increment by 10's
				if (m % 2 == 0) { continue; }

				holderArrayList = testX.mapListOfFactors.get(m);
				// A method to test if relatively prime
				if (testX.relativePrime(holderArrayList, holderHashSet)) {
					counter++;
					// Some optimization that I can stop because the ratio is too small (big?)
					if (counter > stoppingNumber) {
						counter = n;
						break;
					}
				}
			}

			phiN = n / counter;

			if (phiN > maxPhiN) {
				maxPhiN = phiN;
				maxN = n;
				maxCounter = counter;

				System.out.println("n: " + maxN + " | # of relative prime: " + 
					maxCounter +" | n / phi(n): " + maxPhiN);
			}

			if (n % 10000 == 0) {
				System.out.println("Still going at: " + n);
			}
		}

/*
		double maxPhiN = 0; 						// The max phi / n we are looking for
		double maxN = 0; 							// The number with the max phi n
		double phiN;

		// start at n = 2 and try them up to a limit
		for (int n = 10; n <= 1000000; n += 10) {
			if (testX.setOfPrimes.contains(n)) {		
				continue;
			}
			
			int m = n - 1; 							// checking to see if m is a relative prime
			double counter = 0; 					// counter is the number of relative primes < n
			double stoppingNumber = n / maxPhiN; 	// finding a stopping criteria based on the current max
			
			// System.out.println("stopping number = " + stoppingNumber);
			
			// test and count relative primes
			outerWhile:
			while (m >= 1) {
				if (testX.greatestCommonPositiveFactor(m, n) == 1) {
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
				System.out.println("Max n / phi(n): " + maxPhiN + " | n = " + maxN);
			}

			// maxN = (phiN > maxPhiN) ? n : maxN;
			// maxPhiN = (phiN > maxPhiN) ? phiN : maxPhiN; 

			// System.out.println("n: " + n + " | # of relative prime: " + 
			// 	counter +" | n / phi(n): " + n / counter);

		}
*/
/*
		for (int n = 10; n <= 1000000; n += 10) {
			double counter = 1.; // For 1
			double stoppingNumber = n / maxPhiN;
			for (int m = 3; m <= n - 1; m += 2) {
				if (testX.setOfPrimes.contains(m) && n % m != 0) {
					counter++;
					// System.out.println(m);
				} else {
					if (testX.greatestCommonPositiveFactor(m, n) == 1) {
						counter++;
						// System.out.println(m);
					}
				}

				if (counter > stoppingNumber) {
					counter = n; 
					break;
				}
			}
			// System.out.println(counter);
			phiN = n / counter;

			if (phiN > maxPhiN) {
				maxPhiN = phiN;
				maxN = n;
				System.out.println("Max n / phi(n): " + maxPhiN + " | n = " + maxN);
			}
		}

		System.out.println("Max n / phi(n): " + maxPhiN + " | n = " + maxN);
		// System.out.println(greatestCommonPositiveFactor(2,4));
*/
	}
}