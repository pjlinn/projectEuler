/*
	Multiples of 3 and 5: Problem 1 Project Euler

	If we list all the natural numbers below 10 that are 
	multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of 
	these multiples is 23.

	Find the sum of all the multiples of 3 or 5 below 1000.
*/

// import java.io.*;
// import java.text.*;
import java.util.*;

public class Multiples {

	/*
		INCORRECT implementation -- double counts multiples of
		3 and 5
	*/
	public static int multiples(int x) {
		/*
			Setting up a sum variable to sum the list of multiples
			that I find. This is what will be returned
		*/
		int sum3 = 0;
		int sum5 = 0;	
		int sum = 0;
		/*
			I'm using arrays, but that is pretty stupid. Arrays
			are of fixed size, and I don't know what the size 
			should be. This should work for now, but I want to
			go back and add Sets or Maps or whatever so that I
			can dynamically build the set of value to sum.
		*/
		int[] multiplesOf3;
		int[] multiplesOf5;
		/*
			Here I am setting the size of each array equal to the
			value I am summing over. This is a really lazy thing
			to do, and is allocating way more space than I need.
		*/
		multiplesOf3 = new int[x];
		multiplesOf5 = new int[x];
		/*
			I'm going to use this for loo to build and append 
			multiples to my arrays. I'm going to determine if
			a value is a multiple and then add it.
		*/
		for (int i = 1; i < x; i++) {
			/*
				Super lazy and inefficient storage. I am just 
				moding the value of i to determine if it is a 
				multiple and then adding i at index i instead 
				of a sequential index because I will iterate 
				over the entire array anyway when summing

				Can I use the Ternary if/else here?
			*/
			if (i % 3 == 0) {
				multiplesOf3[i - 1] = i;
				// System.out.println(i);
			}

			if (i % 5 == 0) {
				multiplesOf5[i - 1] = i;
				// System.out.println(i);
			}
		}
		/*
			Can I use the For Each statement here?		
		*/
		for (int j = 0; j < x; j++) {
			sum3 += multiplesOf3[j];
			sum5 += multiplesOf5[j];
		}

		sum = sum3 + sum5;

		return sum;
	}
// ================================================================
	/*
		Correct implementation. I was counting multiples of 
		3 and 5 twice previously. So it would add 15, 30, 
		45, etc. in each loop. This implementation subtractrs
		a value if it's a multiple of 3 and 5 once, to make 
		up for the fact that it's added twice. 

		Probably not the most efficient code, but whatever. 
		This was fun.

	*/
	public static int multiplesv2(int x) {
		/*
			sum is what we will return

			y is a counter of multiples of 3

			z is  a counter of multiples of 5
		*/
		int sum = 0;
		int y = 0;
		int z = 0;
		/*
			We will count by threes until y is greater or
			equal to x. With each iteration we add the 
			multiple of 3 to the sum.

			If the multiple of 3 is also a multiple of 5 
			we subtract it after adding it. We should probably
			not even add it using an if else, but this works.
		*/
		while(y < x) {
			sum += y;

			if (y % 5 == 0) {
				sum -= y;
			}

			y += 3;
		}
		/*
			Here we add multiples of 5 until z is greter or
			equal to x. We don't subtract since we do that 
			above and we do want to count multiples of 3 and
			5, we just want to do it once. 
		*/
		while(z < x) {
			sum += z;
			z += 5;
		}

		return sum;
	}

	public static void main(String[] args) {
		/*
			Make it so we prompt the user for the bound
		*/

		int x;

		Scanner num = new Scanner(System.in);
		System.out.print("Please enter an integer x: ");
		x = num.nextInt();

		// System.out.println(multiples(x)); INCORRECT
		System.out.println(multiplesv2(x)); // **CORRECT
	}
}