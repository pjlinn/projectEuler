/*
	Write a recursive method called sumDigits to return the sum of 
	all of the digits in a given integer value.  Use a helper method 
	if necessary.

	Example tests:

	assertEquals(0, sumDigits(0));
	assertEquals(8, sumDigits(1331));  1 + 3 + 3 + 1 = 8
	assertEquals(16, sumDigits(14641)); 1 + 4 = 6 + 4 + 1 = 16
*/

public class SumDigits {

	public static double sumDigits(double num) {
		if (num == 0) {
			return 0;
		} else {
			return Math.floor(num / (Math.pow(10, Math.floor(Math.log10(num))))) + sumDigits(num % (Math.pow(10, Math.floor(Math.log10(num)))));
		}
	}

	public static void main(String[] args) {
		System.out.println(sumDigits(0));
		System.out.println(sumDigits(1331));
		System.out.println(sumDigits(14641));
	}
}