/*
	All square roots are periodic when written as continued fractions and can be written in the form:

	
	It can be seen that the sequence is repeating. For conciseness, we 
	use the notation √23 = [4;(1,3,1,8)], to indicate that the block (1,3,1,8) 
	repeats indefinitely.

	The first ten continued fraction representations of (irrational) square 
	roots are:

	√2=[1;(2)], period=1
	√3=[1;(1,2)], period=2
	√5=[2;(4)], period=1
	√6=[2;(2,4)], period=2
	√7=[2;(1,1,1,4)], period=4
	√8=[2;(1,4)], period=2
	√10=[3;(6)], period=1
	√11=[3;(3,6)], period=2
	√12= [3;(2,6)], period=2
	√13=[3;(1,1,1,1,6)], period=5

	Exactly four continued fractions, for N ≤ 13, have an odd period.

	How many continued fractions for N ≤ 10000 have an odd period?

	Number: 2497 Period: 134171

*/

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.math.BigInteger;

public class OddPeriodSquareRoots {
	
	private static Integer continuedFraction(Integer n){
		List<Double> sqrtPeriod = new ArrayList<Double>();
		Set<Double> divisors = new HashSet<Double>();

		Double sqrtN = Math.sqrt(n);
		Double sqrtFloor = Math.floor(sqrtN);
		
		Double tempDivisor = sqrtN - sqrtFloor;
		Double tempRemainder = 0.0;
		Double periodDigit = 0.0;
		Double periodTotal = 0.0;
		Double roundedDivisor = 0.0;

		Integer counter = 0;
		BigInteger multiply = new BigInteger("100");

		while(!divisors.contains(roundedDivisor)){
			if (roundedDivisor != 0.0) {
				divisors.add(roundedDivisor);
			}
			
			periodTotal = 1.0 / tempDivisor; //  > 1
			// System.out.println("period total " + periodTotal);
			periodDigit = Math.floor(periodTotal); // = 1		
			// System.out.println("period digit " + periodDigit);
			sqrtPeriod.add(periodDigit);

			tempDivisor = periodTotal - periodDigit; // = .256547
			roundedDivisor = Math.floor(tempDivisor * multiply.doubleValue());
			// divisors.add(Math.floor(tempDivisor * 100000));

			// System.out.println("rounded divisor " + roundedDivisor);
			// System.out.println(divisors.contains(roundedDivisor));

			counter++;
		}

		// System.out.println(sqrtN);
		// System.out.println(sqrtFloor);
		// System.out.println(sqrtPeriod);
		// System.out.println(divisors.size());

		return divisors.size();
	}

	public static void main(String[] args) {
		
		int counter = 0;
		int limit = 10000;

		for (int i = 2; i <= limit; i++) {
			if (Math.sqrt(i) - Math.round(Math.sqrt(i)) == 0) {
				continue;
			} else {
				if (continuedFraction(i) % 2 != 0) {
					System.out.println("Number: " + i + " Period: " + continuedFraction(i));
					counter++;
				}
			}
		}
		System.out.println(counter);
	}
}