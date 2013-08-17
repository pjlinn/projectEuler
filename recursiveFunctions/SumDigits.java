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

	public static int sumDigits(int num) {
		if (num == 0) {
			return 0;
		} else {
			/*
				I want to sum how pull the last, or first, digit off
				of the number, and then call the sumDigits function
				again, this time with the digit pulled off
			*/
			return num + sumDigits(num % 2);
		}
	}

	public static void main(String[] args) {
		System.out.println(sumDigits(0));
		System.out.println(sumDigits(1331));
		System.out.println(sumDigits(14641));
	}
}