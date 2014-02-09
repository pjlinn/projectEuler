/*
	The number, 1406357289, is a 0 to 9 pandigital number because it is 
	made up of each of the digits 0 to 9 in some order, but it also has 
	a rather interesting sub-string divisibility property.

	Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, 
	we note the following:

	d2d3d4=406 is divisible by 2
	d3d4d5=063 is divisible by 3
	d4d5d6=635 is divisible by 5
	d5d6d7=357 is divisible by 7
	d6d7d8=572 is divisible by 11
	d7d8d9=728 is divisible by 13
	d8d9d10=289 is divisible by 17

	Find the sum of all 0 to 9 pandigital numbers with this property.
*/

import java.util.ArrayList;

public class SubstringDivisibility {
	
	ArrayList<String> pandigitalList = new ArrayList<String>();

	// Brute force check of the substring Divisibility
	private static boolean substringDivisibilityCheck(String pandigitalNumber) {
		boolean isSubstringDivisible = false;

		if (Integer.parseInt(pandigitalNumber.substring(1, 4)) % 2 == 0) {
			if (Integer.parseInt(pandigitalNumber.substring(2, 5)) % 3 == 0) {
				if (Integer.parseInt(pandigitalNumber.substring(3, 6)) % 5 == 0) {
					if (Integer.parseInt(pandigitalNumber.substring(4, 7)) % 7 == 0) {
						if (Integer.parseInt(pandigitalNumber.substring(5, 8)) % 11 == 0) {
							if (Integer.parseInt(pandigitalNumber.substring(6, 9)) % 13 == 0) {
								if (Integer.parseInt(pandigitalNumber.substring(7, 10)) % 17 == 0) {
									isSubstringDivisible = true;
								}
							}
						}
					}
				}
			}
		}
		return isSubstringDivisible;		
	}
	// Generates an ArrayList of pandigital numbers
	private String pandigitalGenerator(ArrayList<Integer> digits, String result) {
		if (digits.size() == 0) {
			pandigitalList.add(result);
			return result;
		}
		for (int i = digits.size() - 1; i >= 0; i--) {
			String tempResult = result;
			int tempDigit = digits.get(i);
			result += Integer.toString(tempDigit);
			digits.remove(i);
			pandigitalGenerator(digits, result);
			digits.add(i, tempDigit);

			result = tempResult;
		}
		return result;
	}
	public static void main(String[] args) {
		// String pandigital = "1406357289";
		long counter = 0L;
		ArrayList<Integer> digits = new ArrayList<Integer>();

		digits.add(0);
		digits.add(1); digits.add(2); digits.add(3);
		digits.add(4); digits.add(5); digits.add(6);
		digits.add(7); digits.add(8); digits.add(9);

		SubstringDivisibility x = new SubstringDivisibility();

		x.pandigitalGenerator(digits, "");

		for (String pandigital : x.pandigitalList) {
			if (substringDivisibilityCheck(pandigital)) {
				counter += Long.parseLong(pandigital);
			}
		}
		System.out.println(counter);
	}
}