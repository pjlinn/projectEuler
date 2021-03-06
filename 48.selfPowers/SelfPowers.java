/*
	The series, 11 + 22 + 33 + ... + 1010 = 10405071317.

	Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.

	-------
	Pretty straight forward. I use Big Integer to keep track and I mod
	each result to just keep the results cleaner.
*/

import java.lang.Math;
import java.math.BigInteger;

public class SelfPowers {
	public static void main(String[] args) {
		
		final BigInteger modulus = new BigInteger("10000000000");

		BigInteger result = new BigInteger("0");

		for (int i = 1; i < 1000; i++) {
			BigInteger x = new BigInteger(Integer.toString(i));
			result = result.add(x.pow(i).mod(modulus));
		}

		System.out.println(result.mod(modulus));
	}
}