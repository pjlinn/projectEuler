/*
	By starting at the top of the triangle below and 
	moving to adjacent numbers on the row below, the maximum 
	total from top to bottom is 23.

	3
	7 4
	2 4 6
	8 5 9 3

	That is, 3 + 7 + 4 + 9 = 23.

	Find the maximum total from top to bottom of the triangle below:

	75
	95 64
	17 47 82
	18 35 87 10
	20 04 82 47 65
	19 01 23 75 03 34
	88 02 77 73 07 63 67
	99 65 04 28 06 16 70 92
	41 41 26 56 83 40 80 70 33
	41 48 72 33 47 32 37 16 94 29
	53 71 44 65 25 43 91 52 97 51 14
	70 11 33 28 77 73 17 78 39 68 17 57
	91 71 52 38 17 14 91 43 58 50 27 29 48
	63 66 04 68 89 53 67 30 73 16 69 87 40 31
	04 62 98 27 23 09 70 98 73 93 38 53 60 04 23

	NOTE: As there are only 16384 routes, it is possible to solve 
	this problem by trying every route. However, Problem 67, is the 
	same challenge with a triangle containing one-hundred rows; it cannot 
	be solved by brute force, and requires a clever method! ;o)	
*/

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
import java.util.HashMap;

/*
	Solved. Used the technique were you take the max from the bottom up,
	Should work for 100 rows. I never actually got brute force to work
	though.
*/

public class MaxPathSum {

	// don't actually use this, switched to ArrayList
	public static int[] stringToArray(String num) {
		
		int stringLength = num.length();
		int arraySize = 1 + (stringLength - 2) / 3;
		String digit = "";
		int counter = 0;

		int [] intArray = new int[arraySize];

		for (int i = 0; i < stringLength; i = i + 3) {
			digit = num.substring(i, i+2);
			intArray[counter] = Integer.parseInt(digit);
			counter++;
		}

		return intArray;
	}

	public static ArrayList<Integer> stringToArrayList(String num) {
		
		int stringLength = num.length();
		String digit = "";

		ArrayList<Integer> intArray = new ArrayList<Integer>();

		for (int i = 0; i < stringLength; i = i + 3) {
			digit = num.substring(i, i + 2);
			intArray.add(Integer.parseInt(digit));
		}

		return intArray;
	}

	public static ArrayList<Integer> deepCopy(ArrayList<Integer> source) {
		
		ArrayList<Integer> copy = new ArrayList<Integer>();

		for (Integer x : source) {
			copy.add(x);
		}

		return copy;
	}

