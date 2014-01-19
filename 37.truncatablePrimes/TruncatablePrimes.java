/*
	The number 3797 has an interesting property. Being prime itself, it 
	is possible to continuously remove digits from left to right, and 
	remain prime at each stage: 3797, 797, 97, and 7. Similarly we can 
	work from right to left: 3797, 379, 37, and 3.

	Find the sum of the only eleven primes that are both truncatable from 
	left to right and right to left.

	NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

	----------------
	Will generate a list of primes that could be truncatable under a certain
	limit, or I guess until we get to 11.

	Then I will test if it is truncatable from the left side and the right 
	side. Right now I have this set up as 2 separate methods, but this might
	be a good time to use the technique to avoid writing 2 almost duplicate 
	methods.

	Also, I tried to use Java generics again, but couldn't get it to work.
	Need to look into that some more.
*/

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class TruncatablePrimes {
	
	private final ArrayList<String> possiblePrimes = new ArrayList<String>();

	private TruncatablePrimes(int limit) {
		generatePrimes(limit, possiblePrimes);
	}
	/*
		Generater primes under a certain limit
	*/
	private void generatePrimes(int limit, ArrayList<String> possiblePrimes) {
		boolean isPrime = true;
		for (int i  = 2; i < limit; i++) {
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				possiblePrimes.add(Integer.toString(i));
			}
			isPrime = true;
		}
	}
	/*
		boolen is trunctable left
	*/
	private boolean isTruncatableL(String prime) {
		if (prime.equals("2") || prime.equals("3") || prime.equals("5") ||
			prime.equals("7")) {
			return false;
		}
		boolean isTruncatableL = true;
		int length = prime.length();
		// System.out.println(possiblePrimes);
		for (int i = 0; i < length; i++) {
			// System.out.println(prime.substring(i, length));
			if (!possiblePrimes.contains(prime.substring(i, length))) {
				isTruncatableL = false;
				// System.out.print(prime + " ");
				return isTruncatableL;
			}
		}
		for (int j = length - 1; j > 0; j--){
			if (!possiblePrimes.contains(prime.substring(0, j))) {
				isTruncatableL = false;
				return isTruncatableL;
			}
		}
		// System.out.print(prime + " ");
		return isTruncatableL;
	}
	/*
		boolean is trunctable right
	*/
	/*
		main method
	*/
	public static void main(String[] args) {
		TruncatablePrimes x = new TruncatablePrimes(739398);
		// System.out.println(x.possiblePrimes);
		// System.out.println(x.isTruncatableL("2"));
		int sum = 0;
		for (String prime : x.possiblePrimes) {
			if (x.isTruncatableL(prime)) {
				System.out.println(prime);
				sum += Integer.parseInt(prime);
			}
		}
		System.out.println(sum);
		// String two = "3967";
		// String sub = two.substring(0, 1);
		// System.out.println(sub);
		// System.out.println(x.possiblePrimes.contains(two.substring(0, 3)));
	}
}