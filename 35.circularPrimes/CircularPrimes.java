/*
	The number, 197, is called a circular prime because all rotations 
	of the digits: 197, 971, and 719, are themselves prime.

	There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 
	37, 71, 73, 79, and 97.

	How many circular primes are there below one million?

	---------
	Misunderstood the spec for a second, and thought every rotation
	meant every permutation. That's not what it meant, so the actual
	answer was 55 and not 22. 

	Sped up the calculation by limiting the list of primes to only 
	including primes that didn't have the digits 2,4,5,6,8, and 0 
	which couldn't be prime when rotated. 
*/

import java.util.ArrayList;

public class CircularPrimes {
	// Possible circular primes
	private ArrayList<Integer> primeNumbers = new ArrayList<Integer>();

	public CircularPrimes() {
		primeNumbers = calculatePrimes(1000000);
	}
	/*
		Rotates the int that is passed to it to create a rotation,
		which is added to the ArrayList it returns. Each of these
		rotations will be checked to see if they are prime.
	*/
	public static ArrayList<Integer> rotate(int num) {
		ArrayList<Integer> number = new ArrayList<Integer>(intLength(num));
		ArrayList<Integer> rotations = new ArrayList<Integer>();
		String rotation = "";
		int start, end;
		int counter = 0;

		while (!rotation.equals(Integer.toString(num))) {
			rotation = "";
			start = number.get(0);
			end = number.size();
			for (int i = 0; i < end - 1; i++) {
				number.set(i, number.get(i + 1));
			}
			number.set(end - 1, start);
			for (Integer digit : number) {
				rotation += digit + "";
			}
			rotations.add(Integer.parseInt(rotation));
			counter++; 
		}
		return rotations;
	}	
	/*
		Int length method
			Takes an int as an argument
			Returns an ArrayList with each digit of the int in its own
				index from 0 to n
			This ArrayList will be passed into the permutationGenerator
				to generate all the possible permutation of this int

		Moving an integer into an Arraylist with each index being a
		digit makes working the number much easier for me, however
		I'm sure there is a better way to do it.

	*/
	static public ArrayList<Integer> intLength(int num) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int divisor = 1;
		int digit;
		int newNum = num;
		// Determines starting divisor for the passed int
		while(num % divisor != num) {
			divisor = divisor * 10;
		}
		divisor = divisor / 10;
		// A quick loop to break off each digit and add it to the ArrayList
		while(divisor >= 1) {
			newNum = newNum % divisor;
			digit = num / divisor;
			digits.add(digit);
			divisor = divisor / 10;
			num = newNum;
		}
		return digits;
	}
	/*
		Calculate all the primes below a certain threshold, which is 
		passed as an argument.

		Automatically adds 2, 3, and 5 - then calles the intLength
		method to generate the prime in it's on ArrayList. If the
		prime has a 0, 2, 4, 6, or 8 it is excluded from the list
		of possible primes as those digits wouldn't not be prime
		when rotated to the end.
	*/
	public ArrayList<Integer> calculatePrimes(int limit) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i < limit; i++) {
			boolean isPrime = true;
			if (i == 2 || i == 3 || i == 5) {
				primes.add(i);
				continue;
			}
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				ArrayList<Integer> digits = new ArrayList<Integer>(intLength(i));
				if (!digits.contains(2) && !digits. contains(4) && !digits.contains(6) &&
					!digits.contains(8) && !digits.contains(0) && !digits.contains(5)) {
					primes.add(i);
				}
				// System.out.println(digits);
			}
		}
		// System.out.println(primes);
		return primes;
	}
	/*
		Rotates digits of the passed number and checks to see if
		it's a circular prime.
	*/
	public boolean circularPrimeCheck(int prime) {
		boolean isCircularPrime = true;

		// ArrayList<Integer> permutations = new ArrayList<Integer>();
		// permutations = permutationGenerator(intLength(prime));
		ArrayList<Integer> rotations = new ArrayList<Integer>();
		rotations = rotate(prime);

		for (Integer rotation : rotations) {
			if (!primeNumbers.contains(rotation)) {
				isCircularPrime = false;
				// System.out.print(permutation +", ");
				return false;
			}
		}
		return isCircularPrime;	
	}
	/*
		Main method
	*/
	public static void main(String[] args) {
		CircularPrimes x = new CircularPrimes();
		int count = 0;
		for (Integer prime : x.primeNumbers) {
			if (x.circularPrimeCheck(prime)) {
				System.out.print(prime + ", ");
				count++;
			}
		}
		System.out.println("\n" + count);
	}
}