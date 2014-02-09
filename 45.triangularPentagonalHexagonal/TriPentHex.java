/*
	Triangle, pentagonal, and hexagonal numbers are generated 
	by the following formulae:

	Triangle	 	Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
	Pentagonal	 	Pn=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
	Hexagonal	 	Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
	It can be verified that T285 = P165 = H143 = 40755.

	Find the next triangle number that is also pentagonal 
	and hexagonal.

	-------

	Really striaghtforward approach here. Generate 3 hashset, go
	through each triangle number and return the ones that are
	in both other sets. I had no clue how high I needed to go,
	just hard coded it instead of looping until I had 3 answers.
*/

import java.util.HashSet;

public class TriPentHex {
	// Generate a HashSet of hexagonal numbers up to a certain index
	private static HashSet<Double> generateHexSet(double nthIndex) {
		HashSet<Double> hexSet = new HashSet<Double>();
		for (double n = 1; n <= nthIndex; n++) {
			double result = n * (2 * n - 1) ;
			hexSet.add(result);
		}
		return hexSet;
	}
	// Generate a HashSet of pentagonal numbers up to a certain index
	private static HashSet<Double> generatePentSet(double nthIndex) {
		HashSet<Double> pentSet = new HashSet<Double>();
		for (double n = 1; n <= nthIndex; n++) {
			double result = n * (3*n - 1) / 2;
			pentSet.add(result);
		}
		return pentSet;
	}
	// Generate a HashSet of triangular numbers up to a certain index
	private static HashSet<Double> generateTriSet(double nthIndex) {
		HashSet<Double> triSet = new HashSet<Double>();
		for (double n = 1; n <= nthIndex; n++) {
			double result = n * (n + 1 ) / 2;
			triSet.add(result);
		}
		return triSet;
	}
	public static void main(String[] args) {
		double nthIndex = 100000; // Brute forcing the uppper limit
		HashSet<Double> triSet = new HashSet<Double>(generateTriSet(nthIndex));
		HashSet<Double> pentSet = new HashSet<Double>(generatePentSet(nthIndex));
		HashSet<Double> hexSet = new HashSet<Double>(generateHexSet(nthIndex));

		for (Double entry : triSet) {
			if (hexSet.contains(entry) && pentSet.contains(entry)) {
				System.out.println(entry);
			}
		}
	}
}