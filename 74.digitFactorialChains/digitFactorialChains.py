# But slow brute force. Want to modify it so
# that I'm not double counting numbers I've 
# already calculated. 

import math

# Doesn't work for numbers like 10, 100, 1000, etc.
# Shouldn't matter though
def factorialLink(number):
	
	divisor = 1000000000 # Guess on appropriate size

	while number / divisor == 0:
		divisor = divisor / 10		

	result = 0

	while divisor > 0:
		digit = number / divisor
		result = result + math.factorial(digit)
		number = number % divisor
		divisor = divisor / 10
	return result

unique = True
chainList = []
count = 0

# print factorialLink(145)

for x in xrange(1,1000000):
	chainList.append(x)
	while unique:
		nextLink = factorialLink(x)
		# print nextLink
		if nextLink in chainList:
			unique = False
			if len(chainList) == 60:
				count = count + 1
		else:
			chainList.append(nextLink)
			x = nextLink

	unique = True
	chainList = []

print count