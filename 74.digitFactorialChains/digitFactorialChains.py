''' 
Runs under a minute. Checks to see if I've already found
the chain for each calculated if number. If I have, I add
it to what I've found so far. If I haven't, I continue and
add once I have finished.
'''

import math
import time

# Doesn't work for numbers like 10, 100, 1000, etc.
# Shouldn't matter though
def factorialLink(number):
	
	# Guess on appropriate size. I could try and
	# parse the number so it would be length independent
	divisor = 1000000000 

	while number / divisor == 0:
		divisor = divisor / 10		

	result = 0

	while divisor > 0:
		digit = number / divisor
		result = result + math.factorial(digit)
		number = number % divisor
		divisor = divisor / 10
	return result
# ========================

startTime = time.time()

unique 			= True
count = totalLength = holder = 0
chainList 		= []
existingDict 	= {}


for x in xrange(1,10000):
	holder = x
	chainList.append(x) # Add number to chain

	# While the chain has not repeated
	while unique:
		# Next value in the chain
		nextLink = factorialLink(x)

		# If we know the length of the nextLink already
		if nextLink in existingDict:
			totalLength = len(chainList) + existingDict[nextLink]

			existingDict[holder] = totalLength
			unique = False

			if totalLength == 60:
				count = count + 1
		else:
			if nextLink in chainList:
				existingDict[holder] = len(chainList)
				unique = False
				if len(chainList) == 60:
					count = count + 1
			else:
				chainList.append(nextLink)
				x = nextLink

	unique = True
	chainList = []

endTime = time.time()
print count
print endTime - startTime