/*
	The fraction 49/98 is a curious fraction, as an inexperienced mathematician in 
	attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is 
	correct, is obtained by cancelling the 9s.

	We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

	There are exactly four non-trivial examples of this type of fraction, 
	less than one in value, and containing two digits in the numerator and denominator.

	If the product of these four fractions is given in its lowest common terms, 
	find the value of the denominator.

	----------------------------
	16/64
	19/95
	49/98

*/

import java.util.ArrayList;

public class DigitCanceling {
	/*
		Method to return an array list of possible numerators for this problem.
		A possible numerator is one that shares at least one digit with the
		denominator and is 2 digits long.
	*/
	static public ArrayList<Integer> possibleNumerators(int denominator) {
		ArrayList<Integer> numerators = new ArrayList<Integer>();

		for (int i = 10; i <= denominator - 1 ; i++) {
			int numeratorOnesDigit = i % 10;
			int denominatorOnesDigit = denominator % 10;

			int numeratorTensDigit = i / 10;
			int denominatorTensDigit = denominator / 10;

			if (numeratorOnesDigit == denominatorOnesDigit || 
				numeratorTensDigit == denominatorTensDigit || 
				numeratorTensDigit == denominatorOnesDigit || 
				numeratorOnesDigit == denominatorTensDigit) {
				if (i % 10 == 0 || denominator % 10 == 0) {
					continue;
				}
				numerators.add(i);
			}
		}
		return numerators;
	}
	/*
		Takes a fraction and cancels the like digits. Returns a string "a/b"
	*/
	public static String digitCanel(int numerator, int denominator) {
		int numeratorOnesDigit 		= numerator % 10;
		int denominatorOnesDigit 	= denominator % 10;
		int numeratorTensDigit 		= numerator / 10;
		int denominatorTensDigit 	= denominator / 10;

		String cancelFraction = numerator + "/" + denominator;

		if (numeratorOnesDigit == denominatorOnesDigit) {
			cancelFraction = numeratorTensDigit + "/" + denominatorTensDigit;
			return cancelFraction;
		} else if (numeratorOnesDigit == denominatorTensDigit) {
			cancelFraction = numeratorTensDigit + "/" + denominatorOnesDigit;
			return cancelFraction;
		} else if (numeratorTensDigit == denominatorOnesDigit) {
			cancelFraction = numeratorOnesDigit + "/" + denominatorTensDigit;
			return cancelFraction;
		} else if (numeratorTensDigit == denominatorTensDigit) {
			cancelFraction = numeratorOnesDigit + "/" + denominatorOnesDigit;
			return cancelFraction;
		} else {
			return cancelFraction;
		}
	}
	/*
		Method to evaluate a fraction in String form.
		String fraction must only be singal digits.
	*/
	public static double evaluateFraction(String fraction) {
		double dResult = 0.0;

		String sNumerator = fraction.substring(0,1);
		String sDenominator = fraction.substring(2);

		double iNumerator = Integer.parseInt(sNumerator);
		double iDenominator = Integer.parseInt(sDenominator);

		dResult = iNumerator / iDenominator;

		return dResult;
	}
	/*
		Takes 2 ints and returns the result of dividing the numerator and denominator
	*/
	public static double evaluateOriginalFraction(int numerator, int denominator) {
		double dNumerator = numerator;
		double dDenominator = denominator;

		return dNumerator/dDenominator;
	}
	/*
		Main mehtod.
	*/
	public static void main(String[] args) {
		// Loop through the possible denominators	
		for (int denominator = 10; denominator < 100; denominator++) {
			// Find possible numerators
			ArrayList<Integer> numerators = new ArrayList<Integer>(possibleNumerators(denominator));
			/*
				1. Loop through each numerator
				2. Create a String of the starting fraction ab / bc
				3. Cancel like terms
				4. Evalute the canceled fraction
				5. Evalute the original fraction
				6. If they are equal print them out
			*/
			for (Integer numerator : numerators) {
				String startingFraction = numerator + "/" + denominator;
				String canceledFraction = digitCanel(numerator, denominator);

				double evalCanceledFraction = evaluateFraction(canceledFraction);
				double evalStartingFraction = evaluateOriginalFraction(numerator, denominator);

				if (evalCanceledFraction == evalStartingFraction) {
					System.out.print(numerator + "/" + denominator + " => " +
					digitCanel(numerator, denominator) + 
					"\n");	
				}
			}
		}
	}
}