	public static void main(String[] args) {
	
		// provided data
		String t1 = "75";
		String t2 = "95 64";
		String t3 = "17 47 82";
		String t4 = "18 35 87 10";
		String t5 = "20 04 82 47 65";
		String t6 = "19 01 23 75 03 34";
		String t7 = "88 02 77 73 07 63 67";
		String t8 = "99 65 04 28 06 16 70 92";
		String t9 = "41 41 26 56 83 40 80 70 33";
		String t10 = "41 48 72 33 47 32 37 16 94 29";
		String t11 = "53 71 44 65 25 43 91 52 97 51 14";
		String t12 = "70 11 33 28 77 73 17 78 39 68 17 57";
		String t13 = "91 71 52 38 17 14 91 43 58 50 27 29 48";
		String t14 = "63 66 04 68 89 53 67 30 73 16 69 87 40 31";
		String t15 = "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";

		// test triangle
		String test1 = "03";
		String test2 = "07 04";
		String test3 = "02 04 06";
		String test4 = "08 05 09 03";

		// never used these, used ArrayList instead
		int[] arrayT1 = stringToArray(t1);
		int[] arrayT2 = stringToArray(t2);
		int[] arrayT3 = stringToArray(t3);
		int[] arrayT4 = stringToArray(t4);
		int[] arrayT5 = stringToArray(t5);
		int[] arrayT6 = stringToArray(t6);
		int[] arrayT7 = stringToArray(t7);
		int[] arrayT8 = stringToArray(t8);
		int[] arrayT9 = stringToArray(t9);
		int[] arrayT10 = stringToArray(t10);
		int[] arrayT11 = stringToArray(t11);
		int[] arrayT12 = stringToArray(t12);
		int[] arrayT13 = stringToArray(t13);
		int[] arrayT14 = stringToArray(t14);
		int[] arrayT15 = stringToArray(t15);

		ArrayList<Integer> aList1 = stringToArrayList(t1);
		ArrayList<Integer> aList2 = stringToArrayList(t2);
		ArrayList<Integer> aList3 = stringToArrayList(t3);
		ArrayList<Integer> aList4 = stringToArrayList(t4);
		ArrayList<Integer> aList5 = stringToArrayList(t5);
		ArrayList<Integer> aList6 = stringToArrayList(t6);
		ArrayList<Integer> aList7 = stringToArrayList(t7);
		ArrayList<Integer> aList8 = stringToArrayList(t8);
		ArrayList<Integer> aList9 = stringToArrayList(t9);
		ArrayList<Integer> aList10 = stringToArrayList(t10);
		ArrayList<Integer> aList11 = stringToArrayList(t11);
		ArrayList<Integer> aList12 = stringToArrayList(t12);
		ArrayList<Integer> aList13 = stringToArrayList(t13);
		ArrayList<Integer> aList14 = stringToArrayList(t14);
		ArrayList<Integer> aList15 = stringToArrayList(t15);

		ArrayList<Integer> aTest1 = stringToArrayList(test1);
		ArrayList<Integer> aTest2 = stringToArrayList(test2);
		ArrayList<Integer> aTest3 = stringToArrayList(test3);
		ArrayList<Integer> aTest4 = stringToArrayList(test4);

		ArrayList<ArrayList> allLists = new ArrayList<ArrayList>();
		ArrayList<Integer> holder1 = new ArrayList<Integer>();

		// allLists.add(aTest1); allLists.add(aTest2); allLists.add(aTest3);
		// allLists.add(aTest4);

		allLists.add(aList1); allLists.add(aList2); allLists.add(aList3);
		allLists.add(aList4); allLists.add(aList5); allLists.add(aList6);
		allLists.add(aList7); allLists.add(aList8); allLists.add(aList9);
		allLists.add(aList10); allLists.add(aList11); allLists.add(aList12);
		allLists.add(aList13); allLists.add(aList14); allLists.add(aList15);

/*
					Brute Force Attempt: Failed btw...
========================================================================
		int power = 1;
		int index = 0;
		int constant = 1;
		// starts with the second to last list
		for (int i = allLists.size() - 2; i >= 0; i--) {
			holder1 = deepCopy(allLists.get(i));
			allLists.get(i).clear();
			// starts with the first element in the list
			for (int j = 0; j < holder1.size(); j = j + 1) {
				// add that element to the next n numbers
				for (int k = 0; k < Math.pow(2, power); k++) {
					int sum = (Integer)holder1.get(j) + (Integer)allLists.get(i+1).get(k);
					allLists.get(i).add(sum);
					System.out.println(sum);
				}
				System.out.println("break");
				constant = 0;
				index ++;
			}
			power++;
			index = 0;
			constant = 1;
		}
========================================================================
*/

/*
	Different approach, going to a binary tree approach taking the
	largest value from the bottom up: WORKED
========================================================================
*/
	int sum = 0;
	int hold = 0;
	// start with second to last list and work up
	for (int i = allLists.size() - 2; i >= 0; i--) {
		// copy the list into another arraylist
		holder1 = deepCopy(allLists.get(i));
		// clear the original to be rebuilt with new values
		allLists.get(i).clear();
		// start with the first index in the copied list
		for (int j = 0; j < holder1.size(); j++) {
			// add it to the index and index + 1 of the row below
			for (int k = j; k < j + 2; k++) {
				hold = (int) holder1.get(j) + (int) allLists.get(i + 1).get(k);
				sum = (hold > sum) ? hold : sum;
			}
			// add the greater of the 2 options to be used in the next iteration, reset sum
			allLists.get(i).add(sum);
			sum = 0;
		}
	}
// ========================================================================

	// prints the top row, with the single greatest sum
	System.out.println(aList1);
	}
}