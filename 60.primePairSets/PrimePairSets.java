/*
	The primes 3, 7, 109, and 673, are quite remarkable. By taking 
	any two primes and concatenating them in any order the result 
	will always be prime. For example, taking 7 and 109, both 7109 
	and 1097 are prime. The sum of these four primes, 792, represents 
	the lowest sum for a set of four primes with this property.

	Find the lowest sum for a set of five primes for which any two 
	primes concatenate to produce another prime.


	----------

	Was finally able to pull off. It was important to test each 
	concatenated number for being prime rather than hoping it 
	was in the list. My logic had been wrong, otherwise it wouldn't
	have taken so long to sovle this problem...Oh well.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.text.DecimalFormat;

import java.math.BigInteger;

public class PrimePairSets {

	ArrayList<Double> alPrimes = new ArrayList<Double>();
	HashSet<Double> hsPrimes = new HashSet<Double>();

	public PrimePairSets() {
		alPrimes.add(2.); 
		alPrimes.add(3.); alPrimes.add(5.);
		hsPrimes.add(2.); 
		hsPrimes.add(3.); hsPrimes.add(5.);
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

		if (num % 2 == 0) {
			return false;
		}

		for (double i = 3; i <= Math.sqrt(num); i = i + 2) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}

	// isPrime method STATIC, don't want 2 or 5 in our set for this problem
	private static boolean isPrimeStatic(double num) {
		if (num == 2 || num == 5 || num == 0 || num == 0) {
			return false;
		}

		if (num % 2 == 0) {
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

		System.out.println("start");
		// Holds the set of primes that fit the desired property
		ArrayList<Double> possiblePrimes = new ArrayList<Double>(); 
		// Keeps track of how many of the possible primes meet the property with a given prime
		double counter = 0;
		// Start with the first prime
		for (int a = 0; a < alPrimes.size(); a++) {
			// Add it to the possible primes
			possiblePrimes.add(alPrimes.get(a));
			// Names the loop
			outerloop:
			// I had it! But my logic was screwing me, originally had b = a + 1...
			// Start at the beginning of the list of primes
			for (int b = 0; b < alPrimes.size(); b++) {
				// Grab the prime at the end of possible primes
				for (int i = possiblePrimes.size() - 1; i >= 0; i--) {
					// Get rid of the decimals for concatenating strings, sets the format
					DecimalFormat f = new DecimalFormat("##");
					// Gets rid of the decimals
					String sPrime = f.format(alPrimes.get(b));
					String sIndex = f.format(possiblePrimes.get(i));
					// Concatenate the prime from possible primes with the one from the list of primes
					String sConcat1 = sPrime + sIndex;
					String sConcat2 = sIndex + sPrime;

					double iConcat1 = Double.parseDouble(sConcat1);
					double iConcat2 = Double.parseDouble(sConcat2);

					double current = alPrimes.get(b);
					double sum = 0;
					// Sums the possible primes for the answer to the PE question
					for (Double x : possiblePrimes) {
						sum += x;
					}	
					// If the 2 concatenated numbers are prime, it meets the property, increase the counter
					if (hsPrimes.contains(iConcat1) && hsPrimes.contains(iConcat2) || isPrimeStatic(iConcat1) && isPrimeStatic(iConcat2)) {
						counter++;
						hsPrimes.add(iConcat1);
						hsPrimes.add(iConcat2);
					} else {
						// Here I break, but I don't change the list, so it still contains whatever it contains, this was a problem with my a + 1 logic, wasn't testing every possibility
						counter = 0;
						break;
					}
					// If all the possible primes meet the property with this prime add this prime 
					if (counter == possiblePrimes.size()) {
						possiblePrimes.add(alPrimes.get(b));
						counter = 0;
						// If you have the desired 5, spit it out and the sum
						if (possiblePrimes.size() == 5) {
							// Break out and start a brand new list
							System.out.println(possiblePrimes + " " + sum);	
							break outerloop;

						}
					}
				}
			}
			possiblePrimes.clear();
		}
		return possiblePrimes;
	}

	public static void main(String[] args) {
		
		final double ceiling = 10000.;

		PrimePairSets x = new PrimePairSets();
		
		x.generatePrimes(ceiling);

		System.out.println(primePairFilter(x.alPrimes, x.hsPrimes));
	}
}