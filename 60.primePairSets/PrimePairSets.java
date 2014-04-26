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

	public PrimePairSets() {
		alPrimes.add(2.); alPrimes.add(3.); alPrimes.add(5.);
		hsPrimes.add(2.); hsPrimes.add(3.); hsPrimes.add(5.);
	}

	// Alternate prime generator: super slow not useful
	private void alternateGen(double ceiling) {

		for (double i = 7.; i <= ceiling; i = i + 2.) {
			if (i % 3 == 0 || i % 5 == 0) {
				continue;
			}
			for (Double prime : alPrimes) {
				if (prime > i / 2.) {
					alPrimes.add(i);
					break;
				} else if (i % prime == 0) {
					break;
				}
			}
		}
	}


	// Generate a list of primes to some arbitrary limit
	private void generatePrimes(double ceiling) {

		for (double i = 3; i < ceiling; i = i + 2) {
			if (i % 3 == 0 || i % 5 == 0) {
				continue;
			}
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

		for (double i = 3; i <= Math.sqrt(num); i = i + 2) {
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
		for (int a = 0; a < alPrimes.size(); a++) {
			possiblePrimes.add(alPrimes.get(a));
			for (int b = a + 1; b < alPrimes.size(); b++) {
				for (int i = possiblePrimes.size() - 1; i >= 0; i--) {
					DecimalFormat f = new DecimalFormat("##");

					String sPrime = f.format(alPrimes.get(b));
					String sIndex = f.format(possiblePrimes.get(i));

					String sConcat1 = sPrime + sIndex;
					String sConcat2 = sIndex + sPrime;

					double iConcat1 = Double.parseDouble(sConcat1);
					double iConcat2 = Double.parseDouble(sConcat2);
					
					if (possiblePrimes.size() == 4) {
						System.out.println(possiblePrimes);	
					}

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
						possiblePrimes.add(alPrimes.get(b));
						counter = 0;
					}
				}
			}
			possiblePrimes.clear();
		}
		return possiblePrimes;
	}

	public static void main(String[] args) {
		
		final double ceiling = 1000000000.;

		PrimePairSets x = new PrimePairSets();
		
		x.generatePrimes(ceiling);

		// x.alternateGen(ceiling);

		System.out.println(primePairFilter(x.alPrimes, x.hsPrimes));
		// System.out.println(x.hsPrimes.contains(6737));

		

		// System.out.println(ceiling);
		// System.out.println(f.format(ceiling));

		// System.out.println(x.alPrimes);
	}
}