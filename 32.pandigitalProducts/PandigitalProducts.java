/*
	We shall say that an n-digit number is pandigital if it makes use of 
	all the digits 1 to n exactly once; for example, the 5-digit number, 
	15234, is 1 through 5 pandigital.

	The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing 
	multiplicand, multiplier, and product is 1 through 9 pandigital.

	Find the sum of all products whose multiplicand/multiplier/product 
	identity can be written as a 1 through 9 pandigital.

	HINT: Some products can be obtained in more than one way so be sure 
	to only include it once in your sum.

	Things to note:
	-----------------------------------
	1. It is limited to 1-9 pandigital numbers, which all together I think
	should equal 9! possibilities

	2. You have to multipliy them, so a lot of things can be eliminated 
	if I end up just looping through.
		=> You only have 9 digits to work with, so the:
		multiplier + multiplicand + product == 9 digits
*/

import java.util.ArrayList;

public class PandigitalProducts {

	private ArrayList<Integer> numbers = new ArrayList<Integer>();

	/*
		Checks to see if the number is pandigital. Just the openning
		salvo to figuring out how to do this...
	*/
	public static Boolean pandigitalCheck() {
		Boolean isPandigital = true;

		return isPandigital;
	}
	/*
		One approach may be to create a list of all the possible 1-9
		pandigital possibilities and then figure out if they can be 
		broken into identities? 

		I do not think this is a good approach however...


		I think we should do this 9! is only 362,880
	*/
	public static void pandigitalNumbers() {

	}
	/*
		A method to create a set of permutations

		I want to create all possible permutations, possible approaches:
			1. Hold the digits in an ArrayList and remove them upon
				concatenation. Then somehow alter how I add them. Maybe I
				add the first index and make all possible permutations with
				that index. Then I switch the numbers in that index with the
				next and re-run? 
				while(numbers is not empty)
	*/
	public void getPermutations() {

		int counter = 0;

		createArrayList(3);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == i) {
					continue;
				}
				for (int k = 0; k < 3; k++) {
					if (k == i || k == j) {
						continue;
					}
					System.out.println(Integer.toString(numbers.get(i)) + numbers.get(j) + numbers.get(k));
					counter++;
				}
			}
		}
	}
	/*
		Create an ArrayList of specified digits
	*/
	public ArrayList<Integer> createArrayList(int num) {
		for (int i = 1; i <= num; i++ ) {
			numbers.add(i);
		}
		return numbers;
	}
	/*
		Main method
	*/
	public static void main(String[] args) {
		// System.out.println("Hello World");
		// System.out.println(pandigitalCheck());

		PandigitalProducts x = new PandigitalProducts();
		// x.getPermutations();


		ArrayList<Integer> tester = new ArrayList<Integer>();
		ArrayList<String> holder = new ArrayList<String>();

		tester.add(1); tester.add(2); tester.add(3);

		int a = tester.get(0);
		int b = tester.get(1);
		int c = tester.get(2);
		int counter = 0;
		String permutation = "";

		while (counter < 6) {
			for (int i = 0; i < tester.size(); i++) {
				permutation = permutation + tester.get(i);
				tester.remove(i);
				while (tester.isEmpty() != true) {
					permutation += tester.get(0);
					tester.remove(0);
				}
				System.out.println(permutation);
				tester.add(1); tester.add(2); tester.add(3);
				permutation = "";
				counter++;
			}
		}
	}
}