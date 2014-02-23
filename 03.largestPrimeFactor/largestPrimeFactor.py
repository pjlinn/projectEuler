"""
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143?
"""

import math

# function that returns a list of primes up to a certain number
def generateListOfPrimes(n):
	listOfPrimes = []
	for x in xrange(1,n):
		if isPrime(x):
			listOfPrimes.append(x)
	return listOfPrimes

# tests if number passed is a prime
def isPrime(n):
	for x in xrange(2,int(math.ceil(math.sqrt(n) + 1))):
		if n % x == 0:
			return False
	return True

# main function
maxValue = 600851475143
# print maxValue / 2
# listOfPrimes = generateListOfPrimes(maxValue)

for x in xrange(int(maxValue ** 0.5), 1, -1):
	if maxValue % x == 0:
		if isPrime(x):
			print x
			break

