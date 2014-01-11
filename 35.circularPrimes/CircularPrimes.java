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
	which wouldn't be prime when rotated. 
*/

import java.util.ArrayList;

public class CircularPrimes {
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
		Permutation method
		Returns an ArrayList of possible permutations
	*/
	static public ArrayList<Integer> permutationGenerator(ArrayList<Integer> digits) {
		ArrayList<Integer> permutations = new ArrayList<Integer>();
		int holder1, holder2;
		int size = digits.size();
		int counter = 0;
		int iterations = factorial(size);
		String concatenatedNum = "";

		if (size == 1) {
			permutations.add(digits.get(0));
			return permutations;
		}

		while(counter < iterations) {
			size = digits.size();
			while(size - 2 >= 0) {
				holder1 = digits.get(size - 1);
				holder2 = digits.get(size - 2);

				digits.set(size - 1, holder2);
				digits.set(size - 2, holder1);

				for (Integer digit : digits) {
					concatenatedNum = concatenatedNum + "" + digit;
				}

				permutations.add(Integer.parseInt(concatenatedNum));

				size--;
				counter++;
				concatenatedNum = "";
			}
		}
		return permutations;
	}
	/*
		Int length method
			Takes an int as an argument
			Returns an ArrayList with each digit of the int in its own
				index from 0 to n
			This ArrayList will be passed into the permutationGenerator
				to generate all the possible permutation of this int
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
		Factorial method

		Passed the length of the number to calculate the number of permutations
	*/
	static public int factorial(int length) {
		if (length == 0) {
			return 1;
		} else {
			return length * factorial(length - 1);
		}
	}
	/*
		Calculate all the primes below a certain threshold, which is 
		passed as an argument.
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
		it's a circular prime
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