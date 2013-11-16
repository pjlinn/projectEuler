/*
	A perfect number is a number for which the sum of its proper divisors 
	is exactly equal to the number. For example, the sum of the proper 
	divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 
	28 is a perfect number.

	A number n is called deficient if the sum of its proper divisors 
	is less than n and it is called abundant if this sum exceeds n.

	As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the 
	smallest number that can be written as the sum of two abundant numbers 
	is 24. By mathematical analysis, it can be shown that all integers 
	greater than 28123 can be written as the sum of two abundant numbers. 
	However, this upper limit cannot be reduced any further by analysis 
	even though it is known that the greatest number that cannot be 
	expressed as the sum of two abundant numbers is less than this limit.

	Find the sum of all the positive integers which cannot be written as 
	the sum of two abundant numbers.

	***
	It works but is absolutly butt ugly code and extremely inefficient...
	Mostly brute force, I should use sqrt(n) instead of n / 2 as an
	upper limit for one thing...

	Definitely want to revisit this one..
	***
*/

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.*;

public class NonAbundantSums {

	public static boolean isAbundant(int num) {

		boolean bIsAbundant = false;

		int iSum = 0;

		for (int i = 1; i <= num / 2; i++) {
			if (num % i == 0) {
				iSum += i;
			}
		}

		bIsAbundant = (iSum > num) ? true : false;

		return bIsAbundant;
	}

	public static void main(String[] args) {

		// all the numbers < 12 are automatically in
		int iSum = 0;
	
		ArrayList<Integer> abundantList = new ArrayList<Integer>();
		// ArrayList<Integer> sumList = new ArrayList<Integer>();
		Hashtable<Integer, Integer> sumList = new Hashtable<Integer, Integer>();

		for (int i = 1; i <= 28123; i++) {
			if (isAbundant(i)) {
				abundantList.add(i);
				// System.out.print(i + "\t");
			}
		}
		/*
			Create a new list that will add up all the different
			combinations of abundant numbers in the list.
		*/
		for (int j = 0; j < abundantList.size() - 1; j++) {
			for (int k = j; k < abundantList.size(); k++) {
				int num = abundantList.get(j) + abundantList.get(k);
				if (num < 28123) {
					sumList.put(num, 1);
				} else {
					break;
				}
				
			}
		}
		System.out.println(sumList.size());
		// for (Map.Entry<Integer, Integer> entry : sumList.entrySet()) {
		// 	System.out.print(entry.getKey() + "\t");
		// }
		/*
			Check to see if the collection has the following key, if
			not add it.
		*/
		for (int l = 1; l < 28123; l++) {
			if (sumList.containsKey(l) == false) {
				iSum += l;
			}
		}
		System.out.println(iSum);
		// System.out.println(abundantList.size());
		/*
			Determine the "prime" numbers within the list - i.e. the abundant 
			numbers	that aren't factors of other abundant numbers.
		*/
		// for (int j = 0; j < abundantList.size() - 1; j++) {
		// 	for (int k = j + 1; k < abundantList.size(); k++) {
		// 		if (abundantList.get(k) % abundantList.get(j) == 0) {
		// 			abundantList.remove(k);
		// 			// System.out.println(k);
		// 		}
		// 	}
		// }
		// System.out.println(abundantList.size());
		// for (Integer x : abundantList) {
		// 	System.out.print(x + "\t");
		// }
		/*
			Now I want to see if numbers are factors of the numbers in the
			condensed list.

			I'm doing it wrong, it's just the sum of 2, not multiples...
		*/
		// for (int l = 1; l < 28123; l++) {
		// 	boolean bIsDivisible = false;
		// 	for (int m = 0; m < abundantList.size(); m++) {
		// 		if (abundantList.get(m) > l) {
		// 			break;
		// 		} else {
		// 			if (l % abundantList.get(m) == 0) {
		// 				bIsDivisible = false;
		// 				break;
		// 			} else {
		// 				bIsDivisible = true;
		// 			}
		// 		}
		// 	}
		// 	if (bIsDivisible == true) {
		// 		iSum += l;
		// 		System.out.print(l + "\t");
		// 	}
		// }
		// System.out.println(iSum);
	}
}