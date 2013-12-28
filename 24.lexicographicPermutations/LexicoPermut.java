/*
	A permutation is an ordered arrangement of objects. 
	For example, 3124 is one possible permutation of the digits 
	1, 2, 3 and 4. If all of the permutations are listed numerically 
	or alphabetically, we call it lexicographic order. The lexicographic 
	permutations of 0, 1 and 2 are:

	012   021   102   120   201   210

	What is the millionth lexicographic permutation of the digits 
	0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

	-------
	I noticed a pattern in that each digit accounted for x amount of 
	the possible permutations. So of the 10! permutations, the first
	10!/10 started with 0 followed by the next group starting with 1,
	2,3,...,9.

	In the for loop, which probably could be recursive, I determine 
	how many times the first group divides into the goal (in this case
	the 1,000,000th digit). The number of times provides the index, and 
	subsequently the digit used. So this digit is removed from the array
	and added to another to build that final number. 

*/


import java.lang.Math;
import java.util.ArrayList;

public class LexicoPermut {
	
	public static void main(String[] args) {
	
		ArrayList<Integer> value = new ArrayList<Integer>();
		ArrayList<Integer> array = new ArrayList<Integer>(); //0-9

		int sum = 0;
		int permutations = 3628800; // 10!
		int divider = 0;
		int goal = 999999; // ************The goal is 999999 not 1,000,000
		int holder = 0;

		for (int i = 0; i < 10; i++) {
			array.add(i);
		}

		for (int j = 0; j < 10; j++) {
			divider = permutations / array.size();	// divider = 3628800 / 10 -> 362880 / 9 -> ...
			System.out.println("divider: " + divider + " permutations " + permutations + " arraySize " + array.size());
			holder = goal / divider; 				// holder = 2 -> 
			System.out.println("holder" + holder);
			goal = goal - holder * divider;			// goal = 274240 -> 
			value.add(array.get(holder));			// add index holder
			array.remove(holder);					// remove index holder, decrease size by 1
			permutations = divider;
		}

		for (Integer a : value) {
			System.out.print(a);
		}
		System.out.println();
	}
}