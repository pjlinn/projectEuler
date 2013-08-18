/*
	10001st Prime: Problem 7 Project Euler

	By listing the first six prime numbers: 2, 3, 5, 7, 11, 
	and 13, we can see that the 6th prime is 13.

	What is the 10001st prime number?
*/

import java.util.*;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

/*
	I already had code to generate a list of prime numbers 
	from problem 3, LargePrimeFactor, so I just modified it
	a bit. Took about 5 minutes.
*/

public class PrimeList {
	/*
		Created a class variable List because I want this list to
		be dynamically built and maintained. I don't want to 
		rebuild the List everytime a method is called. That's
		repeating work.
	*/
	List<Integer> divisors = new ArrayList<>();	
	/*
		I wanted to add these values only once, so I took out this
		statement from the method it was previously in. However, I
		got a compiler error when I left it out of a method. So
		I created a constructor where I add these 2 values only 
		once.
	*/
	public PrimeList(){
		divisors.add(2);
		divisors.add(3);
	}
	/*
		More complicated, but also more efficient algorithm for
		testing whether or not a number is prime. 

		Basically I try to minimize the set of values I need to 
		try before determining whether or not a number is prime.
		I do this by taking advantage of the fact that if a number 
		is divisible by a value, then it won't be divisible by 
		any factor of that number. So if you can't divide the 
		number by 3, then don't bother trying multiples of 3, like
		6, 9, 12, 15, etc.
	*/
	public boolean isPrimev2 (long num) {
		/*
			The method returns isPrime and inList is used
			when testing whether or not to add a value to the list
			of numbers to divide the value by (which turns out to be
			a collection of prime numbers)
		*/
		boolean isPrime = true;
		boolean inList = true;
		/*
			Again, we are setting the upperbound on what the set 
			of possible numbers are that we need to test to see
			if the number is prime. We are ignoring all even 
			numbers.
		*/
		long z = (num / 2);
		z = (z % 2 == 0) ? z -= 1 : z;
		/*
			Pre-test of values.
		*/
		if (num == 2) {
			isPrime = true;
			return isPrime;
		} else if (num <= 1) {
			isPrime = false;
			return isPrime;
		} else if (num % 2 == 0) {
			isPrime = false;
			return isPrime;
		}		
		/*
			Okay, so because I am using a dynamic class variable
			list, I want to test whether any of the numbers I've
			already calculated are factors of the passed number. 

			This can save time because I don't have to re-do work
			and just quickly elimnate the number from consideration
			without any extra work.
		*/
		for (Integer ab : divisors) {
			if (ab < (num / 2)) {
				if (num % ab == 0) {
					isPrime = false;
					return isPrime;
				}
			}
		}
		/*
			Here I am getting the current last index key in the 
			list by taking it's size and subtracting by 1 to account
			for 0 being the first index. 

			Then I am pulling the actual value, as this is where
			I want to start my new test since I've already 
			calculated and tested the values below this one.
		*/
		int lastIndex = divisors.size() - 1;
		int xyz = divisors.get(lastIndex);
		/*
			This for loop builds/appends the list of prime numbers,
			or numbers that aren't multiples of any previous 
			numbers. Again, I'm only trying odd numbers and am
			starting with where I left off the last time I called
			this method.

			First for generates the next number I am considering.
			The second for loop tests to see if the number is
			divisible by any of the other numbers in my list.
			If it is, we don't add it and move to the next number,
			if it isn't, we add it to the list.
		*/
		for (int i = xyz + 2;  i < z; i += 2) {
			for (int j = 0; j < divisors.size(); j++) {
				if (i % divisors.get(j) == 0) {
					inList = false;
					break;
				}
			}
			/*
				If inList is true, which it starts at, we add it
				to the list. We also see if the num is divisible 
				by it, if it is we return a false isPrime.
			*/
			if (inList) {
				divisors.add(i);
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
		long curTime = System.currentTimeMillis();
		/*
			I got compiler errors regarding my use of static and non-static
			which ended up meaning that once I used a class variable, I 
			needed to actually create an object of this class and invoke
			then now non-static methods this way. 
		*/
		PrimeList x = new PrimeList();
		/*
			Number to test whether or not it is prime.
		*/
		int counter = 5;
		/*
			Keeps track of the number of primes found.
		*/
		int numPrime = 2;
		/*
			While the list of primes is less than 10001, 
			test whether the counter isPrime, if it is,
			add it to the list of primes, if not, increment
			2.
			
			Spit out counter - 2 to get that 10001 value.
		*/
		while (numPrime < 10001) {
			if (x.isPrimev2(counter)) {
				numPrime += 1;
				// System.out.println(counter);
			}
			counter += 2;
		}
		System.out.println(counter - 2);
		System.out.println(" -> Time: " + (System.currentTimeMillis() - curTime) + " ms");
	}
}