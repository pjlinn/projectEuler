/*
	n! means n × (n − 1) × ... × 3 × 2 × 1

	For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
	and the sum of the digits in the number 10! is 3 + 6 + 
	2 + 8 + 8 + 0 + 0 = 27.

	Find the sum of the digits in the number 100!

	**** Make the factorial function recursive ****
*/

import java.math.BigInteger;

public class FactorialDigitSum {

	public static int recursiveFactorial(int num) {
		if (num == 1) {
			return 1;
		} else {
			return (num * recursiveFactorial(num - 1));
		}
	}

	public static int stringSum(String num){
		int length = num.length();
		int sum = 0;

		for (int i = 0; i < length; i++) {
			sum += num.charAt(i)-48; 
		}

		return sum;
	}

	public static BigInteger factorial(int num) {
		
		BigInteger factorial = new BigInteger("1");

		// long factorial = 1L;

		for (int i = num; i > 1; i--) {
			BigInteger holder = new BigInteger(Integer.toString(i));
			factorial = factorial.multiply(holder);
		}
		return factorial;
	}

	public static void main(String[] args) {
		
		String num = factorial(100).toString();

		System.out.println(num);

		System.out.println(stringSum(num));

		System.out.println("Recursive: " + recursiveFactorial(10));

	}

}