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

	***Correct, but def needs to be cleaned up
*/

public class NumberLetter {

	final int charDiff = 48;

	final String s0 = "";
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

	final String s20 = "twenty";
	final String s30 = "thirty";
	final String s40 = "forty";
	final String s50 = "fifty";
	final String s60 = "sixty";
	final String s70 = "seventy";
	final String s80 = "eighty";
	final String s90 = "ninety";

	final String s00 = "hundred";

	final String s000 = "thousand";

	final String sAnd = "and";

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
		90 - 99

		I can gain some efficiency with the teen numbers, but I just hard 
		coded it for now...
	*/
	public int doubleDigit (String num) {

		int sum = 0;

		if (num.charAt(0) - charDiff == 1) {
			if (num.charAt(1) - charDiff == 0) {
				System.out.println("Numeric value = " + num + ", which is " + s10.length() + " digits long.");
				return s10.length();
			} else if (num.charAt(1) - charDiff == 1) {
				System.out.println("Numeric value = " + num + ", which is " + s11.length() + " digits long.");
				return s11.length();
			} else if (num.charAt(1) - charDiff == 2) {
				System.out.println("Numeric value = " + num + ", which is " + s12.length() + " digits long.");
				return s12.length();
			} else if (num.charAt(1) - charDiff == 3) {
				System.out.println("Numeric value = " + num + ", which is " + s13.length() + " digits long.");
				return s13.length();
			} else if (num.charAt(1) - charDiff == 4) {
				System.out.println("Numeric value = " + num + ", which is " + s14.length() + " digits long.");
				return s14.length();
			} else if (num.charAt(1) - charDiff == 5) {
				System.out.println("Numeric value = " + num + ", which is " + s15.length() + " digits long.");
				return s15.length();
			} else if (num.charAt(1) - charDiff == 6) {
				System.out.println("Numeric value = " + num + ", which is " + s16.length() + " digits long.");
				return s16.length();
			} else if (num.charAt(1) - charDiff == 7) {
				System.out.println("Numeric value = " + num + ", which is " + s17.length() + " digits long.");
				return s17.length();
			} else if (num.charAt(1) - charDiff == 8) {
				System.out.println("Numeric value = " + num + ", which is " + s18.length() + " digits long.");
				return s18.length();
			} else if (num.charAt(1) - charDiff == 9) {
				System.out.println("Numeric value = " + num + ", which is " + s19.length() + " digits long.");
				return s19.length();
			}
		} else if (num.charAt(0) - charDiff == 2) {
			sum = s20.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 3) {
			sum = s30.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 4) {
			sum = s40.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 5) {
			sum = s50.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 6) {
			sum = s60.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 7) {
			sum = s70.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 8) {
			sum = s80.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 9) {
			sum = s90.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		} else if (num.charAt(0) - charDiff == 0) {
			sum = s0.length() + singleDigit(Character.toString(num.charAt(1)));
			return sum;
		}

		return -9999999;
	}

	/*
		Triple digit method
	*/

	public int tripleDigit(String num) {
		// when passed the 000 from 1000
		if (num.charAt(0) - charDiff == 0 && num.charAt(1) - charDiff == 0 && num.charAt(2) - charDiff == 0) {
			return 0;
		}

		// automatically adds the hundred length
		int sum = s00.length();
		System.out.println("a hundred is : " + sum + " chars long.");

		sum += singleDigit(Character.toString(num.charAt(0)));

		if (num.charAt(1) - charDiff == 0 && num.charAt(2) - charDiff == 0) {
			return sum;
		} else {
			// add the 'and'
			sum += sAnd.length();
			System.out.println("Summm:" + sum);
			sum += doubleDigit(num.substring(1));
		}

		return sum;
	}

	/*
		Four digit method
	*/

	public int fourDigit(String num) {

		int sum = s000.length();

		sum += singleDigit(num.substring(0,1));
		sum += tripleDigit(num.substring(1));

		return sum;
	}


	public static void main(String[] args) {

		String stringtest = "123";
		NumberLetter test = new NumberLetter();
		int iSum = 0;
		int y = 1000;

		for (int i = 1; i <= y; i++) {
			String sNum = Integer.toString(i);
			/*
				Check the length of the string (i.e. number), which
				will decide which method to call.

				Trying to minimize the hard coding...
			*/
			if (sNum.length() == 1) {
				iSum += test.singleDigit(sNum);
			} else if (sNum.length() == 2) {
				iSum += test.doubleDigit(sNum);
			} else if (sNum.length() == 3) {
				iSum += test.tripleDigit(sNum);
			} else if (sNum.length() == 4) {
				iSum += test.fourDigit(sNum);
			} else {
				System.out.println("Error");
			}
		}

		System.out.println(iSum);
		// System.out.println(stringtest.substring(0,1));
	}
}