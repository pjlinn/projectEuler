/*
	Starting with 1 and spiralling anticlockwise in the following way, a 
	square spiral with side length 7 is formed.

	37 36 35 34 33 32 31
	38 17 16 15 14 13 30
	39 18  5  4  3 12 29
	40 19  6  1  2 11 28
	41 20  7  8  9 10 27
	42 21 22 23 24 25 26
	43 44 45 46 47 48 49

	It is interesting to note that the odd squares lie along the bottom 
	right diagonal, but what is more interesting is that 8 out of the 13 
	numbers lying along both diagonals are prime; that is, a ratio of 
	8/13 ≈ 62%.

	If one complete new layer is wrapped around the spiral above, a square 
	spiral with side length 9 will be formed. If this process is continued,
	what is the side length of the square spiral for which the ratio of primes 
	along both diagonals first falls below 10%?

	---------------
	Solved it finally...I had the correct algorithm for the longest time
	but I wasn't using the write variable type. I had floats and ints, but
	I needed doubles as I wasn't generating enough primes. Stopped working
	around ~12%, kept getting row 5229...

	It's pretty fast though, checks minimal numbers
*/

import java.util.ArrayList;

public class SpiralPrimes {
	
	private static int numPrime(ArrayList<Double> diagonals) {
		boolean isPrime = true;
		int numPrime = 0;
		for (Double diagonal : diagonals) {
			if (diagonal == 1 || diagonal == 2) {
				isPrime = false;
			}

			for (double i = 2; i <= Math.sqrt(diagonal); i++) {
				if (diagonal % i == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
			 	numPrime++;	
			}
			isPrime = true;
		}
		return numPrime;
	}

	public static void main(String[] args) {
		double start = 1;
		float increase = 2;
		double squareLength = 1;
		float total = 1;
		float numPrime = 0;

		ArrayList<Double> diagonals = new ArrayList<Double>();

		boolean tenPercent = false;

		while(!tenPercent) {
			// iteratate 4 times to get the corners
			for (double i = 0; i < 4; i++) {
				start += increase;
				diagonals.add(start);
			}

			total = total + 4;
			numPrime += numPrime(diagonals);
			squareLength += 2;

			if (numPrime / total < .10) {
				tenPercent = true;
				System.out.println(squareLength + " " + numPrime + "/" + total + " = " + numPrime / total);
			}
			increase += 2;
			diagonals.clear();
		}
	}
}