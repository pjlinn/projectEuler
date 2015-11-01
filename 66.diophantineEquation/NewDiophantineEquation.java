/*
	Consider quadratic Diophantine equations of the form:

	x2 – Dy2 = 1

	For example, when D=13, the minimal solution in x is 
	6492 – 13×1802 = 1.

	It can be assumed that there are no solutions in 
	positive integers when D is square.

	By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, 
	we obtain the following:

	32 – 2×22 = 1
	22 – 3×12 = 1
	92 – 5×42 = 1
	52 – 6×22 = 1
	82 – 7×32 = 1

	Hence, by considering minimal solutions in x for D ≤ 7, 
	the largest x is obtained when D=5.

	Find the value of D ≤ 1000 in minimal solutions of x for 
	which the largest value of x is obtained.

	---------------------------------------------

	Tried this on DiophantineEquation.java, but was brute forcing
	it to no avail. Looked up the pell equation on the sites listed below.
	Built off of my solution to problem 64, which did the first step of
	the solution. From there, I added the second part to find x and y.

	Basically coded the solution I found online.
*/

import java.util.ArrayList;
import java.util.HashSet;

public class NewDiophantineEquation {
	
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
		return numerator / denominatorHolder;
	}

	/*
		Same as above, except with the denominator.
	*/
	private static Double reduceDenominator(Double tempDenom, Double commonDenom) {
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
		list = generateList(1000);
		Integer counter = 0;
		Double max = 0.0;

		// Go through the list of irrational 
		for (Integer i : list) {
			stringOfNumbers.clear();

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
			
			/*
				Pell's equation code using solution to problem 64 which generated the
				period length and the period itself. Used the algorithm from: 
				http://www.math.leidenuniv.nl/~psh/ANTproc/01lenstra.pdf &
				http://www-history.mcs.st-and.ac.uk/HistTopics/Pell.html

				Basically add up the continued fraction of the sqrt(d). What digits
				you add up depends on the period number.

			*/
			Integer periodSize = stringOfNumbers.size();

			// If even period, truncate after first cycle
			if (periodSize % 2 == 0) {
				if (periodSize >= 3) { // My algorithm needs at least 3 numbers
					Double numerator = 1.0;
					Double denominator1 = continuedFractions.get(periodSize - 2); // 2nd to last index
					Double denominator2 = continuedFractions.get(periodSize - 3); //3rd to last
					Double newNumerator = 1.0;

					for (int x = periodSize - 4; x >= -1; x--) {
						numerator = denominator2 * denominator1 + newNumerator;
						newNumerator = denominator1;
						denominator1 = numerator;

						if (x > -1) { // can't have -1 index
							denominator2 = continuedFractions.get(x);
							// System.out.println(denominator2);
						}
					}
					numerator = iSqrtFloor * denominator1 + newNumerator;

					if (numerator > max) {
						max = numerator;
						System.out.println(i + " " + numerator + " " + denominator1);
					}
				}
			// If odd period, truncate after second cycle
			} else {
				ArrayList<Double> newList = new ArrayList<Double>();

				for (int b = 0; b < periodSize; b++) {
					newList.add(continuedFractions.get(b));
				}
				for (int c = 0; c < periodSize -1; c++) {
					newList.add(continuedFractions.get(c));
				}

				periodSize = newList.size();

				if (periodSize >= 3) { // My algorithm only works for periods at least 3 numbers
					Double numerator = 1.0;
					Double denominator1 = newList.get(periodSize - 1); // Want the last digit of the new list
					Double denominator2 = newList.get(periodSize - 2);
					Double newNumerator = 1.0;
					
					for (int x = periodSize - 3; x >= -1; x--) {
						numerator = denominator2 * denominator1 + newNumerator;
						newNumerator = denominator1;
						denominator1 = numerator;

						if (x > -1) { // can't have -1 index
							denominator2 = newList.get(x);
							// System.out.println(denominator2);
						}
					}
					numerator = iSqrtFloor * denominator1 + newNumerator;

					if (numerator > max) {
						max = numerator;
						System.out.println(i + " " + numerator + " " + denominator1);
					}

				}
			}

		}

		// System.out.println(counter);
	}
}