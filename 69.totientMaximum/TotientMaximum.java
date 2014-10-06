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
*/

import java.util.ArrayList;
import java.util.Collections;

public class TotientMaximum {


	public static void greateastCommonPositiveFactor (int m, int n) {
		ArrayList<Integer> nFactors = new ArrayList<Integer>();
		ArrayList<Integer> mFactors = new ArrayList<Integer>();
		for (int i  = 1; i <= Math.sqrt(n); i++ ) {
			if (n % i == 0) {
				int divisor = i;
				int result = n / i;
				nFactors.add(i); nFactors.add(result);
			}
		}

		for (int i = 1; i <= Math.sqrt(m); i++) {
			if (m % i == 0) {
				int divisor = i;
				int result = m / i;
				mFactors.add(i); mFactors.add(result);
			}
		}

		Collections.sort(nFactors);
		Collections.sort(mFactors);
		Collections.reverse(nFactors);
		Collections.reverse(mFactors);

		// System.out.println(nFactors);

		for (Integer factor : mFactors) {
			if (nFactors.contains(factor) && factor > 1) {
				System.out.println("greatest common divisor: " + factor);
				break;
			} else if (nFactors.contains(factor) && factor == 1) {
				System.out.println("greatest common divisor: " + factor);
			}
		}

		// System.out.println(nFactors + "\n" + mFactors);
	}

	public static void main(String[] args) {
		// System.out.println("Still got it");
		greateastCommonPositiveFactor(2,4);
	}
}