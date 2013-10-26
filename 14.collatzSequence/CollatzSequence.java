/*
	The following iterative sequence is defined for the set of positive 
	integers:

	n → n/2 (n is even)
	n → 3n + 1 (n is odd)

	Using the rule above and starting with 13, we generate the following 
	sequence:

	13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
	It can be seen that this sequence (starting at 13 and finishing at 
	1) contains 10 terms. Although it has not been proved 
	yet (Collatz Problem), it is thought that all starting numbers 
	finish at 1.

	Which starting number, under one million, produces the longest chain?

	NOTE: Once the chain starts the terms are allowed to go above one million.
*/

import java.lang.Math;

public class CollatzSequence {

	public static void firstTry () {
		double dStart = 2.0;
		boolean bKeepGoing = true;

		/*
			Tried to count upwards from 2.0 using the inverse of the
			equations...didn't work. Got: 

			2.0, 4.0, 8.0, 16.0, 5.0, 10.0, 3.0, 6.0, 12.0, 24.0, 48.0, 
			96.0, 192.0, 384.0, 768.0, 1536.0, 3072.0, 6144.0, 12288.0, 
			24576.0, 49152.0, 98304.0, 196608.0, 393216.0, 786432.0
		*/
		while (dStart < 1000000){
			System.out.print(dStart + ", ");
			/*
				Check to see if the number is even
			*/
			if (dStart % 2 == 0) {
				/*
					check to see if n / 3 + 1 is a whole number not equal
					to 1
				*/
				// System.out.println("Here");
				if (dStart / 3.0 - (1.0 / 3.0) == 1 || ((dStart / 3.0 - (1.0 / 3.0)) - Math.floor(dStart / 3.0 - 1.0 / 3.0)) != 0.0) {
					dStart *= 2;
					// System.out.println("there");
				}
				else {
					dStart = dStart / 3.0 - 1.0/3.0;
				}
			} else {
				dStart *= 2;
			}
		}		
	}
	/*
		This one worked: straightforward, just counts down trying
		them all in main starting at 999999.
	*/
	public static int secondTry (double iSum) {
		// double dStart = 999999;
		int icount = 0;
		int iLinks = 0;
		// int iSum = 0;

		while (iSum != 1) {
			if (iSum % 2 == 0){
				iSum = iSum / 2;
				iLinks += 1;
				// System.out.println(iSum + ", ");
			} else {
				iSum = 3 * iSum + 1;
				iLinks += 1;
				// System.out.println(iSum + ", ");
			}

		}
		return iLinks;
	}
	public static void main(String[] args) {

		double dCounter = 999999d;
		int iMax = 0;
		int iHold = 0;

		/*
			I got 524 as the max links, but didn't know the
			value. So I modified it to stop at 524 and give
			me the value.

			Another, more elegant way to do this later.
		*/
		while (dCounter > 2) {
			iHold = secondTry(dCounter);
			iMax = (iHold > iMax) ? iHold : iMax;
			if (iMax == 524) {
				System.out.println(dCounter);
				break;
			}
			dCounter--;
			// System.out.print(dCounter + ", ");
		}
		System.out.println(iMax);
		// System.out.println(secondTry(997823d));
	}
}