/*
	The cube, 41063625 (3453), can be permuted to produce two other cubes: 
	56623104 (3843) and 66430125 (4053). In fact, 41063625 is the smallest 
	cube which has exactly three permutations of its digits which are also 
	cube.

	Find the smallest cube for which exactly five permutations of its digits 
	are cube.

	-----------------

	Solved it, but very hacky solution. I basically just generate a large
	HashSet of cubes that I increased using trial and error. Then I iterate
	through the HashSet and determine whether or not there are other cubes
	of the same length and digit sum, if the number passes that test then 
	I kind of check if it's a permutation by cubing the sum. Like I said this
	is not good. It just spits out numbers, 5 are not permutations, but the
	other 5 are so I was able to get it.

	Shouldn't bother with the digit sum and the permutation checker, should
	just combine that step. I should also avoid iterating through everything
	and re-calculating how long a number is. I should instead create maps
	for a cube's length and digitsum and whatever else and just lookup 
	those values.
*/

import java.util.HashSet;
import java.util.HashMap;

public class CubicPermutations {
	
	// Generates a HashSet of cubes up to a given limit
	private static HashSet<Double> cubeGenerator(double limit) {

		double resultingCube = 0;
		double n = 1;

		HashSet<Double> cubeSet = new HashSet<Double>();

		while(n <= limit) {
			resultingCube = Math.pow(n, 3);
			cubeSet.add(resultingCube);
			n++;
			// System.out.printf("%.0f\n", resultingCube);
		}

		return cubeSet;
	}

	// Sums the digits of a number. Use this to limit what cubes are even possible
	private static double digitSum(double num) {
		double result = 0;
		double sum = 0;

		while(num > 0) {
			result = num % 10;
			sum += result;
			num = Math.floor(num / 10);
		}
		return sum;
	}

	// Sums the number of digits in a number. Use this to limit what cubes are even possible
	private static double numberLength(double num) {
		double result = 0;
		double sum = 0;

		while(num > 0) {
			result = num % 10;
			sum += 1;
			num = Math.floor(num / 10);
		}
		return sum;
	}
	
	// Permutation checking method. Keeps track of the digits and frequency. 
	// If the maps are the same then it's a permutation
	// Actually that wasn't going to work, so I just used pow 6, to try and make
	// it less likely to capture non permutations.
	private static boolean permutationChecker(double num, double candidate) {

		HashMap<Double, Double> numMap = new HashMap<Double, Double>();
		HashMap<Double, Double> candidateMap = new HashMap<Double, Double>();
		double result = 0;
		double sum1 = 0;
		double sum2 = 0;
		double currentValue = 0;

		while(num > 0) {
			result = num % 10;
			num = Math.floor(num / 10);
			sum1 += Math.pow(result, 5);
			
			// if (numMap.containsKey(result)) {
			// 	currentValue = numMap.get(result);
			// 	numMap.put(result, currentValue + 1.);
			// } else {
			// 	numMap.put(result, 1.);
			// }
		}

		while(candidate > 0) {
			result = candidate % 10;
			candidate = Math.floor(candidate / 10);
			sum2 += Math.pow(result, 5);
			
			// if (candidateMap.containsKey(result)) {
			// 	currentValue = candidateMap.get(result);
			// 	candidateMap.put(result, currentValue + 1.);
			// } else {
			// 	candidateMap.put(result, 1.);
			// }
		}	

		if (sum1 == sum2) {
			return true;		
		}
		return false;
	}


	// Checks to see how many, if any, of the cubes in the list are the same length and sum
	private static boolean cubeTester(double cube, HashSet<Double> cubes) {

		double digitSum = digitSum(cube);
		double numberLength = numberLength(cube);
		double result = 0;

		for (Double x : cubes) {
			double xDigitSum = digitSum(x);
			double xNumberLength = numberLength(x);

			// if (cube == x) {
			// 	continue;
			// }

			if (digitSum == xDigitSum && numberLength == xNumberLength) {
				if (permutationChecker(cube, x)) {
					result++;
					// System.out.printf("%.0f\n", x);
				}	
			}
		}

		if (result == 5) {
			return true;
		} else {
			return false;
		}

		// return result;
	}

	// main method
	public static void main(String[] args) {
		
		final double limit = 10000;

		HashSet<Double> cubeSet = new HashSet<Double>(cubeGenerator(limit));
		HashMap<Double, Double> cubeSums = new HashMap<Double, Double>();

		for (Double cube : cubeSet) {
			// double result = digitSum(cube);
			// if (cubeSums.containsKey(result)) {
			// 	double currentValue = cubeSums.get(result);
			// 	cubeSums.put(result, currentValue + 1.);
			// } else {
			// 	cubeSums.put(result, 1.);
			// }
			if (cubeTester(cube, cubeSet)) {
				System.out.printf("%.0f\n", cube);
				// System.out.println(cube);
			}
		}

		// double x = Math.pow(384, 3);
		// double sum = 0;

		// while(x > 0) {
		// 	double result = x % 10;
		// 	x = Math.floor(x / 10);
			// System.out.println(x + " " + result);
		// 	sum += result;
		// }
		// System.out.println(numberLength(1234));
		// System.out.println(cubeSet.contains(66430125.));
		// System.out.println(numberLength(66430125.));
		// System.out.println(cubeTester(41063625., cubeSet));
	}
}