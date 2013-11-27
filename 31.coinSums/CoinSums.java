/*
	In England the currency is made up of pound, £, and pence, p, and 
	there are eight coins in general circulation:

	1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
	It is possible to make £2 in the following way:

	1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
	How many different ways can £2 be made using any number of coins?
*/

import java.util.ArrayList;

public class CoinSums {
	
	final ArrayList<Integer> coinSet = new ArrayList<Integer>();
	int sum = 0;

	public CoinSums() {
		coinSet.add(200);
		coinSet.add(100);
		coinSet.add(50);
		coinSet.add(20);
		coinSet.add(10);
		coinSet.add(5);
		coinSet.add(2);
		coinSet.add(1);
	}

	public int coinSums(int total) {
		for (Integer x : coinSet) {
			if (x == total) {
				sum = sum + 1;
			} else if (x == 2) {
				return (sum + total / 2 + 1); // combinations of 2's and 1's
			} else if (x < total) {
				if (total % x == 0) {
					return sum + 1 + (total / x) * coinSums((total / x - 1) * x);
				}
			}
		}
		return sum;
	}

	public int tester(int total) {
		int combinations = 0;

		for (Integer x : coinSet) {
			if (x == 1) {
				combinations += 1;
			} else if (x > total) {
				continue;
			} else {
				combinations += (total / x);
			}
		}

		return combinations;
	}

	public static void main(String[] args) {
		CoinSums x = new CoinSums();
		System.out.println(x.coinSums(10));
	}
}