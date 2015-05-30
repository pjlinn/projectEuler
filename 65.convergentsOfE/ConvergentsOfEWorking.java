/*
	The square root of 2 can be written as an infinite continued fraction.
	
	...

	The infinite continued fraction can be written, √2 = [1;(2)], (2) 
	indicates that 2 repeats ad infinitum. In a similar way, 
	√23 = [4;(1,3,1,8)].

	It turns out that the sequence of partial values of continued 
	fractions for square roots provide the best rational approximations. 
	Let us consider the convergents for √2.

	...
	 
	Hence the sequence of the first ten convergents for √2 are:

	1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...
	What is most surprising is that the important mathematical constant,
	e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].

	The first ten terms in the sequence of convergents for e are:

	2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
	The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.

	Find the sum of digits in the numerator of the 100th convergent of 
	the continued fraction for e.
*/

import java.util.ArrayList;
import java.math.BigInteger;

public class ConvergentsOfEWorking {
	
	ArrayList<Double> sequenceOfE = new ArrayList<Double>();

	public ConvergentsOfEWorking() {
		sequenceOfE.add(2.); sequenceOfE.add(1.); sequenceOfE.add(2.);
		sequenceOfE.add(1.); sequenceOfE.add(1.);

		Double next = 4.;
		Double counter = 5.;
		Double i = 3.;

		while(counter < 100.) {
			if (i % 3. == 0.) {
				sequenceOfE.add(next);
				next = next + 2.;
				i = 1.;
			} else {
				sequenceOfE.add(1.);
				i++;
			}
			counter++;
		}
		// System.out.println(sequenceOfE);
	}

	public Double nextDenominator(Double wholeNumber, Double numerator, Double denominator) {
		Double result = wholeNumber * denominator;
		result = result + numerator;
		return result;
	}

	public static void main(String[] args) {
		ConvergentsOfEWorking x = new ConvergentsOfEWorking();
		ArrayList<Double> list = new ArrayList<Double>();

		list = x.sequenceOfE;
		Double sequenceSize = list.size() - 1.;
		Double result = 0.;
		Double previous = 0.;
		Double numerator = 1.;
		Double denominator = list.get(sequenceSize.intValue());
		


		// Double result = x.sequenceOfE(sequenceSize - 2) + 1 / x.sequenceOfE(sequenceSize - 1);

		while(sequenceSize > 0.) {
			previous = list.get(sequenceSize.intValue() - 1);
			
			// System.out.println("(" + previous + ", " + numerator + ", " + denominator + ")");

			result = x.nextDenominator(previous, numerator, denominator);
			numerator = denominator;
			denominator = result;
			sequenceSize--;

			// System.out.println(result);
		}

		System.out.println(result);
	}
}