package com.pjlinn.sumPrimes;

/*
	Summation of Primes (Problem 10):
		The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

		Find the sum of all the primes below two million.	
*/

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
/*
	I took the isPrime implementation from my previous projectEuler 
	problems. It worked, but was wayyyyyy to slow, ~ 71s.

	ANSWER: 142913828922

	TO-DO: Make it faster and more efficient, it should take significantly
			less than a minute.
				=> Maybe move the method into the main function. I am 
				doubling up on for loops when looping through
				numbers I think.

	TO-DO: Make sure the primes list is only being appended and not
			stating over everytime. I feel like it's not working right...

	***Adjustments Made*** -> I instead decided to count down from 2000000
	and stop once I reached my first prime. The thought process being the
	list of primes is already compiled. This worked and was faster, however
	it was incomplete because the list is for primes < num / 2. So I 
	made a quick change to z, but the answer was wrong (142911828929)
	so I need to look at it again later. I think I could maybe make the 
	num = 4000000 to and leave the isPrime implementation unchanged with
	num / 2...
*/
public class SumOfPrimes {

	List<Integer> primes = new ArrayList<Integer>();

	public SumOfPrimes() {
		primes.add(2);
		primes.add(3);
	}

	public boolean isPrime (long num) {

		boolean isPrime = true;
		boolean inList = true;

		// long z = (num / 2);
		// z = (z % 2 == 0) ? z -= 1 : z;
		long z = num;

		if (num == 2) {
			isPrime = true;
			return isPrime;
		} else if (num <= 1 || num % 2 == 0) {
			isPrime = false;
			return isPrime;
		}

		for (Integer ab : primes) {
			if (ab < (num / 2)) {
				if (num % ab == 0) {
					isPrime = false;
					return isPrime;
				}
			} else {
				break;
			}
		}

		int lastIndex = primes.size() - 1;
		int xyz = primes.get(lastIndex);

		for (int i = xyz + 2; i < z; i +=2) {
			for (int j = 0; j < primes.size(); j++) {
				if (i % primes.get(j) == 0) {
					inList = false;
					break;
				}
			}
			if (inList) {
				primes.add(i);
				if (num % i == 0) {
					isPrime = false;
					return isPrime;
				}
			}
			inList = true;
		}
		return isPrime;
	}

	public static void main(String[] args) {
		/*
			TO-DO: Don't use 2 lists, it's wasteful. Adjust the code 
					accordingly.
		*/
		// List<Integer> x = new ArrayList<Integer>();

		long currTime = System.currentTimeMillis();

		SumOfPrimes primeList = new SumOfPrimes();

		long sum = 0L;

		// for (int i = 1999999; i > 5; i -= 2) {
		// 	if(primeList.isPrime(i)) {
		// 		// System.out.print(i + ", ");
		// 		sum += i;
		// 	}
		// }

		// for (Integer y : x) {
		// 	sum += y;
		// }

		int counter = 1999999;
		while (!primeList.isPrime(counter)) {
			counter -= 2;
		}

		for (Integer x : primeList.primes) {
			sum += x;
		}
		System.out.println("\nFirst prime reached = " + sum);
		System.out.println("Time in ms: " + 
			(System.currentTimeMillis() - currTime));
	}
}