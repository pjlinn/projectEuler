/*
	Take the number 192 and multiply it by each of 1, 2, and 3:

	192 × 1 = 192
	192 × 2 = 384
	192 × 3 = 576
	By concatenating each product we get the 1 to 9 pandigital, 192384576. 
	We will call 192384576 the concatenated product of 192 and (1,2,3)

	The same can be achieved by starting with 9 and multiplying by 
	1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the 
	concatenated product of 9 and (1,2,3,4,5).

	What is the largest 1 to 9 pandigital 9-digit number that can be formed 
	as the concatenated product of an integer with (1,2, ... , n) where n > 1?
*/

import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class PandigitalMultiples {
	
	private final ArrayList<String> digits = new ArrayList<String>();
	private ArrayList<String> permutations = new ArrayList<String>();

	public PandigitalMultiples() {
		for (int i = 1; i < 10; i++) {
			digits.add(Integer.toString(i));
		}
	}

	/*
		Permutation method
		Returns an ArrayList of possible permutations using the lowerbound of
		918273645 from the problem spec as n = 5 and 918273645.

		Basically this method works by getting the passed ArrayList to 
		take a foreach of a set of numbers, a for each or remaining 
		numbers, a for each of remaining number...until no numbers remain,
		then we add that to another ArrayList if it's above the lower bound.

		I tried to use an actualy for each loop, but it doesn't like you
		adding and removing things as you go.
	*/
	private ArrayList<String> pandigitalGenerator(ArrayList<String> digits, 
		String output) {
		if (digits.size() == 0) {
			if (Integer.parseInt(output) >= 918273645) {
				permutations.add(output);	
			}
			return permutations;
		}
		/*
			Take a digit in an ArrayList, pass the rest of the list,
			re-insert what you took out, and then take the next element.

			Make sure to reset the output to what you start with before
			calling any more funcitons.
		*/
		for (int i = digits.size() - 1; i >= 0 ; i--) {
			String temp = digits.get(i);
			String tempOutput = output;
			output += temp;
			digits.remove(i);
			pandigitalGenerator(digits, output);
			digits.add(i, temp);
			output = tempOutput;
		}
		return permutations;
	}

	public static void main(String[] args) {
		PandigitalMultiples x = new PandigitalMultiples();
		x.pandigitalGenerator(x.digits, "");
		Collections.sort(x.permutations);
		System.out.println(x.permutations);
	}
}