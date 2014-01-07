/*
	145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

	Find the sum of all numbers which are equal to the sum of the 
	factorial of their digits.

	Note: as 1! = 1 and 2! = 2 are not sums they are not included.

	----------

	I didn't know the upper bound, so I just kept randomly 
	increasing it until I felt like I'd reached the limit.

	I could probably figure it out better using the values of
	each factorial, but this worked fine.
*/

import java.util.ArrayList;

public class DigitFactorials {
	/*
		Returns true if int passed is a digit factorial. False
		otherwise.

		Had to use doubles all over the place because the 
		Math.pow() kept throwing errors when I tried to use
		ints.
	*/
	public static boolean digitFactorialTest(double num) {

		double result = 0; // Sum of factorials
		double length = lengthTest(num) - 1.; // Starting length
		double divisor; // Starting divisor
		double remainder; // Number used in the next interation
		double digit; // This is the individual digit we take the factorial of
		double holder = num; // Used to make the comparison

		while(length >= 0) { 
			divisor 	= Math.pow(10.0, length); 
			remainder 	= num % divisor;
			digit 		= Math.floor(num / divisor);
			num 		= remainder;
			result 		= result + facatorial(digit);
			length--;
		}
		
		if (result == holder) {
			return true;			
		} else {
			return false;
		}
	}
	/*
		Method to determine the length of a random integer
	*/
	public static double lengthTest(double num) {
		double length = 1;
		double divisor = 1;
		double result = 0;

		boolean keepGoing = true;

		while(keepGoing) {
			
			result = num % divisor;

			if (result == num) {
				keepGoing = false;
			} else {
				divisor *= 10;
				length++;
			}
		}
		return length - 1;
	}
	/*
		Factorial method
	*/
	public static double facatorial(double num) {
		if (num == 0) {
			return 1;
		} else {
			return (num * (facatorial(num - 1)));
		}
	}
	/*
		Main method
	*/
	public static void main(String[] args) {
		for (double i = 10; i <= 100000; i += 1) {
			if (digitFactorialTest(i)) {
				System.out.println(i + " " + digitFactorialTest(i));
			}
		}
	}
}