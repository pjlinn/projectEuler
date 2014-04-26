/*
	The primes 3, 7, 109, and 673, are quite remarkable. By taking 
	any two primes and concatenating them in any order the result 
	will always be prime. For example, taking 7 and 109, both 7109 
	and 1097 are prime. The sum of these four primes, 792, represents 
	the lowest sum for a set of four primes with this property.

	Find the lowest sum for a set of five primes for which any two 
	primes concatenate to produce another prime.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.text.DecimalFormat;

public class PrimePairSets {

	ArrayList<Double> alPrimes = new ArrayList<Double>();
	HashSet<Double> hsPrimes = new HashSet<Double>();

	// Generate a list of primes to some arbitrary limit
	private void generatePrimes(double ceiling) {

		for (double i = 2; i < ceiling; i++) {
			if (isPrime(i)) {
				alPrimes.add(i);
				hsPrimes.add(i);
			}
		}
	}

	// isPrime method, don't want 2 or 5 in our set for this problem
	private boolean isPrime(double num) {
		if (num == 2 || num == 5 || num == 0 || num == 0) {
			return false;
		}

		for (double i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}

	// Checks if pairs of primes are prime, filters out ones that aren't
	private static ArrayList<Double> primePairFilter(ArrayList<Double> alPrimes, 
		HashSet<Double> hsPrimes) {

		ArrayList<Double> possiblePrimes = new ArrayList<Double>(); 

		double counter = 0;
		for (Double possiblePrime : alPrimes ) {
			possiblePrimes.add(possiblePrime);
			for (Double prime : alPrimes) {
				for (int i = possiblePrimes.size() - 1; i >= 0; i--) {
					DecimalFormat f = new DecimalFormat("##");

					String sPrime = f.format(prime);
					String sIndex = f.format(possiblePrimes.get(i));

					String sConcat1 = sPrime + sIndex;
					String sConcat2 = sIndex + sPrime;

					double iConcat1 = Double.parseDouble(sConcat1);
					double iConcat2 = Double.parseDouble(sConcat2);
					
					System.out.println(possiblePrimes.size());

					if (possiblePrimes.size() == 5) {
						return possiblePrimes;
					}

					if (hsPrimes.contains(iConcat1) && hsPrimes.contains(iConcat2)) {
						counter++;
					} else {
						counter = 0;
						break;
					}

					if (counter == possiblePrimes.size()) {
						possiblePrimes.add(prime);
						counter = 0;
					}
				}
			}
			possiblePrimes.clear();
		}
		return possiblePrimes;
	}

	public static void main(String[] args) {
		
		final double ceiling = 1000000.;

		PrimePairSets x = new PrimePairSets();
		
		x.generatePrimes(ceiling);

		System.out.println(primePairFilter(x.alPrimes, x.hsPrimes));
		// System.out.println(x.hsPrimes.contains(6737));

		

		// System.out.println(ceiling);
		// System.out.println(f.format(ceiling));

		// System.out.println(x.alPrimes);
	}
}