/*
	Sum Square Difference (Problem 6):
		The sum of the squares of the first ten natural numbers is,

		12 + 22 + ... + 102 = 385
		The square of the sum of the first ten natural numbers is,

		(1 + 2 + ... + 10)2 = 552 = 3025
		Hence the difference between the sum of the squares of the first 
		ten natural numbers and the square of the sum is 3025 − 385 = 2640.

		Find the difference between the sum of the squares of the first 
		one hundred natural numbers and the square of the sum.
*/
import java.lang.Math;

public class SumSquareDiff {
	
	public static int sumSquares (int num) {
		int sum = 0;
		for (int i = 1; i <= num; i++) {
			sum += Math.pow(i, 2);
		}
		return sum;
	}

	public static double sumSquared (int num) {
		double sum = 0;
		for (int i = 1; i <= num; i++) {
			sum += i;
		}
		return Math.pow(sum, 2);
	}

	public static void main(String[] args) {
		System.out.println(sumSquared(100) - sumSquares(100));
	}
}