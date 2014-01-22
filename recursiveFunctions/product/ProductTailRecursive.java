/*
	EFFECTS: returns the product of each element in list
			 one if the list is empty
*/

import java.util.ArrayList;

public class ProductTailRecursive {

	private static int product(ArrayList<Integer> list, int result) {
		result = (result == 0) ? 1 : result;
		if (list.size() == 0) {
			return result * 1;
		}
		result *= list.get(0);
		list.remove(0);
		return product(list, result);
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			list.add(i);	
		}

		System.out.println(product(list, 0));
	}
}