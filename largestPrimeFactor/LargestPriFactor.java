/*
	Largest Prime Factor: Problem 3 Project Euler

	The prime factors of 13195 are 5, 7, 13 and 29.

	What is the largest prime factor of the number 600851475143 ?
*/

import java.util.*;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

/*
	XXXXXXXXX
	My initial idea is to first generate a collection of all the prime
	numbers below the prime factor number we input.

	Then I will sort it from smallest to largest, if it isn't already
	and modulo the last index (the largest value) and start working 
	my way down until I get a modulo of 0. 
	XXXXXXX

	So wrong. The value provided is so large that this would take
	forever. I don't need to test every number 1 -> value whether
	it is prime, I just need to test if an evenly divisible number
	is prime. So value mod 2, 3, 4, ... until you get one that is
	0 and also is a prime number. The first one is the largest.
*/

public class LargestPriFactor {
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
	public LargestPriFactor(){
		divisors.add(2);
		divisors.add(3);
	}
	/*
		First attempt at an isPrime test method. It works, but was
		too slow with my initial apporach of testing every number
		between 1 and the given value so I scrapped it. With my
		much more efficient approach to solving the problem, 
		this implementation should work fine, but the second 
		version is more efficient I believe.
	*/
	public static boolean isPrime(long num) {
		/*
			The boolen we are going to return.
		*/
		boolean isPrime = true;
		/*
			Even numbers can't prime, sans 2

			This could be a good place to try a recursive function
			the trivial case being when a prime number test equals 
			0.
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
			Creating the upperbound of a possible set of numbers
			that could be factors of the value. Real simply we
			divide by 2.
		*/
		long i = (num / 2);
		/*
			If the result is even, add 1 to make it odd, otherwise
			leave it. I did this because I wasn't really sure what
			was going to happen diving the int, and I know all even
			numbers can't be prime so I'm only testing odd numbers
			by subtracting by 2 in my loop.
		*/
		i = (i % 2 == 0) ? i += 1 : i;
		/*
			For some reason you can't just put 'i'. So I had to set
			a new int, j, equal to i. Real simply, this starts at 
			the upperbound and starts counting down. If you hit a 
			mod = 0 then it quits with isPrime = false.
		*/
		for (long j = i; j > 1; j -= 2) {
			if (num % j == 0) {
				isPrime = false;
				return isPrime;
			} else {
				isPrime = true;
			}
		}

		return isPrime;
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
			if (num % ab == 0) {
				isPrime = false;
				return isPrime;
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
		/*
			I got compiler errors regarding my use of static and non-static
			which ended up meaning that once I used a class variable, I 
			needed to actually create an object of this class and invoke
			then now non-static methods this way. 
		*/
		LargestPriFactor x = new LargestPriFactor();
		/*
			Varaibles used. keepGoing is variable for my while loop.

			abc is the big value provided by ProjectEuler.

			abcd are the possible range of divisble values.
		*/
		boolean keepGoing = true;
		long abc = 600851475143L;
		long abcd = (abc % 2 == 0) ? abc + 1 : abc; 
		/*
			Start at 3
		*/
		int counter = 3;
		/*
			Starting at 3, if the number provided is evenly divisible
			we test the result of dividing 3 by the number for being
			prime. The first result we get that is prime will be the
			answer. 

			If it is evenly divisible, but not prime, keep going while
			skipping even numbers.
		*/
		while (keepGoing) {
			if (abc % counter != 0) {
				keepGoing = true;				
			}else if (x.isPrimev2(abc / counter)) {
				System.out.println("Divide by: " + counter);
				System.out.println("THE ANSWER IS: " + abc / counter);
				keepGoing =	false;
			} else {
				keepGoing = true;
			}
			counter += 2;
			}
		}
	}
}