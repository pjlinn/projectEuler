/*
	Euler discovered the remarkable quadratic formula:

	n² + n + 41

	It turns out that the formula will produce 40 primes for the consecutive 
	values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 
	is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly 
	divisible by 41.

	The incredible formula  n² − 79n + 1601 was discovered, which produces 
	80 primes for the consecutive values n = 0 to 79. The product of the 
	coefficients, −79 and 1601, is −126479.

	Considering quadratics of the form:

	n² + an + b, where |a| < 1000 and |b| < 1000

	where |n| is the modulus/absolute value of n
	e.g. |11| = 11 and |−4| = 4
	Find the product of the coefficients, a and b, for the quadratic 
	expression that produces the maximum number of primes for consecutive 
	values of n, starting with n = 0.

	---------------------------------------------

	Key things to observe:

	Coefficients abs(a) and abs(b) have to be less than 1000
	Coefficients a and b have to be prime
		=> Thus I generate a list of primes < 1000 as possible iterators
	n is less than 79 since this is the given upper limit

	Brute force formulation of creating every permutation of n^2 + an + b
	and then going from n = 0 to n = 79 testing the consecutive prime and 
	returning the values that produced the max.
*/

import java.util.ArrayList;
import java.lang.Math.*;

public class QuadraticPrimes {
	
	public static ArrayList<Integer> primes(int num) {

		ArrayList<Integer> listOfPrimes = new ArrayList<Integer>();
		listOfPrimes.add(1);
		listOfPrimes.add(2);
		listOfPrimes.add(3);
		listOfPrimes.add(5);
		listOfPrimes.add(7);

		boolean isPrime = false;

		for (int i = 5; i < num; i = i + 2) {
			for (int j = 3; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				} else {
					isPrime = true;
				}
			}
			if (isPrime) {
				listOfPrimes.add(i);
			}
			isPrime = false;
		}

		return listOfPrimes;
	}

	public static void main(String[] args) {
		
		int sum = 0;
		int product = 0;
		int counter = 0;
		int max = 0;

		// list of prime numbers under 1000, which are the possible value for b
		ArrayList<Integer> listOfPrimes1000 = new ArrayList<Integer>(primes(1000));
		// list of primes under 10000, to check results against
		ArrayList<Integer> listOfPrimes10000 = new ArrayList<Integer>(primes(10000));

		/*
			Checks for most consecutive primes using only positive coefficient a and b
			~ returns Euler's equation:
			n^2 + n + 41

			Same exact implmentation as above, except this time the equation uses negative
			values for a and postive for b. I didn't need to try other combinations, this
			generated the correct quadratic formula to answer the problem:
			
			n^2 - 61*n + 971

			-61 * 971 = -59231

			***Instead of doubling up instances, maybe need to create a method or,

			Add another wrapper for loop of the implementation first as the value -1 that will be
			multiplied against a, and then as +1
		*/

		for (int z = 1; z > -2; z = z - 2) {
			// coefficient b
			for (int i = 0; i < listOfPrimes1000.size(); i++) {
				// coefficient a
				for (int j = 0; j < listOfPrimes1000.size(); j++) {
					// consecutive values of n
					for (int k = 0; k < 79; k++) {
						product = k * k + k * listOfPrimes1000.get(j) * z + listOfPrimes1000.get(i); 

						if (listOfPrimes10000.contains(product)) {
							counter++;
						} else {
							if (counter > max) {
								max = counter;
								System.out.println("Coefficient b: " + listOfPrimes1000.get(i) +
									" Coefficient a: " + listOfPrimes1000.get(j) + " * (" + z + ") " +
									" Max: " + max);
							}

							max = (counter > max) ? counter : max;
							counter = 0;
							break;
						}
					}
				}
			}
		}
	}
}