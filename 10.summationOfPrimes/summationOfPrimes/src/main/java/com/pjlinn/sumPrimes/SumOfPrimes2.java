package com.pjlinn.sumPrimes;
/*

*/

import java.util.*;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

/*
	I want to countdown from 2,000,000 and find the first prime number
	by creating a list of all other prime numbers, then stopping and 
	summing the list
*/

public class SumOfPrimes2 {

	List<Integer> divisors = new ArrayList<Integer>();	

	public SumOfPrimes2(){
		divisors.add(2);
		divisors.add(3);
	}

	public List isPrimev2 (long num) {
		boolean isPrime = false;
		boolean inList = true;

		long z = (num);
		// Makes sure z is odd
		z = (z % 2 == 0) ? z -= 1 : z;

		while (isPrime == false) {
			if (num == 2) {
				isPrime = true;
				return divisors;
			} else if (num <= 1) {
				isPrime = false;
				return divisors;
			} else if (num % 2 == 0) {
				isPrime = false;
				return divisors;
			}		

			System.out.println("Did we get here?");
			// divide the number by all the current numbers in the list
			for (Integer ab : divisors) {
				if (ab < (num)) {
					if (num % ab == 0) {
						isPrime = false;
						// return divisors;
					}
				}
			}

			// xyz is set to the value of the last index
			int lastIndex = divisors.size() - 1;
			int xyz = divisors.get(lastIndex);

			// Check to see if the next odd number is prime, by dividing it by all the numbers currently in the list
			for (int i = xyz + 2;  i < z; i += 2) {
				for (int j = 0; j < divisors.size(); j++) {
					if (i % divisors.get(j) == 0) {
						inList = false;
						break;
					}
				}

				if (inList) {
					divisors.add(i);
					if (num % i == 0) {
						isPrime = false;
						// return divisors;
					}	
				}
				inList = true;
			}
			num -= 2;
		}

		return divisors;
	}

	public static void main(String[] args) {
		long curTime = System.currentTimeMillis();

		SumOfPrimes2 x = new SumOfPrimes2();

		System.out.println(x.isPrimev2(2000000));

		System.out.println(" -> Time: " + (System.currentTimeMillis() - curTime) + " ms");
	}
}