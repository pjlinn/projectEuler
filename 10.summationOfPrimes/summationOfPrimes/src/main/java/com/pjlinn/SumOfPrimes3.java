// package com.pjlinn.sumPrimes;

import java.util.*;
import java.lang.Math;

public class SumOfPrimes2 {

	List<Integer> primeList = new ArrayList<Integer>();

	public SumOfPrimes2 () {
		primeList.add(2);
		primeList.add(3);
	}

	public List generateList (int num) {
		// boolean isPrime = false;
		// boolean inList = true;

		// int z = num;
		// z = (z % 2 == 0) ? z -= 1 : z;

			// Modulo each item in list
			// for (Integer x : primeList) {
			// 	if (num % x == 0) {
			// 		isPrime = false;
			// 	} 
			// }

		// int iLastIndex = primeList.get(primeList.size() - 1);

		// while () {
		// 	for (int j = 0; j < primeList.size(); j++) {
		// 		if (z % primeList.get(j) == 0) {
		// 			inList = false;
		// 		}
		// 	}
		// 	if (inList == true) {
		// 		primeList.add(z);
		// 	}
		// 	z -= 2;
		// }
		// int counter = 5;
		// while (counter < num) {
		// 	for (int i = 3; i < Math.sqrt(counter); i += 2) {
		// 		if (num % i == 0) {
		// 			inList = false;
		// 			break;
		// 		}
		// 	}
		// 	if (inList == true) {
		// 		primeList.add(counter);
		// 	}
		// 	counter += 2;
		// 	inList = true;
		// }

		// for (int i = 5; i < num; i += 2) {
		// 	for (int j = 0; j < primeList.size(); j++) {
		// 		if (i % primeList.get(j) == 0) {
		// 			inList = false;
		// 			break;
		// 		}
		// 	}
		// 	if (inList == true) {
		// 		primeList.add(i);
		// 	}

		// 	inList = true;
		// }


		return primeList;
	}

	public boolean isPrime (int num) {
		boolean isPrime = true;

		if (num % 2 == 0) {
			isPrime = false;
			return isPrime;
		}

		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) {
				isPrime = false;
				break;
			}
		}

		return isPrime;
	}

	public static void main(String[] args) {
		
		long curTime = System.currentTimeMillis();

		SumOfPrimes2 x = new SumOfPrimes2();

		// int num = 20000;

		// System.out.println(x.generateList(num));
		long sum = 2L;
		for (int i = 3; i < 2000000; i += 2) {
			if (x.isPrime(i) == true) {
				sum += i;
				// System.out.println(i);
			}
		}
		System.out.println(sum);

		System.out.println("Time : " + (System.currentTimeMillis() - curTime));
	}
}