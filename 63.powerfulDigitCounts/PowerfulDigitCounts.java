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

*/

import java.text.DecimalFormat;
import java.lang.Math;

public class PowerfulDigitCounts {
	
	// Method that checks the number of digits, passed pre-formated double
	private static double numLength(String num) {
		// String sNum = Double.toString(num);
		// System.out.println(sNum);
		// Includes the ".0" as 2 characters, need to remove them
		double dNumLength = num.length() - 2; // -2 for decimal point and trailing zero

		return dNumLength;
	}

	// Main method
	public static void main(String[] args) {

		double numberOfDigits = 1;
		double counter = 1;
		double answer = 0;

		double testingX = 134217728;

		double testingLength = numLength(Double.toString(testingX));

		// answer = Math.exp(Math.log(testingX) / 5);
		// answer = Math.pow(testingX, 1 / testingLength);
		answer = Math.pow(9, 1./2);


		// System.out.println(answer);

		/*
			One way to solve 16807 = x^5 is to take the fifth root of 16807 
			using Math.pow(16807, 1/5). But the precision isn't perfect.
			~7.000000000000001
		*/

		for (double x = 134217728.; x < 134217729.; x++) {
			
			numberOfDigits = numLength(Double.toString(x));
			double result = Math.pow(x, 1. / numberOfDigits);
			double roundedResult = Math.floor(result);

			// System.out.println("result: " + result "\n" + "roundedResult: " + roundedResult);
			System.out.println(x);
			System.out.println(numberOfDigits);
			System.out.println(result);
			System.out.println(roundedResult);
			System.out.println(result - roundedResult);

			if (result - roundedResult == 8.881784197001252E-16) {
				System.out.println("works");
			}
		}
		// for (double i = 1; i < 11; i++) {
		// 	double limit = Math.pow(10, i);

		// 	// System.out.println("while < " + limit);
		// 	while (counter < limit) {
		// 		double xRoot = Math.pow(counter, 1 / i);
		// 		if (xRoot % 1 == 0 || xRoot % 1 < .0000000001) {
		// 			answer++;
		// 			System.out.println(counter);
		// 		}
		// 		counter++;
		// 	}
		// 	// counter--;
		// }

		// System.out.println(Math.pow(16807, .2) % 1 );

		// double test = 123456789123456.;
		// DecimalFormat df = new DecimalFormat("#");
		// df.setMaximumFractionDigits(0);
		// System.out.println(numLength(df.format(test)));
		// System.out.println(df.format(test));
		
	}
}