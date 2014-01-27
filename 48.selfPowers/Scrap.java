import java.lang.Math;
import java.math.BigInteger;

public class Scrap {
	public static void main(String[] args) {
		
		final BigInteger modulus = new BigInteger("10000000000");
		// System.out.println(x.pow(999).mod(modulus));

		BigInteger result = new BigInteger("0");

		for (int i = 1; i < 1000; i++) {
			BigInteger x = new BigInteger(Integer.toString(i));
			result = result.add(x.pow(i).mod(modulus));
		}

		System.out.println(result);
	}
}