import java.util.ArrayList;
import java.util.HashSet;

public class PeriodSqrRoot {
	
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
	// Adjusted because this is wrong. It gives you the factor
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
	// Adjusted because this is wrong. It gives you the factor
	private static Double reduceDenominator(Double tempDenom, Double commonDenom) {
		// Double lcd = unreducedNumerator / newTempRemainder;
		// System.out.println("lcd: " + lcd);
		// return tempDenom / lcd;
		return tempDenom / commonDenom;
	}

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

		for (Integer i : list) {
			stringOfNumbers.clear();
			// if (Math.sqrt(i) - Math.floor(Math.sqrt(i)) == 0) {
			// 	continue;
			// }
			final Integer iInput = i;
			final Double dSqrt = Math.sqrt(iInput); // 5.xxxx
			final Double iSqrtFloor = Math.floor(dSqrt); // 5
			ArrayList<Double> continuedFractions = new ArrayList<Double>();
			// continuedFractions.add(iSqrtFloor); // 5
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
			// System.out.println("tempDenom: " + tempDenom + " newTempRemainder: " + newTempRemainder + " tempDenomHolder: " + tempDenomHolder);
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

				// counter++;
				pattern = pattern + Double.toString(tempContFrac); 



				holder = Double.toString(newTempRemainder) + " " + Double.toString(tempDenom) + Double.toString(tempDenomHolder); // +
						// " " + Double.toString(unreducedNumerator);
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
		// ---------------------------------------------
		// tempRemainder = newTempRemainder; // -3
		// invTempRemainder = -tempRemainder; // 3
		// tempDenomHolder = tempDenom; // 7 -> 2
		// tempDenom = iInput + tempRemainder * invTempRemainder; // 23 + -3 * 3 = 14
		// tempPosNumerator = tempDenomHolder * (iSqrtFloor + invTempRemainder); // 2 * (4 + 3) = 14
		// tempContFrac = Math.floor(tempPosNumerator / tempDenom); // 14 / 14
		// continuedFractions.add(tempContFrac); // add(1)
		// tempPosNumeratorRemainder = tempPosNumerator % tempDenom; // 14 % 14 = 0
		// unreducedNumerator = tempDenomHolder * -iSqrtFloor + tempPosNumeratorRemainder; // 2 * -4 + 0 = -8
		// newTempRemainder = reduceFraction(tempDenom, unreducedNumerator, tempDenom); // reduceFraction(14, -8, 14)
		// tempDenom = reduceDenominator(unreducedNumerator, newTempRemainder, tempDenom);

		System.out.println(counter);
	}
}