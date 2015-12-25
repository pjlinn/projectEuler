/*
	Consider the fraction, n/d, where n and d are 
	positive integers. If n<d and HCF(n,d)=1, it is called 
	a reduced proper fraction.

	If we list the set of reduced proper fractions for 
	d ≤ 8 in ascending order of size, we get:

	1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 
	1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

	It can be seen that there are 21 elements in this set.

	How many elements would be contained in the set of 
	reduced proper fractions for d ≤ 1,000,000?

	--------------

	Sum of 1,000,000 to 1 = 1,784,293,664
							  815,921,389

	--------------

	Looking for relative primes. Then sum them.

	 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29
*/

import java.util.HashSet;
import java.util.ArrayList;
import java.lang.Math;

public class CountingFractions {
	
	private static Integer numOfNonRelativePrimes(Integer denominator, 
		ArrayList<Integer> primeFactors, Integer eliminationCount){
		
		ArrayList<Integer> newPrimeFactors = new ArrayList<Integer>();

		Integer size = primeFactors.size();

		if (size == 1) {
			eliminationCount = eliminationCount + denominator / primeFactors.get(0) - 1;
			return eliminationCount;
		} else {
			Integer lastIndex = primeFactors.size() - 1;
			Integer nextIndex = lastIndex;
			Integer result = denominator / primeFactors.get(lastIndex) - 1; // 210 / 7 - 1 = 29
			Integer resultHolder = result; // 29
			Integer tempResult = result; // 29
			Integer tempDivider = 0;

			while(nextIndex > 0) {
				nextIndex--;
				tempDivider = primeFactors.get(nextIndex); // 5
				tempResult = resultHolder / tempDivider; // 29 / 5 = 5

				result = result - tempResult;
				System.out.println("Result: " + result);
			}

			for (int i = 0; i < primeFactors.size() - 1; i++) {
				newPrimeFactors.add(primeFactors.get(i));
			}

			result = result + eliminationCount;

			return numOfNonRelativePrimes(denominator, newPrimeFactors, result);
		}
	}

	public static void main(String[] args) {
		
		HashSet<Integer> hashPrimes = new HashSet<Integer>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> primeFactor = new ArrayList<Integer>();
		// primes.add(1); 
		primes.add(2);
		hashPrimes.add(2);

		Integer limit = 210;
		Integer count = 0;
		Integer eliminate = 0;

		Boolean prime = true;

		for (int i = 3; i <= limit; i++) {
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					prime = false;
					break;
				}
			}
			if (prime) {
				primes.add(i);
				hashPrimes.add(i);
			}
			prime = true;
		}

		System.out.println(primes.size());

		for (Integer denominator = limit; denominator >  limit - 1; denominator--) {
			if (denominator % 100000 == 0) {
				System.out.println("denominator: " + denominator);
			}
			// Check if the denominator is prime
			if (hashPrimes.contains(denominator)) {
				count = count + denominator - 1; // Will always be HCF
			} else {
				for (Integer iPrime: primes) {
					if (iPrime > denominator / 2) {
						break;
					} else {
						if (denominator % iPrime == 0) {
							primeFactor.add(iPrime);
						}
					}
				}
				System.out.println(denominator + " " + primeFactor);
				// System.out.println(numOfNonRelativePrimes(denominator, primeFactor, 0));

				count = count + (denominator - 1 - numOfNonRelativePrimes(denominator, primeFactor, 0));
				primeFactor.clear();
				
			}
		}

		System.out.println(count);


		// for (Integer denominator = limit; denominator > 1; denominator--) {
		// 	for (Integer numerator = 0; numerator < primes.size(); numerator++) {
		// 		Integer index = primes.get(numerator);
		// 		if (index == 1) {
		// 			count++;
		// 			// System.out.println(index + "/" + denominator);
		// 		} else if (index > denominator) {
		// 			break;
		// 		} else if (denominator % index == 0) {
		// 			continue;
		// 		} else if (denominator % index != 0) {
		// 			count++;
		// 			// System.out.println(index + "/" + denominator);
		// 		}
		// 	}
		// }

		// for (int i = 1000000; i > 1; i--) {
		// 	for (int j = i - 1; j > 0; j--) {
		// 		count = i / j;
		// 	}
		// 	System.out.println(i);
		// }

		// for (limit = 8.; limit < 1001; limit++) {
		// 	for (Double numerator = 2.; numerator < limit; numerator++) {
		// 		for (Double denominator = limit; denominator > numerator; denominator--) {
		// 			if (denominator % numerator != 0.) {
		// 				Double result = numerator / denominator;
		// 				values.add(result);
		// 				// System.out.println(result);
		// 				// count++;
		// 				// System.out.println(numerator + "/" + denominator);
		// 			}
		// 		}
		// 	}
		// 	System.out.println(values.size() + count);
		// 	count = limit;
		// }

		// System.out.println(count);
	}
}