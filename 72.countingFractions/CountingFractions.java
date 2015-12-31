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

	10,000 		-         30,397,485
	100,000 	- 	   3,039,650,753
	1,000,000 	-    303,963,552,391

	--------------

	Worked on this for a long, long time. I realized
	early on that what I wanted was the Euler Totient
	for each number 1 to 1 million. This was similar to
	problem 69. Problem was, I didn't have a fast
	Phi function and had trouble creating one.

	Tried to recursively solve for unique numbers 
	given a set of prime factors. This either couldn't be
	done or got me too confused.

	I eventually just counted up to a number i using each
	of its prime factors and only keeping the unique ones.
	Then I subtracted the size of unique indicies from the
	total possible. Except for primes which were i - 1. 

	I thought of a couple other ways that could be faster

		*Tried implementing this. Not sure if it's working correctly, 
		but if it is it isn't faster. Actually slower....
		
		->>>1. Create key-value pairs. Key is the prime factors,
		the value is the list of indicies for those prime factors.
		Somehow I track where I left off and for each number I check
		if I've been building a list. If so, continue you rather than
		starting from scratch. If not, start one and save.

		2. I still think something can be done knowing the prime
		factors. For 210 [2, 3, 5, 7], maybe try dividing by 7 (30)
		subtract by 1. Then check if indicies 1, 2, 3...29 are divisible
		by any of the remaining prime factors. If so, remove. Count the 
		remainder and attach it to that number. Do this for the rest and
		sum them. Subtract from max possible (i - 1).
*/

import java.util.HashSet;
import java.util.ArrayList;
import java.lang.Math;
import java.math.BigInteger;
import java.util.HashMap;

public class CountingFractions {

	HashMap<ArrayList<Integer>, HashSet<Integer>> indexMap = new HashMap<ArrayList<Integer>, HashSet<Integer>>();

	private Integer indexCount(Integer denominator, ArrayList<Integer> primeFactors) {
		HashSet<Integer> setOfIndicies = new HashSet<Integer>();

		Integer start = 0;
		Integer index = 0;

		if (indexMap.containsKey(primeFactors)) {
			setOfIndicies = indexMap.get(primeFactors);
			System.out.println(primeFactors + " :" + setOfIndicies);

			for (Integer x : setOfIndicies) {
				if (x > start) {
					start = x;
				}
			}

			for (Integer prime : primeFactors) {
				index = start;

				while(index < denominator) {
					setOfIndicies.add(index);
					index += prime;
				}
			}

		} else {
			for (Integer prime : primeFactors) {
				index = prime;

				while(index < denominator) {
					setOfIndicies.add(index);
					index += prime;
				}
			}
		}
		indexMap.put(primeFactors, setOfIndicies);
		return setOfIndicies.size();
	}

	public static void main(String[] args) {
		
		CountingFractions x = new CountingFractions();

		HashSet<Integer> hashPrimes = new HashSet<Integer>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> primeFactor = new ArrayList<Integer>();

		primes.add(2);
		hashPrimes.add(2);

		Integer limit = 100;
		BigInteger count = new BigInteger("0");

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

		for (Integer denominator = 2; denominator < limit; denominator++) {
			if (denominator % 1000 == 0) {
				System.out.println("denominator: " + denominator);
			}
			// Check if the denominator is prime
			if (hashPrimes.contains(denominator)) {
				Integer primeValue = denominator - 1;
				BigInteger tempBigInt = new BigInteger(primeValue.toString());
				count = count.add(tempBigInt); // Will always be HCF
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
				
				Integer nonPrimeValue = denominator - 1 - x.indexCount(denominator, primeFactor);
				BigInteger anotherTempBigInt = new BigInteger(nonPrimeValue.toString());

				count = count.add(anotherTempBigInt);

				primeFactor.clear();			
			}
		}
		System.out.println(count);
	}
}