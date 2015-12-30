/*
	Consider the fraction, n/d, where n and d are positive 
	integers. If n<d and HCF(n,d)=1, it is called a reduced 
	proper fraction.

	If we list the set of reduced proper fractions for 
	d ≤ 8 in ascending order of size, we get:

	1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 
	1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 
	7/8

	It can be seen that there are 3 fractions between 
	1/3 and 1/2.

	How many fractions lie between 1/3 and 1/2 in the 
	sorted set of reduced proper fractions for 
	d ≤ 12,000?

	-------------

	Need to check: 
		8 		-> 3
		10 		-> 4
		100 	-> 505
		1000 	-> 50695
		12000	-> 7295372

	Completed it. Doesn't scale at all, but it works.
	Manually check using prime factors and size.
*/

import java.util.HashSet;
import java.util.ArrayList;

public class CountingFracInRange {
	
	private static Integer primeRangeCheck(Integer prime) {
		
		Integer count = 0;

		for (Integer i = 1; i < prime; i++) {
			if (i * 2 < prime * 1 && i * 3 > prime * 1) {
				// System.out.println(i + "/" + prime);
				count++;
			} else if (i * 2 > prime * 1) {
				break;
			}
		}
		return count;
	}

	private static Integer nonPrimeRangeCheck(Integer denominator, ArrayList<Integer> primeFactors) {
		
		boolean check = true;
		Integer count = 0;

		for (Integer index = 1; index < denominator; index++) {
			for (Integer primeFactor: primeFactors) {
					if (index % primeFactor == 0) {
						check = false;
					}
				}
			if (check) {
				if (index * 3 > denominator * 1 && index * 2 < denominator * 1) {
					// System.out.println(index + "/" + denominator);
					count++;
				}
			}
			if (index * 2 > denominator * 1) {
				break;
			}
			check = true;
		}
		return count;
	}

	public static void main(String[] args) {

		HashSet<Integer> hashPrimes = new HashSet<Integer>();
		ArrayList<Integer> listPrimes = new ArrayList<Integer>();
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();

		hashPrimes.add(2); listPrimes.add(2);

		Integer limit = 12000;
		Integer count = 0;
		boolean prime = true;

		/*
			Prime building loop.
		*/
		for (Integer i = 3; i <= limit; i++) {			
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					prime = false;
					break;
				}
			}
			if (prime) {
				listPrimes.add(i); hashPrimes.add(i);
			}
			prime = true;
		}
		// System.out.println(listPrimes);
		/*
			Prime factor building loop
		*/
		for (Integer denominator = limit; denominator > 1; denominator--) {
			if (denominator % 1000 == 0) {
				System.out.println("denominator: " + denominator);
			}			
			if (hashPrimes.contains(denominator)) {
				count += primeRangeCheck(denominator);
			} else {
				for (Integer iPrime: listPrimes) {
					if (iPrime > denominator / 2) {
						break;
					} else {
						if (denominator % iPrime == 0) {
							primeFactors.add(iPrime);
						}
					}
				}
				count += nonPrimeRangeCheck(denominator, primeFactors);
				primeFactors.clear();
			}
		}
		System.out.println(count);
	}
}