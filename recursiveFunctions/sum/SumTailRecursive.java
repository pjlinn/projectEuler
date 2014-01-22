/*
	Effects: returns the sume of each element in list
			 zero if list is empty.
*/

import java.util.ArrayList;

public class SumTailRecursive {
	
	private static int sum(ArrayList<Integer> list, int result) {
		if (list.size() == 0) {
			return result;
		}
		
		result += list.get(0);
		list.remove(0);

		return(sum(list, result));
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < 5; i++) {
			list.add(i);	
		}

		System.out.println(sum(list, 0));
	}
}