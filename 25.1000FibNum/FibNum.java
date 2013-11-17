/*
	The Fibonacci sequence is defined by the recurrence relation:

	Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
	Hence the first 12 terms will be:

	F1 = 1
	F2 = 1
	F3 = 2
	F4 = 3
	F5 = 5
	F6 = 8
	F7 = 13
	F8 = 21
	F9 = 34
	F10 = 55
	F11 = 89
	F12 = 144
	The 12th term, F12, is the first term to contain three digits.

	What is the first term in the Fibonacci sequence to 
	contain 1000 digits?

	------

	You don't need to keep track of the entire number.
	I started it at 7 digits long and the chopped it down by a digit
	everytime it got back to 7, adding a counter of the total number 
	of digits and keeping track of total terms.
*/

public class FibNum {
	public static void main(String[] args) {
		long a = 832040L;
		long b = 514229L;
		long c = 0L;
		long counter = 6;
		long term = 30;
		int num5s = 2;
		int digits = 1;
		int x = 1;


		// Calcualtes the term that adds a digit
		while(counter < 1000) {
			c = a + b;
			b = a;
			if (c > 1000000) {
				c = c / 10;
				b = a / 10;
				a = c;
				counter++;
				term++;
			} else {
				a = c;
				term++;
			}
			System.out.println(c + "	counter: " + counter + "	term: " + term);
		}
		System.out.println("term: " + term);
	}
}