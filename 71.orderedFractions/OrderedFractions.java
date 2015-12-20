/*
	Consider the fraction, n/d, where n and d are positive integers. 
	If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

	If we list the set of reduced proper fractions for d ≤ 8 in ascending 
	order of size, we get:

	1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 
	2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

	It can be seen that 2/5 is the fraction immediately to the left of 3/7.

	By listing the set of reduced proper fractions for d ≤ 1,000,000 in 
	ascending order of size, find the numerator of the fraction immediately 
	to the left of 3/7.

	----------------
	
	First checked all number where 7 * x - 3 * y = -1 to narrow the field
	of possible numerators and denominators less than 3/7. From here, I needed to find
	the greatest of the group, which ended up being the largest, but I basically
	checked each by multiplying the numerator and denominators to determine the greater
	of the two fractions. Again, it was the first -1 I hit less than a million, but
	I was still able to do it.
*/

import java.lang.Math;

public class OrderedFractions {
	
	public static void main(String[] args) {
		Double start = 1000000.;


		Double x = 0.;
		Double y = 0.;

		Double constNumerator = 3.0;
		Double constDenominaor = 7.0;

		Double holderY = start; // Get a starting value
		Double holderX = Math.floor(constNumerator * holderY / constDenominaor); // Get a starting value

		Double leftSum = 0.; // Used to determine the greater fraction
		Double rightSum = 0.;

		for (y = start; y > 1.; y--) {
			
			x = Math.floor(constNumerator * y / constDenominaor);
			Double result = constDenominaor * x - constNumerator * y;
			
			if (result == -1.) {
				leftSum = x * holderY; // New candidate
				rightSum = holderX * y; // Existing candidate
				
				if (leftSum > rightSum) {
					holderX = x;
					holderY = y;
					System.out.println(holderX + "/" + holderY);	
				}
			}
		}
	}
}