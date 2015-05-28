/*
	All square roots are periodic when written as continued 
	fractions and can be written in the form:
	
	It can be seen that the sequence is repeating. For conciseness, we 
	use the notation √23 = [4;(1,3,1,8)], to indicate that the block (1,3,1,8) 
	repeats indefinitely.

	The first ten continued fraction representations of (irrational) square 
	roots are:

	√2=[1;(2)], period=1
	√3=[1;(1,2)], period=2
	√5=[2;(4)], period=1
	√6=[2;(2,4)], period=2
	√7=[2;(1,1,1,4)], period=4
	√8=[2;(1,4)], period=2
	√10=[3;(6)], period=1
	√11=[3;(3,6)], period=2
	√12= [3;(2,6)], period=2
	√13=[3;(1,1,1,1,6)], period=5

	Exactly four continued fractions, for N ≤ 13, have an odd period.

	How many continued fractions for N ≤ 10000 have an odd period?
	------------------------------------------------------------

	Started fresh as I couldn't grasp what I was trying to do with the 
	other program. My methodology was simple: 
		1a. Write out the steps on paper for an iteration
		1b. Code that iteration, then code the next iteration looking 
		for patterns that I could repeat

		2. I found patterns, but I wasn't getting the right answer. 
		Using the sqrt function didn't give me the exact precision that
		I needed to test for duplicate in the patter. This was my issue
		in my first attempt. To fix this, I looked for ways to keep
		everything in whole numbers, which I was actually able to do.

		3. Realized I would need to reduce fractions in some cases, but
		not in others. Created some special reduction functions for the 
		numerator and denominator. Called those functions to make sure 
		each loop gave me the right set of numbers.

		4. It's tricky to define when you've reached a continuous set
		of numbers. I solved this in a previous problem by stopping 
		the loop once I reach an equation that I had previously solved.
		By tracking each equation, I could tell (and count) when the loop
		would continue. I did this and stored the digits I needed in a list.

		5. Sum the list of odd length lists.
*/

import java.util.ArrayList;
import java.util.HashSet;

public class PeriodSqrRoot {
	
	/*
		I do this, but don't need to. I forget what I was doing, but
		I had some error when performing a rational square check. This 
		skips rational squares.
	*/
	private	static ArrayList<Integer> generateList (int limit) {
		ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
		for (Integer i = 2; i <= limit; i++) {
			Double dSqrt = Math.sqrt(i);
			Double rndSqrt = Math.floor(dSqrt);
			if (dSqrt % rndSqrt != 0.) {
				listOfNumbers.add(i);
			}
		}
		return listOfNumbers;
	}

	/*
		Reduces the 'numerator' fraction. Luckily you always do this by dividing
		by the denominatorHolder.
	*/
	private static Double reduceFraction(Double denominator, Double numerator, Double denominatorHolder) {
		// System.out.println(denominator + " " + numerator);
		// if (numerator % denominator == 0 && denominatorHolder % denominator == 0) {
		// 	System.out.println("numerator / denominator: " + numerator/denominator);
		// 	return numerator / denominator;
		// } else {
		// 	return reduceFraction(denominator - 1, numerator, denominatorHolder);
		// }
		return numerator / denominatorHolder;
	}

	/*
		Same as above, except with the denominator.
	*/
	private static Double reduceDenominator(Double tempDenom, Double commonDenom) {
		// Double lcd = unreducedNumerator / newTempRemainder;
		// System.out.println("lcd: " + lcd);
		// return tempDenom / lcd;
		return tempDenom / commonDenom;
	}

	/*
		Main function that does all the work. Has a lot of unnecessary
		stuff that isn't actually used.
	*/
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		HashSet<ArrayList> setOfNumbers = new HashSet<ArrayList>();
		HashSet<String> stringOfNumbers = new HashSet<String>();
		ArrayList<String> patternHolder = new ArrayList<String>();
		String holder = "";
		String pattern = "";
		list = generateList(10000);
		Integer counter = 0;

