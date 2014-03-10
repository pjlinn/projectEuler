#!/usr/bin/python3

"""
There are exactly ten ways of selecting three from five, 12345

It is not until n = 23, that a value exceeds one-million

How many, not necessarily distinct, values of  nCr, 
for 1 <= n <= 100, are greater than one-million?
"""

import math

def nChooseR(n, r):
	result = math.factorial(n) / (math.factorial(r) * math.factorial(n - r))
	return result

# print nChooseR(5, 2)

counter = 0

for x in xrange(1,101):
	for y in xrange(1,x):
		if nChooseR(x,y) > 1000000:
			counter += 1

print counter