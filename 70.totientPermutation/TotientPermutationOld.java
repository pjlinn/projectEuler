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
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;

public class TotientPermutation {
	
	HashMap<Double, Double> mapOfRelativePrimes = new HashMap<Double, Double>();
	HashSet<Double> setOfNonRelativePrimes = new HashSet<Double>();
	ArrayList<Double> listOfPrimes = new ArrayList<Double>();
	HashSet<Double> setOfPrimes = new HashSet<Double>();

	Double limit = 10.;
	Double counter = 0.;
	Double nextFactor = 0.;
	Double result = 0.;
	Double nonRelativePrimes = 0.;

	boolean isPrime = true;

	public TotientPermutation() {
		
		listOfPrimes.add(2.);
		setOfPrimes.add(2.);

		for (double i = 3; i <= limit; i += 2) {
			isPrime = true;
			for (double j = 2; j <= Math.sqrt(i); j++) {
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

		/*
		for (Double i = 2.; i <= limit; i++) {
			counter = 0.;
			setOfNonRelativePrimes.add(i);
			for (Double j = 2.; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					if (j == 2) { result = i / j; setOfNonRelativePrimes.add(result);}
					if (setOfNonRelativePrimes.contains(j)) { continue; }
					nextFactor = j;
					while (nextFactor < i) {
						setOfNonRelativePrimes.add(nextFactor);
						nextFactor += j;
					}
				}
			}
			counter = i - setOfNonRelativePrimes.size();
			// System.out.println(i + " " + setOfNonRelativePrimes);
			setOfNonRelativePrimes.clear();
			mapOfRelativePrimes.put(i, counter);
		}
		*/
	}

	public static void main(String[] args) {
		TotientPermutation testX = new TotientPermutation();
		// for (Double i = 1.; i <= testX.limit; i++) {
		// 	System.out.println("Value: " + i + " --- " + testX.mapOfRelativePrimes.get(i));	
		// }
		// System.out.println(testX.listOfPrimes);

		double numberOfNonRelativePrimes = 1.;

		for (double i = 2; i <= testX.limit; i++) {
			numberOfNonRelativePrimes = 1.;
			innerloop:
			for (Double prime : testX.listOfPrimes) {
				if (prime < i) {
					if (i % prime != 0) {
						numberOfNonRelativePrimes++;
						System.out.println("i: " + i + " | prime: " + prime);
					}
				} else {
					break innerloop;
				}
			}
			if (!testX.setOfPrimes.contains(i - 1.)) {
				numberOfNonRelativePrimes++;
				System.out.println("x: " + i + " | prime: " + (i - 1));
			}
			System.out.println("n = " + i + " | # of relative primes = " + numberOfNonRelativePrimes);
		}

		System.out.println("Done");
		System.out.println(testX.setOfPrimes);
	}
}