		// Go through the list of irrational primes
		for (Integer i : list) {
			stringOfNumbers.clear();
			// if (Math.sqrt(i) - Math.floor(Math.sqrt(i)) == 0) {
			// 	continue;
			// }

			// Get the set of numbers that will be used throughout
			final Integer iInput = i;
			final Double dSqrt = Math.sqrt(iInput);
			final Double iSqrtFloor = Math.floor(dSqrt);
			ArrayList<Double> continuedFractions = new ArrayList<Double>();
			
			// Perform first iteration. Tried to make variable names make sense
			// but failed miserably.
			// --------------------------------------------
			Double tempRemainder = -iSqrtFloor; // -5
			Double invTempRemainder = -tempRemainder; // 5
			Double tempDenomHolder = 1.;
			Double tempDenom = iInput + tempRemainder * invTempRemainder; // 32 + -5 * 5 = 7
			Double tempPosNumerator = tempDenomHolder * (iSqrtFloor + invTempRemainder); // 5 + 5  = 10
			Double tempContFrac = Math.floor(tempPosNumerator / tempDenom); // Math.floor(10/7) = 1
			continuedFractions.add(tempContFrac); // add(1)
			Double tempPosNumeratorRemainder = tempPosNumerator % tempDenom; // 10 % 7 = 3
			Double unreducedNumerator = tempDenomHolder * -iSqrtFloor + tempPosNumeratorRemainder; // 1 * -5 + 3 = -2
			// System.out.println("tempDenom: " + tempDenom + " unreducedNumerator: " + unreducedNumerator + " tempDenomHolder: " + tempDenomHolder);
			Double newTempRemainder = (tempDenomHolder == 1) ? unreducedNumerator : reduceFraction(tempDenom, unreducedNumerator, tempDenomHolder); // reduceFraction(7, -3) = -3
			tempDenom = (tempDenomHolder == 1) ? tempDenom : reduceDenominator(tempDenom, tempDenomHolder);
			
			// Continued with iterations until we reach the same set of numbers meaning
			// the restart of the loop
			// ---------------------------------------------
			while (true) {
				holder = "";
				pattern = "";

				tempRemainder = newTempRemainder; // -2
				invTempRemainder = -tempRemainder; // 2
				tempDenomHolder = tempDenom; // 1 -> 7
				tempDenom = iInput + tempRemainder * invTempRemainder; // 32 + -2 * 2 = 28
				tempPosNumerator = tempDenomHolder * (iSqrtFloor + invTempRemainder); // 7 * (4 + 3) = 49
				tempContFrac = Math.floor(tempPosNumerator / tempDenom); // 49 / 14 = 3
				continuedFractions.add(tempContFrac); // add(3)
				tempPosNumeratorRemainder = tempPosNumerator % tempDenom; // 49 % 14 = 7
				unreducedNumerator = tempDenomHolder * -iSqrtFloor + tempPosNumeratorRemainder; // 7 * -5 + 21 = -14
				// System.out.println("1. tempDenom: " + tempDenom + " unreducedNumerator: " + unreducedNumerator + " tempDenomHolder: " + tempDenomHolder);
				newTempRemainder = (tempDenomHolder == 1) ? unreducedNumerator : reduceFraction(tempDenom, unreducedNumerator, tempDenomHolder); // reduceFraction(7, -21, 7)
				tempDenom = (tempDenomHolder == 1) ? tempDenom : reduceDenominator(tempDenom, tempDenomHolder); // 2
				// System.out.println("2. tempDenom: " + tempDenom + " newTempRemainder: " + newTempRemainder + " tempDenomHolder: " + tempDenomHolder);

				pattern = pattern + Double.toString(tempContFrac); 

				holder = Double.toString(newTempRemainder) + " " + Double.toString(tempDenom) + Double.toString(tempDenomHolder);

				if (stringOfNumbers.contains(holder)) {
					break;
				}
				stringOfNumbers.add(holder);

				// System.out.println(stringOfNumbers);
			}
			// System.out.println(i + " " + continuedFractions);
			// System.out.println(i + " " + stringOfNumbers.size());
			

			if (stringOfNumbers.size() % 2 != 0) {
				counter++;
			}
		}

		System.out.println(counter);
	}
}