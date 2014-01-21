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

public class PandigitalMultiples {
	
	/*
		Permutation method
		Returns an ArrayList of possible permutations
	*/
	private static ArrayList<Integer> pandigitalGenerator(ArrayList<Integer> digits) {
		ArrayList<Integer> pandigitalNums = new ArrayList<Integer>();
		
		int holder1, holder2;
		int size = digits.size();
		int counter = 0;
		String startingNum = "";
		String concatenatedNum = "";

		for (Integer digit : digits) {
			startingNum += Integer.toString(digit);
		}

		while(counter < 24) {
			size = digits.size();
			while(size - 2 >= 0) {
				concatenatedNum = "";
				holder1 = digits.get(size - 1);
				holder2 = digits.get(size - 2);

				digits.set(size - 1, holder2);
				digits.set(size - 2, holder1);

				for (Integer digit : digits) {
					concatenatedNum = concatenatedNum + "" + digit;
				}

				pandigitalNums.add(Integer.parseInt(concatenatedNum));

				size--;
				counter++;
			}
		}
		System.out.println(counter);
		return pandigitalNums;
	}

	public static void main(String[] args) {
		ArrayList<Integer> num = new ArrayList<Integer>();
		for (int i = 1; i < 5; i++) {
			num.add(i);
		}
		System.out.println(pandigitalGenerator(num));
	}
}