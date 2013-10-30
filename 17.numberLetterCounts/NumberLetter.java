/*
	If the numbers 1 to 5 are written out in words: one, two, three, 
	four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in 
	total.

	If all the numbers from 1 to 1000 (one thousand) inclusive were 
	written out in words, how many letters would be used?


	NOTE: Do not count spaces or hyphens. For example, 342 (three 
	hundred and forty-two) contains 23 letters and 115 (one hundred 
	and fifteen) contains 20 letters. The use of "and" when writing 
	out numbers is in compliance with British usage.
*/

public class NumberLetter {

	final int charDiff = 48;

	final String s0 = "zero";
	final String s1 = "one";
	final String s2 = "two";
	final String s3 = "three";
	final String s4 = "four";
	final String s5 = "five";
	final String s6 = "six";
	final String s7 = "seven";
	final String s8 = "eight";
	final String s9 = "nine";

	final String s10 = "ten";
	final String s11 = "eleven";
	final String s12 = "twelve";
	final String s13 = "thirteen";
	final String s14 = "fourteen";
	final String s15 = "fifteen";
	final String s16 = "sixteen";
	final String s17 = "seventeen";
	final String s18 = "eighteen";
	final String s19 = "nineteen";

	/*
		Single digit method
		0 - 9
	*/
	public int singleDigit (String num) {

		if (num.charAt(0) - charDiff == 1) {
			System.out.println("Numeric value = " + num + ", which is " + s1.length() + " digits long.");
			return s1.length();
		} else if (num.charAt(0) - charDiff == 2) {
			System.out.println("Numeric value = " + num + ", which is " + s2.length() + " digits long.");
			return s2.length();
		} else if (num.charAt(0) - charDiff == 3) {
			System.out.println("Numeric value = " + num + ", which is " + s3.length() + " digits long.");
			return s3.length();
		} else if (num.charAt(0) - charDiff == 4) {
			System.out.println("Numeric value = " + num + ", which is " + s4.length() + " digits long.");
			return s4.length();
		} else if (num.charAt(0) - charDiff == 5) {
			System.out.println("Numeric value = " + num + ", which is " + s5.length() + " digits long.");
			return s5.length();
		} else if (num.charAt(0) - charDiff == 6) {
			System.out.println("Numeric value = " + num + ", which is " + s6.length() + " digits long.");
			return s6.length();
		} else if (num.charAt(0) - charDiff == 7) {
			System.out.println("Numeric value = " + num + ", which is " + s7.length() + " digits long.");
			return s7.length();
		} else if (num.charAt(0) - charDiff == 8) {
			System.out.println("Numeric value = " + num + ", which is " + s8.length() + " digits long.");
			return s8.length();
		} else if (num.charAt(0) - charDiff == 9) {
			System.out.println("Numeric value = " + num + ", which is " + s9.length() + " digits long.");
			return s9.length();
		} else if (num.charAt(0) - charDiff == 0) {
			System.out.println("Numeric value = " + num + ", which is " + s0.length() + " digits long.");
			return s0.length();
		}

		return -99999;
	}

	/*
		double digit method:
		10 -19
		20 - 29
		...
		90 99
	*/
	public int doubleDigit (String num) {

		if (num.charAt(0) -charDiff == 1) {
			if (num.charAt(1) - charDiff == 0) {
				System.out.println("Numeric value = " + num + ", which is " + s10.length() + " digits long.");
				return s10.length();
			}
		}
		return -999999;
	}


	public static void main(String[] args) {

		NumberLetter test = new NumberLetter();
		int iSum = 0;

		for (int i = 1; i < 11; i++) {
			String sNum = Integer.toString(i);
			/*
				Check the length of the string (i.e. number), which
				will decide which method to call.

				Trying to minimize the hard coding...
			*/
			if (sNum.length() == 1) {
				iSum += test.singleDigit(sNum);
			}
			if (sNum.length() == 2) {
				iSum += test.doubleDigit(sNum);
			}



/*
			if (num.length() == 1) {
				if (num.charAt(0) - charDiff == 1) {
					System.out.println("String length = " + num.length());			
				} else if (num.charAt(0) - charDiff == 2) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 3) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 4) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 5) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 6) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 7) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 8) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 9) {
					System.out.println("String length = " + num.length());
				} else if (num.charAt(0) - charDiff == 0) {
					System.out.println("String length = " + num.length());
				}
			} else if (num.length() == 2) {
				System.out.println("String length = " + num.length());
			} else if (num.length() == 3) {
				System.out.println("String length = " + num.length());
			} else if (num.length() == 4) {
				System.out.println("String length = " + num.length());
			}
*/
		}

		System.out.println(iSum);
	}
}