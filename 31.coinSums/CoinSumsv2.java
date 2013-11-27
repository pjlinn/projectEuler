/*
	In England the currency is made up of pound, £, and pence, p, and 
	there are eight coins in general circulation:

	1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
	It is possible to make £2 in the following way:

	1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
	How many different ways can £2 be made using any number of coins?

	--- 
	Brute forced it, which is totally lame...

	I think there must be a way to do this recursively knowing things like
	2 and 1 make up 5 in 3 distinct ways. So 10 is made up of 5,5 but also
	5,(2,1) for a total of 4.

	Everything should be able to be defined in factors of 5 I think.
*/

import java.util.ArrayList;

public class CoinSumsv2 {
	// Trying to figure out how to do this not brute force...

	public static void main(String[] args) {
		int count = 0;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 5; k++) {
					for (int l = 0; l < 11; l++) {
						for (int m = 0; m < 21; m++) {
							for (int n = 0; n < 41; n++) {
								for (int p = 0; p < 101; p++) {
									for (int q = 0; q < 201; q++) {
										if (200 * i + 100 * j + 50 * k + 20 * l + 10 * m + 5 * n + 2 * p + 1 * q > 200) {
											break;
										} else if (200 * i + 100 * j + 50 * k + 20 * l + 10 * m + 5 * n + 2 * p + 1 * q == 200) {
											count++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(count);
	}
}