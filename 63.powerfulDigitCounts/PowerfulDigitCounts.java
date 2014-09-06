/*
	The 5-digit number, 16807=7^5, is also a fifth power. 
	Similarly, the 9-digit number, 134217728=8^9, is a ninth power.

	How many n-digit positive integers exist which are also an 
	nth power?
	-----------------
	Having issues with precision with this technique:
		16807 ^ 1/5 % 1 = 8.881784197001252E-16

	This brute force way takes entirly too long. Going to try
	something else. 
		1. Start with a base and set it to a power
		2. Check the number of digits of the result
			- If less, increment the power
			- If equal, increment the answer and the power,
			- If greater than, increment the base and start
			  the power over
		3. Keep going until I stop getting resutls I guess.

	--------------------
	Helpful site reagrding my problems with precision
	http://stackoverflow.com/questions/19743975/java-math-cubic-root-loss-of-precision

	----------
	Solved it, see algorithm for notes.

*/

import java.text.DecimalFormat;
import java.lang.Math;
import java.math.BigInteger;

public class PowerfulDigitCounts {
	
	// Method that checks the number of digits, passed pre-formated double
	private static double numLength(String num) {
		// String sNum = Double.toString(num);
		// System.out.println(sNum);
		// Includes the ".0" as 2 characters, need to remove them
		double dNumLength = num.length();

		return dNumLength;
	}

	// Main method
	public static void main(String[] args) {

		double numberOfDigits = 1;
		double counter = 0;

		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(0);

		/*
			This attempt counted up using a for loop and then got
			the length of each number, and checked if the length
			root of the number was an integer. Due to floating
			point arithmetic, you don't get exact numbers. So 
			I round the number down, and subtract it. If the 
			remainder is really close to 0 or 1 (I choose
			arbitrarily until I didn't get duplicate numbers
			returned) then I count it. 

			It works, but takes awhile to get up to 8^9 
			example.
		*/

		// for (double x = 1.; x < 134217729.; x++) {
			
		// 	df.format(x);
		// 	numberOfDigits = numLength(df.format(x));
		// 	double result = Math.pow(x, 1. / numberOfDigits);
		// 	double roundedResult = Math.floor(result);

		// 	if (result - roundedResult < .00000000001 || result - roundedResult > .999999999) {
		// 		counter++;
		// 		System.out.println(Math.round(result) + "^" + numberOfDigits + " = " + df.format(x));
		// 	}
		// }
		// System.out.println(counter);

		/*
			Going to try another approach. The form we are using is a^b = c 
			I will increment b for a until length(c) is larger than b then
			go to the next a. Not sure where I'm going to stop yet.

			Sooooooo much faster :)
		*/

		for (double a = 2.; a < 10 ; a++) { // 10 is the limit here
			for (double b = 1; b < 30; b++) {
				
				double result = Math.pow(a, b);
				String formatedResult = df.format(result);
				double resultLength = numLength(formatedResult);

				if (resultLength > b) {
					break;
				} else if (resultLength == b) {
					counter++;
					System.out.println(a + "^" + b + " = " + df.format(result));
				} else {
					continue;
				}
			}
		}
		System.out.println(counter + 1); // + 1 for 1^1
	}
}