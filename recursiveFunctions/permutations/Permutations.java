/*
	Permutation methods
*/

import java.util.ArrayList;

public class Permutations {
	/*
		Permutation method
	*/
	static public ArrayList<Integer> permutationGenerator(String num) {
		ArrayList<Integer> permutations = new ArrayList<Integer>();
		String holder1, holder2;
		int length = num.length();

		if (length == 1) {
			permutations.add(num);
			return permutations;
		}

		while(permutation.equal(num)) {
			holder1 = num.substring(length);
			holder2 = num.substring(length - 1, length);
			num
		}

		return permutations;
	}

	public static void main(String[] args) {
		
	}
}