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
	The first version could only find 3 / 4 fractions:
	16/64
	19/95
	49/98


	26/65 (!)
	My conjecture is that I can't check fractions that reduce to 1 / 2 or less.
	I need to go above denominator / 2. I need a method that will reduce a 
	fraction to it's lowest common terms. I think this should be done recursively? 

*/

import java.util.ArrayList;
import java.util.Collections;

public class DigitCancelingv2 {
	/*
		Method to return the factors of the denominator that is passsed to 
		it. Using the factors I can construct the possible fractions that
		are candidates for this problem.
	*/
	static public ArrayList<Integer> factors(int denominator) {
		ArrayList<Integer> numerators = new ArrayList<Integer>();

		for (int i = 10; i <= denominator - 1; i++) {
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
		Takes a fraction and cancels the like digits.
	*/
	public static String digitCanel(int numerator, int denominator) {
		int numeratorOnesDigit = numerator % 10;
		int denominatorOnesDigit = denominator % 10;
		int numeratorTensDigit = numerator / 10;
		int denominatorTensDigit = denominator / 10;

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
		Method to find the lowest common term between 2 numbers, which is then used
		to reduce the fraction.
	*/
	public static int lowestCommonTerm(int numerator, int denominator) {
		int lowestTerm = 0;

		ArrayList<Integer> numeratorFactors = new ArrayList<Integer>();
		ArrayList<Integer> denominatorFactors = new ArrayList<Integer>();

		for (int i = numerator; i >= 1; i--) {
			if (numerator % i == 0) {
				numeratorFactors.add(i);
			}
		}

		for (int j = denominator; j >= 1; j--) {
			if (denominator % j == 0) {
				denominatorFactors.add(j);
			}
		}

		for (int k = 0; k <= numeratorFactors.size(); k++) {
			if (denominatorFactors.contains(numeratorFactors.get(k))) {
				lowestTerm = numeratorFactors.get(k);
				return lowestTerm;
			}
		}

		return lowestTerm;
	}


	public static void main(String[] args) {
		

		for (int i = 10; i <= 99; i++) {
			ArrayList<Integer> x = new ArrayList<Integer>(factors(i));
			for (Integer factor : x) {
				if (lowestCommonTerm(i, factor) == 1) {
					continue;
				}
				int lowestTerm = lowestCommonTerm(i, factor);

				int reducedDenominator = i / lowestTerm;
				int reducedNumerator = factor / lowestTerm;

				String digitsCanceled = digitCanel(factor, i);
				String canceledNumerator = digitsCanceled.substring(0,1);
				String canceledDenominator = digitsCanceled.substring(2);

				int x1 = Integer.parseInt(canceledNumerator);
				int y1 = Integer.parseInt(canceledDenominator);				

				System.out.print(digitCanel(factor, i) + " ");
				System.out.print(reducedNumerator + "/" + reducedDenominator + " ");
				System.out.println(factor + "/" + i);

				// if (y1 == reducedDenominator) {
				// 	System.out.print(digitCanel(factor, i) + " ");
				// 	System.out.println(reducedNumerator + "/" + reducedDenominator);
				// }
			}
		}

		/*
		for (int i = 10; i < 100; i++) {
				if (factors(i).isEmpty()) {
					continue;
				} else {
					ArrayList<Integer> numerators = factors(i);
					for (Integer numerator : numerators) {
						int simplifiedNumerator = numerator / numerator;
						int simplifiedDenominator = i / numerator;

						String digitsCanceled = digitCanel(numerator,i);
						String canceledNumerator = digitsCanceled.substring(0,1);
						String canceledDenominator = digitsCanceled.substring(2);

						int x = Integer.parseInt(canceledNumerator);
						int y = Integer.parseInt(canceledDenominator);

						if (x == simplifiedNumerator &&
							y == simplifiedDenominator) {
							System.out.print(numerator + "/" + i + "" + ": " + 
								digitCanel(numerator, i) + "=>" +
								simplifiedNumerator + 
								"/" + simplifiedDenominator + "\n");	
						}
					}
					System.out.println(i + " " + factors(i));
					System.out.println();
				}
			}
		*/
	}
}