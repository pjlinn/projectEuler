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
	1. It is limited to 1-9 pandigital numbers, which is 9! possibilities

	2. You have to multipliy them, so a lot of things can be eliminated 
		Test: 2 digit * 3 digit = 4 digit and 1 digit * 4 digit = 4 digit

	-----------------------------------

	Okay I did it. Once I had all the possible permutations using the 
	nested for loops I split each permutation into:
		2 digit * 3 digit = 4 digit & 1 digit * 4 digit = 4 digit
	Then I spit out the possbilities that worked.
*/

import java.util.ArrayList;

public class PandigitalProducts {

	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	private String permutation = "";
	/*
		Constructor
	*/
	public PandigitalProducts () {
		createArrayList(9);
	}
	/*
		Checks to see if the number is pandigital.
	*/
	public static Boolean pandigitalCheck() {
		Boolean isPandigital = true;

		return isPandigital;
	}
	/*
		One approach may be to create a list of all the possible 1-9
		pandigital possibilities and then figure out if they can be 
		broken into identities? 

		I think we should do this 9! is only 362,880
	*/
	public static void pandigitalNumbers() {

	}
	/*
		A method to create a set of permutations: currently is just a 
		brute force set of for loops. Not optimal.
	*/
	public void getPermutations() {
		int counter = 0;
		String multiplicand, multiplier, product;
		int iMultiplicand, iMultiplier, iProduct;

		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (j == i) { continue; }
				for (int k = 0; k < numbers.size(); k++) {
					if (k == i || k == j) { continue; }
					for (int l = 0; l < numbers.size(); l++) {
						if (l == i || l == j || l == k) { continue; }
						for (int m = 0; m < numbers.size(); m++) {
							if (m == i || m == j || m == k || m == l) { continue; }
							for (int n = 0; n < numbers.size(); n++) {
								if (n == i || n == j || n == k || n == l || n == m) { continue; }
								for (int p = 0; p < numbers.size(); p++) {
									if (p == i || p == j || p == k || p == l || p == m || p == n) { continue; }
									for (int q = 0; q < numbers.size(); q++) {
										if (q == i || q == j || q == k || q == l || q == m || q == n || q == p) { continue; }
										for (int r = 0; r < numbers.size(); r++) {
											if (r == i || r == j || r == k || r == l || r == m || r == n || r == p || r == q) { continue; }

											// 2 digits * 3 digits = 4 digits
											multiplier = Integer.toString(numbers.get(i)) + numbers.get(j);											
											multiplicand = Integer.toString(numbers.get(k)) + numbers.get(l) + numbers.get(m);
											product = Integer.toString(numbers.get(n)) + numbers.get(p) + + numbers.get(q) + numbers.get(r);

											iMultiplier = Integer.parseInt(multiplier);
											iMultiplicand = Integer.parseInt(multiplicand);
											iProduct = Integer.parseInt(product);

											if (iMultiplier * iMultiplicand == iProduct) {
												System.out.println(multiplier + " * " + multiplicand + " = " + product);
											}

											// 1 digit * 4 digit = 4 digit
											multiplier = Integer.toString(numbers.get(i)) ;											
											multiplicand = Integer.toString(numbers.get(j)) + numbers.get(k) + numbers.get(l) + numbers.get(m);
											product = Integer.toString(numbers.get(n)) + numbers.get(p) + + numbers.get(q) + numbers.get(r);

											iMultiplier = Integer.parseInt(multiplier);
											iMultiplicand = Integer.parseInt(multiplicand);
											iProduct = Integer.parseInt(product);

											if (iMultiplier * iMultiplicand == iProduct) {
												System.out.println(multiplier + " * " + multiplicand + " = " + product);
											}																	
										}
									}
								}
							}

						}
					} 
				}
			}
		}
	}
	/*
		Try to get permutations recursively. Currently this is nothing,
		I was just playing around with recursion.
	*/
	public String recursivePermutations(int elementIndex) {
		while (elementIndex >= 0) {
			permutation += numbers.get(elementIndex);

			elementIndex = elementIndex - 1;

			recursivePermutations(elementIndex);
		}
		return permutation;
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
		x.getPermutations();
		// System.out.println(x.recursivePermutations(2));


		ArrayList<Integer> tester = new ArrayList<Integer>();
		ArrayList<String> holder = new ArrayList<String>();

		tester.add(1); tester.add(2); tester.add(3);

		int a = tester.get(0);
		int b = tester.get(1);
		int c = tester.get(2);
		int counter = 0;
		// String permutation = "";

		// while (counter < 6) {
		// 	for (int i = 0; i < tester.size(); i++) {
		// 		permutation = permutation + tester.get(i);
		// 		tester.remove(i);
		// 		while (tester.isEmpty() != true) {
		// 			permutation += tester.get(0);
		// 			tester.remove(0);
		// 		}
		// 		System.out.println(permutation);
		// 		tester.add(1); tester.add(2); tester.add(3);
		// 		permutation = "";
		// 		counter++;
		// 	}
		// }
	}
}