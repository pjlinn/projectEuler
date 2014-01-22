/*
	Permutation method:
*/

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	// private static ArrayList<Integer> 

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

	private static void permutationGen(ArrayList<Integer> digits, int limit) {
		int swap = 1;
		while(limit < 4) {
			while(swap <= limit) {
				int holder1 = digits.get(digits.size() - swap);
				int holder2 = digits.get(digits.size() - swap - 1);

				digits.set(digits.size() - swap, holder2);
				digits.set(digits.size() - swap - 1, holder1);

				System.out.println(digits);
				swap++;
			}
			limit++;
			swap = 1;
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> digits =  new ArrayList<Integer>();
		digits.add(1); digits.add(2); digits.add(3); digits.add(4);
		// digits.add(5);

		// digits.set(0, digits.get(1));
		// digits.set(1, digits.get(0));

		permutationGen(digits, 2);
	}
}