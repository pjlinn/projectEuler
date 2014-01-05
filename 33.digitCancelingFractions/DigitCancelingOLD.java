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
		Method to return the factors of the denominator that is passsed to 
		it. Using the factors I can construct the possible fractions that
		are candidates for this problem.
	*/
	static public ArrayList<Integer> factors(int denominator) {
		ArrayList<Integer> numerators = new ArrayList<Integer>();

		for (int i = 10; i <= denominator / 2 ; i++) {
			if (denominator % i == 0) {
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
	public static void main(String[] args) {
		for (int i = 10; i < 100; i++) {
				if (factors(i).isEmpty()) {
					continue;
				} else {
					ArrayList<Integer> numerators = factors(i);
					for (Integer numerator : numerators) {
						int simplifiedNumerator = numerator / numerator;
						int simplifiedDenominator = i / numerator;
						System.out.print(numerator + "/" + i + "" + ": " + 
							digitCanel(numerator, i) + "=>" +
							simplifiedNumerator + 
							"/" + simplifiedDenominator + "\t");
					}
					// System.out.println(i + " " + factors(i));
					System.out.println();
				}
			}	
	}
}