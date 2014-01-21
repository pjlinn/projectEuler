/*
	Permutation method:
*/

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	private static ArrayList<String> permutationGen(ArrayList<Integer> digits) {
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

	public static void main(String[] args) {
		ArrayList<Integer> digits =  new ArrayList<Integer>();
		digits.add(1); digits.add(2); digits.add(3); digits.add(4);
		digits.add(5);
		System.out.println(permutationGen(digits));
	}
}