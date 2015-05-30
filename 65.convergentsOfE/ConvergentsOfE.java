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

	[2.0, 1.0, 2.0, 1.0, 1.0, 4.0, 1.0, 1.0, 6.0, 1.0, 1.0, 8.0, 1.0, 1.0, 10.0, 1.0, 1.0, 12.0, 1.0, 1.0, 14.0, 1.0, 1.0, 16.0, 1.0, 1.0, 18.0, 1.0, 1.0, 20.0, 1.0, 1.0, 22.0, 1.0, 1.0, 24.0, 1.0, 1.0, 26.0, 1.0, 1.0, 28.0, 1.0, 1.0, 30.0, 1.0, 1.0, 32.0, 1.0, 1.0, 34.0, 1.0, 1.0, 36.0, 1.0, 1.0, 38.0, 1.0, 1.0, 40.0, 1.0, 1.0, 42.0, 1.0, 1.0, 44.0, 1.0, 1.0, 46.0, 1.0, 1.0, 48.0, 1.0, 1.0, 50.0, 1.0, 1.0, 52.0, 1.0, 1.0, 54.0, 1.0, 1.0, 56.0, 1.0, 1.0, 58.0, 1.0, 1.0, 60.0, 1.0, 1.0, 62.0, 1.0, 1.0, 64.0, 1.0, 1.0, 66.0, 1.0]
	6.963524437876955E57

	------------------------------------

	Finished. Pretty simple alogrithm, but I ran into issues with precision 
	since the number got so large. Had to change everything from Integer to 
	Double then I tried long. Eventually just switched the BigInteger class.

	Might have been able to make it totally recursive, but for now I just 
	keep calling the function and swapping then numbers. Then I get the
	numerator and parse it digit by digit to get the sum.

*/

import java.util.ArrayList;
import java.math.BigInteger;

public class ConvergentsOfE {
	
	ArrayList<Long> sequenceOfE = new ArrayList<Long>();

	/*
		Constructor class to create the list of numbers. Pretty
		simple pattern.
	*/
	public ConvergentsOfE() {
		sequenceOfE.add(2L); sequenceOfE.add(1L); sequenceOfE.add(2L);
		sequenceOfE.add(1L); sequenceOfE.add(1L);

		Long next = 4L;
		Long counter = 5L;
		Long i = 3L;

		while(counter < 100) {
			if (i % 3 == 0) {
				sequenceOfE.add(next);
				next = next + 2;
				i = 1L;
			} else {
				sequenceOfE.add(1L);
				i++;
			}
			counter++;
		}
	}
	/*
		Function to work out the the fraction addition. Basically make the
		fractions have a common denominator then add. Flip the fraction
		and add it to the previous number in line. You flip in the order
		in the main while loop.
	*/
	public String nextDenominator(Long wholeNumber, BigInteger numerator, BigInteger denominator) {
		BigInteger bWholeNumber = new BigInteger(wholeNumber.toString());
		BigInteger result = new BigInteger(bWholeNumber.multiply(denominator).toString());

		result = result.add(numerator);

		return result.toString();
	}

	public static void main(String[] args) {
		ConvergentsOfE x = new ConvergentsOfE();
		ArrayList<Long> list = new ArrayList<Long>();

		list = x.sequenceOfE;
		Long sequenceSize = list.size() - 1L;
		Long previous = 0L;
		// Long numerator = 1L;
		Long denominator = list.get(sequenceSize.intValue());
		
		BigInteger bDenominator = new BigInteger(denominator.toString());
		BigInteger bNumerator = new BigInteger("1");

		String holder = "";

		/*
			Flip the numbers around and continue calling until you've
			reached the end. Then pull out that numerator.
		*/
		while(sequenceSize > 0) {
			previous = list.get(sequenceSize.intValue() - 1);
			String sResult = x.nextDenominator(previous, bNumerator, bDenominator);
			BigInteger result = new BigInteger(sResult);
			bNumerator = bDenominator;
			bDenominator = result;
			sequenceSize--;

			if (sequenceSize == 0) {
				holder = result.toString();

			}
		}

		Integer length = holder.length();
		Integer result = 0;

		/*
			Sum the numerator digit by digit.
		*/
		for (Integer i = 0; i < length; i++) {
			result += Integer.parseInt(holder.substring(i, i + 1));
		}

		System.out.println(result);

	}
}