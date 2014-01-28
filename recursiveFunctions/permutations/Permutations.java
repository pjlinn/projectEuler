/*
	Permutation method:
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Permutations {		

	static ArrayList<String> permutations = new ArrayList<String>();
	static HashMap<String, String> hashPermutations = new HashMap<String, String>();

	// tried just using the for each iterator, but was getting concurrent modification errors
	// errors for adding and removing items.
	private static ArrayList<String> permutationGenerator(ArrayList<String> number, 
		String output) {
		// If the ArrayList passed is empty, add the output string, and return
		if (number.size() == 0) { hashPermutations.put(output, null); return permutations;}
		for (int i = number.size() - 1; i >= 0; i--) {
			String outputTemp = output;
			String temp = number.get(i);
			output += temp;
			number.remove(i);
			permutationGenerator(number, output);
			number.add(i, temp);

			output = outputTemp;
		}
		return permutations;
	}

	/* 
		Same basic implementation of permutationGenerator, except this time
		I"m using a different data structure, to simplify the implementation
		without throwing the concurrent modification error.

		Try concurrent HashMap -- Doesn't seem to work
	*/
		private static HashMap<String, String> concurrentPermutationGenerator(ConcurrentHashMap<String, String> number,
			String output, String extraKey) {
			String outputTemp = "";
			if (number.containsKey(extraKey)) {
				number.remove(extraKey);
			}
			System.out.println(number);
			if (number.isEmpty()) {
				hashPermutations.put(output, "");
				return hashPermutations;
			}
			for (Map.Entry<String,String> entry : number.entrySet()) {
				outputTemp = output;
				output += entry.getKey();
				concurrentPermutationGenerator(number, output, entry.getKey());
				output = outputTemp;
			}
			return hashPermutations;
		}


	public static void main(String[] args) {

		// Permutations x = new Permutations();
		// System.out.println(x.startingNumber);

		// digits.set(0, digits.get(1));
		// digits.set(1, digits.get(0));
		ArrayList<String> digits = new ArrayList<String>();
		for (int i = 1; i < 10; i++) { digits.add(Integer.toString(i)); }
		ConcurrentHashMap<String, String> concurrentHashPermutations = new ConcurrentHashMap<String, String>();
		for (int i = 1; i < 4; i++) { concurrentHashPermutations.put(Integer.toString(i), ""); }
		// permutationGenerator(digits, "");
		for (Map.Entry<String, String> entry : concurrentHashPermutations.entrySet()) {
			// System.out.println(entry.getKey());
		}
		System.out.println(concurrentPermutationGenerator(concurrentHashPermutations, "", ""));
	}
}