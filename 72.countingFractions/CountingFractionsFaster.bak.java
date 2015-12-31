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
		1. Create key-value pairs. Key is the prime factors,
		the value is the list of indicies for those prime factors.
		Somehow I track where I left off and for each number I check
		if I've been building a list. If so, continue you rather than
		starting from scratch. If not, start one and save.

	Implemented this below. Faster, but still slower than 1 minute.
	*-> 2. I still think something can be done knowing the prime
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

public class CountingFractions {

	/*
		Help funciton for the recursive algorithm I was trying to write
		to find the relative primes. Didn't work. Never actually use
		this.
	*/
	private static Integer helperFunction(Integer result, 
		ArrayList<Integer> remainingFactors, Integer totalAlreadyCounted){

		ArrayList<Integer> newRemainingFactors = new ArrayList<Integer>();

		if (remainingFactors.size() == 0) {
			return totalAlreadyCounted;
		} else {
			Integer lastIndex = remainingFactors.size() - 1;
			Integer alreadyCounted = 0;

			Integer nextResult = result / remainingFactors.get(lastIndex);

			if (totalAlreadyCounted == 0) {
				alreadyCounted = nextResult;
			} else {
				alreadyCounted = totalAlreadyCounted - nextResult; 
			}

			for (int i = 0; i < lastIndex; i++) {
				newRemainingFactors.add(remainingFactors.get(i));
			}

			System.out.println("(" + nextResult + ", " + newRemainingFactors + ", " + alreadyCounted + ")");
			return helperFunction(nextResult, newRemainingFactors, alreadyCounted);
		}
	}

	/*
		Recursive algorithm I wrote to try and figure out how to calculate
		relative primes quickly using prime factors. Never worked for all 
		cases such as when I had a bunch of prime factors.
	*/
	private static Integer numOfNonRelativePrimes(Integer denominator, 
		ArrayList<Integer> primeFactors, Integer eliminationCount){
		
		ArrayList<Integer> newPrimeFactors = new ArrayList<Integer>();
		ArrayList<Integer> helpPrimeFactors = new ArrayList<Integer>();

		Integer size = primeFactors.size();

		if (size == 1) {
			eliminationCount = eliminationCount + denominator / primeFactors.get(0) - 1;
			System.out.println("elimination count: " + eliminationCount);
			return eliminationCount;
		} else {
			Integer lastIndex = primeFactors.size() - 1;
			Integer nextIndex = lastIndex;
			Integer result = denominator / primeFactors.get(lastIndex) - 1;
			Integer resultHolder = result; 
			Integer tempResult = result; 
			Integer tempDivider = 0;
			Integer alreadyCounted = 0;
			Integer totalAlreadyCounted = 0;

			while(nextIndex > 0) {
				nextIndex--;

				for (int i = 0; i <= nextIndex; i++) {
					helpPrimeFactors.add(primeFactors.get(i));
				}				

				Integer returnedValue = helperFunction(resultHolder, helpPrimeFactors, 0);

				System.out.println("(" + resultHolder + ", " + helpPrimeFactors + ", " + 0 + ") = " + returnedValue);
				result = result - returnedValue;

				helpPrimeFactors.clear();
			}

			for (int i = 0; i < primeFactors.size() - 1; i++) {
				newPrimeFactors.add(primeFactors.get(i));
			}

			result = result + eliminationCount;						

			return numOfNonRelativePrimes(denominator, newPrimeFactors, result);
		}
	}

	/*
		Takes all possible values of a number and divides by each of the primes factors
		to test if it is a relative prime. Returns the sum of non-relative primes that
		is subtracted by the total possibilities leaving number of relative primes.
	*/
	private static Integer indexCount(Integer denominator, ArrayList<Integer> primeFactors) {
		HashSet<Integer> setOfIndicies = new HashSet<Integer>();

		Integer index = 0;

		for (Integer prime : primeFactors) {
			index = prime;

			while(index < denominator) {
				setOfIndicies.add(index);
				index += prime;
			}
		}
		return setOfIndicies.size();
	}

	private static Integer fasterIndexCount(Integer denominator, ArrayList<Integer> primeFactors,
		Integer count) {
		if (primeFactors.size() == 1) {
			return count += denominator / primeFactors.get(0) - 1;
		} else {
			Integer index = primeFactors.size() - 1; // 3
			Integer result = 0;

			ArrayList<Integer> newPrimeFactors = new ArrayList<Integer>();

			result = denominator / primeFactors.get(index) - 1; // 210 / 7 - 1 = 29

			for (Integer i = 0; i < index; i++ ) {
				newPrimeFactors.add(primeFactors.get(i));
			}

			count += result - indexCount(result, newPrimeFactors); // Returns non-unique non-relative primes

			return fasterIndexCount(denominator, newPrimeFactors, count);
		}
	}

	public static void main(String[] args) {
		
		HashSet<Integer> hashPrimes = new HashSet<Integer>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> primeFactor = new ArrayList<Integer>();
		// primes.add(1); 
		primes.add(2);
		hashPrimes.add(2);

		Integer limit = 100000;
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

		for (Integer denominator = limit; denominator > 1; denominator--) {
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
				// System.out.println(denominator + " " + primeFactor);
				// System.out.println(numOfNonRelativePrimes(denominator, primeFactor, 0));

				// count = count + (denominator - 1 - numOfNonRelativePrimes(denominator, primeFactor, 0));
				
				Integer nonPrimeValue = denominator - 1 - fasterIndexCount(denominator, primeFactor, 0);
				BigInteger anotherTempBigInt = new BigInteger(nonPrimeValue.toString());

				count = count.add(anotherTempBigInt);

				primeFactor.clear();
				
			}
		}
		System.out.println(count);
	}
}