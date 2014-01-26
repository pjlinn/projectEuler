/*
	Permutation method:
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.*;

public class Permutations {		

	static ArrayList<String> permutations = new ArrayList<String>();
	static HashMap<String, String> hashPermutations = new HashMap<String, String>();

/*
	private static ArrayList<String> permutationGen
	(ArrayList<Integer> digits) {
		final ArrayList<String> permuations = new ArrayList<String>();

		String startingNumber = "";
		String permutation = "";

		for (Integer digit : digits) {
			startingNumber += Integer.toString(digit);
		}

		while(!startingNumber.equals(permutation)) {
			for (int i = 1; i < digits.size() - 1; i++) {
				int holder1 = digits.get(i);
				int holder2 = digits.get(i + 1);

				digits.set(i, holder2);
				digits.set(i + 1, holder1);

				for (Integer digit : digits) {
					permutation += Integer.toString(digit);
				}
				permuations.add(permutation);
				if (permutation.equals(startingNumber)) {
					break;
				} else {
					permutation = "";
				}
			}
		}
		return permuations;
	}
*/

/*
	limit = 0;

	if swapNum = original num then limit++

	method(limit, ArrayList digits)
*/

/*
	private static void permutationGen(ArrayList<Integer> digits) {
		int length = digits.size();
		int lastIndex = digits.get(length - 1);
		int holder1, holder2;
		int secondToLastIndex = digits.get(length - 2);
		String startingNumber = "";
		String permutation = "";
		int counter = length - 1;
		int limit = 2;

		for (Integer digit : digits) {
			startingNumber += Integer.toString(digit);
		}

		while(!startingNumber.equals(permutation)) {
			while(counter < limit){
				holder1 = counter;
				holder2 = counter - 1;

				digits.set(counter, holder2);
				digits.set(counter - 1, holder1);
			}
		}

		System.out.println(digits);
	}
*/
/*
	private ArrayList<Integer> recursivePermutation(ArrayList<Integer> digits,
		int upperBound) {
		
		int holder1 = 0;
		int holder2 = 0;
		int shift = 0;

		String currentNumber = "";

		ArrayList<Integer> previousList = new ArrayList<Integer>();
		
		for (Integer digit : digits) { currentNumber += Integer.toString(digit); }
		
		if (permuations.size() == 24) { return permuations; }
		else {
			
			for (int i = 0; i < digits.size(); i++) {
				previousList.set(i, digits.get(i));
			}

			holder1.get(digits.size() - shift);
			holder2.get(digits.size() - shift - 1);

			digits.set(digits.size() - 1, holder2);
			digits.set(digits.size() - 2, holder1);

			for (Integer digit : digits) { 
				currentNumber += Integer.toString(digit); 
			}

			if (currentNumber.equals(startingNumber)) {
				recursivePermutation(previousList, upperBound++);
			}

		}
	}
*/
/*
	// Try counting down
	private static void permutationGen(ArrayList<Integer> digits) {
		int iterations = digits.size() - 1;
		int counter = 0;
		int repeat = 0;
		int iterationCounter = 1;
		int swap = 1;
		int upperBound = 1;
		int repeats = 1;

		while(counter < 1) { 
			while(upperBound < digits.size()) {
				swap = upperBound;
					while(repeat < repeats) {
						while(swap >= 1) {
							System.out.println(digits);

							int index = digits.size() - swap;
							int prevIndex = index - 1;

							int holder1 = digits.get(index);
							int holder2 = digits.get(prevIndex);

							digits.set(index, holder2);
							digits.set(prevIndex, holder1);

							swap--;
						}
						repeat++;
					}
				upperBound++;
				repeat = 0;
				repeats++;
			}
			counter++;
			upperBound = 1;
		}
	}
*/
/*
	private static void generator(ArrayList<Integer> digits, int repeats,
		int swap) {
		
		int counter = 0;

		while(counter < 3) {
			int x = repeats;
			while(x > 0) {
				int y = swap;
				while(y > 0) {
					System.out.println(digits);

					int index = digits.size() - y;
					int prevIndex = index - 1;
					int holder1 = digits.get(index);
					int holder2 = digits.get(prevIndex);

					digits.set(index, holder2);
					digits.set(prevIndex, holder1);

					y--;
				}
				x--;
			}
			repeats++;
			swap++;
			counter++;
		}
	}
*/

	private static ArrayList<String> generator(ArrayList<String> number, 
		String output) {
		// System.out.println(number);
		if (number.size() == 0) { hashPermutations.put(output, null); return permutations;}
		for (int i = number.size() - 1; i >= 0; i--) {
			String outputTemp = output;
			String temp = number.get(i);
			output += temp;
			number.remove(i);
			generator(number, output);
			number.add(i, temp);
			// Collections.sort(number);
			output = outputTemp;
		}
		return permutations;
	}

	public static void main(String[] args) {

		// Permutations x = new Permutations();
		// System.out.println(x.startingNumber);

		// digits.set(0, digits.get(1));
		// digits.set(1, digits.get(0));
		ArrayList<String> digits = new ArrayList<String>();
		for (int i = 1; i < 10; i++) { digits.add(Integer.toString(i)); }
		generator(digits, "");
		System.out.println(hashPermutations.size());
	}
}