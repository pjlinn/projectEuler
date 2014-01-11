/*
	A class for creating a rotation method.
*/

import java.util.ArrayList;

public class Rotation {
	public static ArrayList<String> rotate(int num) {
		ArrayList<Integer> number = new ArrayList<Integer>(intLength(num));
		ArrayList<String> rotations = new ArrayList<String>();
		String rotation = "";
		int start, end;
		int counter = 0;

		while (!rotation.equals(Integer.toString(num))) {
			rotation = "";
			start = number.get(0);
			end = number.size();
			for (int i = 0; i < end - 1; i++) {
				number.set(i, number.get(i + 1));
			}
			number.set(end - 1, start);
			for (Integer digit : number) {
				rotation += digit + "";
			}
			rotations.add(rotation);
			counter++; 
		}
		return rotations;
	}
	/*
		Int length method
			Takes an int as an argument
			Returns an ArrayList with each digit of the int in its own
				index from 0 to n
			This ArrayList will be passed into the permutationGenerator
				to generate all the possible permutation of this int
	*/
	static public ArrayList<Integer> intLength(int num) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int divisor = 1;
		int digit;
		int newNum = num;
		// Determines starting divisor for the passed int
		while(num % divisor != num) {
			divisor = divisor * 10;
		}
		divisor = divisor / 10;
		// A quick loop to break off each digit and add it to the ArrayList
		while(divisor >= 1) {
			newNum = newNum % divisor;
			digit = num / divisor;
			digits.add(digit);
			divisor = divisor / 10;
			num = newNum;
		}
		return digits;
	}
	public static void main(String[] args) {
		System.out.println(rotate(197));
	}
}