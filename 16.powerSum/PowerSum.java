/*
	215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

	What is the sum of the digits of the number 21000?
*/
import java.lang.Math;
import java.math.BigInteger;

public class PowerSum {
	public static void main(String[] args) {
		/*
			Need to print the result in full, not scientific notation.
			This link says how: 
			http://stackoverflow.com/questions/16098046/how-to-print-double-value-without-scientific-notation-using-java
		*/
		// BigInteger result2 = BigInteger.valueOf(Math.pow(2, 1000));
		// long result2 = Math.pow(2, 1000);
		double result = Math.pow(2, 1000);
		String characters = Double.toString(result);
		// System.out.println(result.charAt(1));
		for (int i = 0; i < characters.length(); i++) {
			System.out.println(characters.charAt(i));	
		}
	}
}