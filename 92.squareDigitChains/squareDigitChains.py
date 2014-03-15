#!/usr/bin/python3

"""
	A number chain is created by continuously adding the square of 
	the digits in a number to form a new number until it has been 
	seen before.

	For example,

	44 → 32 → 13 → 10 → 1 → 1
	85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89

	Therefore any chain that arrives at 1 or 89 will become stuck 
	in an endless loop. What is most amazing is that EVERY starting 
	number will eventually arrive at 1 or 89.

	How many starting numbers below ten million will arrive at 89?

	----
	Tried to optimize by keeping track of numbers, didn't really seem
	work
"""

import math

def endingNumber(startingNumber, previousNumbers, 
	endingIn1, endingIn89, firstNumber):
	if startingNumber in endingIn1:
		return 1
	elif startingNumber in endingIn89:
		return 89

	holdingNumber = startingNumber
	modulus = 1
	remainder = startingNumber % modulus
	base = 10
	result = 0

	while remainder != startingNumber:
		modulus = modulus * base
		remainder = startingNumber % modulus

	modulus = modulus / base

	while startingNumber > 0:
		remainder = startingNumber % modulus
		digit = math.floor(startingNumber / modulus)
		startingNumber = remainder
		result = result + digit * digit
		modulus = modulus  / base

	if result == 1 or result == 89:
		if result in previousNumbers:
			if result == 1:
				endingIn1.append(firstNumber)
			elif result == 89:
				endingIn89.append(firstNumber)
			return result
		else:
			previousNumbers.append(result)
			return endingNumber(result, previousNumbers, endingIn1, 
				endingIn89, firstNumber)
	else:
		return endingNumber(result, previousNumbers, endingIn1, 
			endingIn89, firstNumber)

counter = 0

for x in range(1,1000000):
	result = endingNumber(x, [], [], [], x)
	if result == 89:
		counter = counter + 1

print (counter)
