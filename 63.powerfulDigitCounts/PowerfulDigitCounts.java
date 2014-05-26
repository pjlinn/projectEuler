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

*/

public class PowerfulDigitCounts {
	// Main method
	public static void main(String[] args) {

		double numberOfDigits = 1;
		double counter = 1;
		double answer = 0;

		for (double i = 1; i < 11; i++) {
			double limit = Math.pow(10, i);

			// System.out.println("while < " + limit);
			while (counter < limit) {
				double xRoot = Math.pow(counter, 1 / i);
				if (xRoot % 1 == 0 || xRoot % 1 < .0000000001) {
					answer++;
					System.out.println(counter);
				}
				counter++;
			}
			// counter--;
		}

		// System.out.println(Math.pow(16807, .2) % 1 );
		System.out.println(answer);
		
	}
}