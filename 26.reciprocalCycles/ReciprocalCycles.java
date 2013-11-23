/*
	A unit fraction contains 1 in the numerator. The decimal representation 
	of the unit fractions with denominators 2 to 10 are given:

	1/2	= 	0.5
	1/3	= 	0.(3)
	1/4	= 	0.25
	1/5	= 	0.2
	1/6	= 	0.1(6)
	1/7	= 	0.(142857)
	1/8	= 	0.125
	1/9	= 	0.(1)
	1/10	= 	0.1

	Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. 
	It can be seen that 1/7 has a 6-digit recurring cycle.

	Find the value of d < 1000 for which 1/d contains the longest recurring 
	cycle in its decimal fraction part.

	--------------------------------
	Has some issues at first as I was just trying to do it and not really
	thinking about the problem. After thinking about it, I as able to
	code the solution below relatively quickly.
*/

import java.math.*;
import java.util.ArrayList;

public class ReciprocalCycles {
	public static void main(String[] args) {
	
		// ArrayList that holds current longest cycle 
		ArrayList<Integer> cycle = new ArrayList<Integer>();

		int numerator = 1;
		int sum = 0;

		boolean stop = false;

		// Calculate the cycle for each number 2 -> 1000
		for (int i = 2; i <= 1000; i++) {

			while (stop != true) {
				// A little inefficient here, as the loop will have to go
				// through multiple times as numbers > 10 will need to be
				// multiplied again. Still works though.
				if (numerator / i == 0) {
					numerator *= 10;
					stop = false;

				}
				// If the numerator is in the cycle, we are going to start the
				// process over again, so quit. Otherwise add the numerator.
				if (cycle.contains(numerator) == false) {
					cycle.add(numerator);
				} else {
					stop = true;
				}

				numerator = numerator % i;
			}

			sum = (cycle.size() > sum) ? cycle.size() : sum;

			cycle.clear();
			numerator = 1;
			stop = false;

			System.out.println("The sum of " + i + " is: " + sum);
		}


		// for (Integer x : cycle) {
		// 	System.out.println(x);
		// }

		// double dFraction = 1;
		// String sFraction, sShortened;
		// long lNum = 100000000000L;

		// ArrayList<Integer> primes = new ArrayList<Integer>();
		// primes.add(17);
		// primes.add(19);
		// primes.add(23);
		// primes.add(29);
		// primes.add(31);
		// primes.add(37);
		// primes.add(773);
		// primes.add(995);
		// primes.add(997);

		// for (Integer x : primes) {
		// 	BigDecimal bigD = BigDecimal.ONE.divide(new BigDecimal(x), 500, RoundingMode.HALF_UP);
		// 	System.out.println(bigD);
		// }

		// System.out.println(lNum % 997 == 0);

		/*
			Create a shortened output removing the decimal and leading 
			zeros.
		*/
		// for (int i = 2; i <= 1000; i++) {
			// dFraction = 1. / i;
			// sFraction = Double.toString(dFraction);
			// sShortened = sFraction.substring(2);
			// if (sShortened.length() > 6) {
			// 	if (sShortened.substring(2,3).equals("0")) {
			// 		if (sShortened.substring(3,4).equals("0")) {
			// 			System.out.println(sShortened.substring(3));
			// 		} else {
			// 			System.out.println(sShortened.substring(3));
			// 		}
			// 	} else{
			// 		System.out.println(sShortened.substring(2));
			// 	}	
			// System.out.println(i + " " + dFraction);
			//}
		// }



		// for (int i = 2; i <= 100; i = i + 2) {
		// 	dFraction = 1. / i;
		// 	System.out.printf(i + " %.15f \n", dFraction);
			// BigDecimal bFraction = BigDecimal.ONE.divide(new BigDecimal(i, 1));
			// System.out.printf("%.20f \n", bFraction);
		// }

		// ArrayList<Integer> aDivisors = new ArrayList<Integer>();

		// for (int i = 2; i <= 100; i++) {
		// 	if (lNum % i == 0) {
				
		// 	} else {
		// 		aDivisors.add(i);
		// 	}
		// }

		// for (Integer x : aDivisors) {
		// 	sFraction = Double.toString(1. / x);
		// 	sShortened = sFraction.substring(2);
		// 	if (sShortened.substring(2,5).equals(sShortened.substring(6,9))) {
		// 		System.out.print(x + ": " + sShortened.substring(0,16));
		// 	}


		// 	// if (sShortened.substring(0,1).equals("1")) {
		// 	// 	System.out.print(x + ": " + sShortened.substring(1));
		// 	// } else {
		// 	// 	System.out.print(x + ": " + sShortened.substring(0));	
		// 	// }
			
		// 	// System.out.print(1. / x);
		// 	System.out.println();
		// }

		// for (int i = 0; i < aDivisors.size(); i++) {
		// 	sFraction = Double.toString(1. / aDivisors.get(i));
		// 	sShortened = sFraction.substring(2);
		// 	if (sShortened.substring(2,5).equals(sShortened.substring(6,9))) {
		// 		aDivisors.remove(i);
		// 	} else if (sShortened.substring(3,6).equals(sShortened.substring(7,10))) {
		// 		aDivisors.remove(i);
		// 	}
		// }

		// for (Integer x : aDivisors) {
		// 	System.out.print(x + ": ");
		// 	System.out.print(1. / x);
		// 	System.out.println();
		// }

	}
}