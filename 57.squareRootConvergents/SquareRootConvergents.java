/*
	It is possible to show that the square root of two can be expressed 
	as an infinite continued fraction.

	√ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

	By expanding this for the first four iterations, we get:

	1 + 1/2 = 3/2 = 1.5
	1 + 1/(2 + 1/2) = 7/5 = 1.4
	1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
	1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

	The next three expansions are 99/70, 239/169, and 577/408, but the
	 eighth expansion, 1393/985, is the first example where the number 
	 of digits in the numerator exceeds the number of digits in the denominator.

	In the first one-thousand expansions, how many fractions contain a 
	numerator with more digits than denominator?

	-------

	Solved it via brute force. Noticed the pattern for the numerator and
	denominator and just coded that. The numbers got really big, so I used
	BigInteger. There were some other optimizations and patterns that I 
	read about that I could employ. One was using log10 to determine if
	the numerator was a digit longer than the denominator. Also, there
	is a pattern to when the numerator is a digit greater.
*/

import java.math.BigInteger;
import java.lang.Math;

public class SquareRootConvergents {
	public static void main(String[] args) {
		// double dNumerator = 1.0;
		// double dDenominator = 2.0;
		// double tempNumerator = 1.0;
		// double finalNumerator = 1.0;
		String sNumerator = "";
		String sDenominator = "";
		int numeratorLength = 0;
		int denominatorLength = 0;

		int counter = 2;
		int numOfFractions = 0;

		BigInteger dNumerator = new BigInteger("1");
		BigInteger dDenominator = new BigInteger("2");
		BigInteger tempNumerator = new BigInteger("1");
		BigInteger finalNumerator = new BigInteger("1");
		final BigInteger two = new BigInteger("2");

		// System.out.println(dNumerator + " / " + dDenominator);
		while (counter < 1001) {
			tempNumerator = dNumerator;
			dNumerator = dDenominator;
			dDenominator = dDenominator.multiply(two);
			dDenominator = dDenominator.add(tempNumerator);
			finalNumerator = dNumerator.add(dDenominator);

			System.out.println(counter + ": " + Math.log10(finalNumerator.doubleValue()) + " / " + Math.log10(dDenominator.doubleValue()));
			counter++;

			sNumerator = finalNumerator.toString();
			sDenominator = dDenominator.toString();

			numeratorLength = sNumerator.length();
			denominatorLength = sDenominator.length();
			
			// System.out.println(numeratorLength + " / " + denominatorLength);
			if (numeratorLength > denominatorLength) {
				numOfFractions++;
				System.out.println("X");
			} else {
				System.out.println("1");
			}



		}
		System.out.println(numOfFractions);
	}
}