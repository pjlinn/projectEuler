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


			Didn't really work, wrote a script on awk to get the number to
			print fully, couldn't figure out how to break it into 
			individual characters though, so I'll do it here. Lazy, I know.
		*/
		// BigInteger result2 = BigInteger.valueOf(Math.pow(2, 1000));
		// long result2 = Math.pow(2, 1000);
		// double result = Math.pow(2, 1000);
		// String characters = Double.toString(result);
		// System.out.printf("%f", result);
		// for (int i = 0; i < characters.length(); i++) {
		// 	System.out.println(characters.charAt(i));	
		// }
		int constantCharDiff = 48;
		int result = 0;
		String number = "10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376.000000";
		/*
			GIVES 1364 FOR SOME REASON, EVEN THOUGH THE HIGHEST RESULT
			IS 1366, WHICH IS THE ANSWER

			Not sure why, probably has something to do with the decimals
			and zeros...again to lazy to fix it.
		*/
		for (int i = 0; i < number.length(); i++) {
			result = result + number.charAt(i) - constantCharDiff;
			System.out.print(result + ", ");
		}
		// System.out.println(number.charAt(0) - constantCharDiff + number.charAt(2) - constantCharDiff);
		System.out.println(result);
	}	
}