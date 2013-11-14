/*
	Let d(n) be defined as the sum of proper divisors of n (numbers less 
	than n which divide evenly into n).

	If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable 
	pair and each of a and b are called amicable numbers.

	For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 
	20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors 
	of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

	Evaluate the sum of all the amicable numbers under 10000.
*/

public class AmicableNumbers {

	public static boolean isAmicable(int num) {

		boolean isAmicable = false;
		int sum = sumOfDivisors(num);

		isAmicable = (num == sumOfDivisors(sum)) ? true : false;

		if (sum == num) {
			isAmicable = false;
		}

		return isAmicable;
	}

	public static int sumOfDivisors(int num) {

		int sum = 0;

		for (int i = num / 2; i > 0; i--) {
			if (num % i == 0) {
				sum += i;
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		
		int sum = 0;

		// System.out.println("Sum of divisors: " + sumOfDivisors(2));
		// System.out.println("Is this number amicable: " + isAmicable(2));

		for (int i = 3; i < 10000; i++) {
			if (isAmicable(i) == true) {
				sum += i;
				System.out.print(i + ", ");
			}
		}

		System.out.println("Sum: " + sum);
	}
}