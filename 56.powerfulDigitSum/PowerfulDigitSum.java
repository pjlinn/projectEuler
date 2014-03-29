/*
	A googol (10100) is a massive number: one followed by one-hundred 
	zeros; 100100 is almost unimaginably large: one followed by 
	wo-hundred zeros. Despite their size, the sum of the digits in 
	each number is only 1.

	Considering natural numbers of the form, ab, where a, b < 100, 
	what is the maximum digital sum?

	------
	Brute force. Seems like the only optimizations would be to
	adjust the constraints;
*/

import java.math.BigInteger;

public class PowerfulDigitSum {
	
	private static BigInteger digitSum(BigInteger num) {
		BigInteger base = new BigInteger("10");
		BigInteger zero = new BigInteger("0");
		BigInteger digit = new BigInteger("0");
		BigInteger sum = new BigInteger("0");

		while(!num.equals(zero)) {
			digit = num.mod(base);
			num = num.divide(base);
			sum = sum.add(digit);
		}
		return sum;
	}

	public static void main(String[] args) {
		BigInteger max = new BigInteger("0");
		BigInteger digitSum = new BigInteger("0");
		BigInteger result = new BigInteger("10");
		
		// System.out.println(digitSum(max));

		for (int a = 2; a < 100; a++) {
			BigInteger base = new BigInteger(Integer.toString(a));
			for (int b = 1; b < 100; b++) {
				BigInteger exponent = new BigInteger(Integer.toString(b));
				result = base.pow(b);
				digitSum = digitSum(result);
				max = max.max(digitSum);
			}
		}

		// System.out.println(max.max(result));
		System.out.println(max);

	// 	BigInteger x = new BigInteger("10");
	// 	x = x.pow(100);
	// 	System.out.println(x.pow());
	}
}