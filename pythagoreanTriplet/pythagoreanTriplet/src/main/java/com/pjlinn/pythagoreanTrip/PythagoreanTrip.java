/*
	Special Pythagorean Triplet (Problem 9):
		A Pythagorean triplet is a set of three natural numbers, 
		a < b < c, for which,

			a^2 + b^2 = c^2
		For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

		There exists exactly one Pythagorean triplet for which 
		a + b + c = 1000.
		Find the product abc.
*/

package com.pjlinn.pythagoreanTrip;

import java.lang.Math;

public class PythagoreanTrip {
	/*
		Pretty unimaginative way to solve this. I basically just cycle
		through integers looking for the special Pythagorean Triplet.

		The for tests for the 3 parameters:
			a < b < c
			a + b + c = 1000
			a^2 + b^2 = c^2

		I test the a + b + c = 1000 first because I think that will be
		less computationally expensive although I do not know this 
		for sure. 

		TO-DO: I don't need to set such loose bounds for my for loops. 
		Adjsut parameters to more accurately represent possible ranges.
			For instance, start with the upper range and decrease by 1
			rather than start low and increment up by one.
			Also, it could be better to start the for loops with b, or c,
			and set a to be b - 1
	*/
	public static int pythagoreanTrip(int num) {

		for (int a = 1; a < num / 2; a++) {
			// a < b 
			for (int b = a + 1; b < num / 2; b++) {
				// b < c
				for (int c = b + 1; c < num; c++) {
					// a + b + c = 1000
					if (a + b + c == num) {
						// a^2 + b^2 = c^2
						if (Math.pow(a,2) + Math.pow(b, 2) == Math.pow(c, 2)) {
							System.out.println(a + " + " + b + " + " +
								c + " = " + num);
							return (a * b * c);
						}
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		long currTime = System.currentTimeMillis();
		System.out.println(pythagoreanTrip(1000));
		System.out.println("Time expired (ms): " + 
			(System.currentTimeMillis() - currTime));
	}
}