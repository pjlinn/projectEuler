import math
from decimal import *

ks = [1, -1, 2, -2, 4, -4]
finished = False

for d in range(2,25):
	finished = False
	if math.sqrt(d) % 1 == 0:
		continue
	for a in range(1,1000):
		if finished:
			break
		for b in range(1,1000):
			if finished:
				break

			if d*a*a + 4 < b*b: # 'a' will never catch 'b' at this point
				break

			for k in ks:
				if d*a*a + k == b*b:
					resultY = a * b
					resultX = b*b - 1
					if resultX != 0:
						print ('D: %d X : %d Y: %d a: %d b: %d k: %d' % (d, resultX, resultY, a, b, k))
						finished = True
						break



# sqrtNinteen = [1, 1, 1, 1, 6]
# arrayLength = len(sqrtNinteen)
# numerator = 1
# denominator1 = sqrtNinteen[arrayLength - 2]		# 2 first reading far right to left
# denominator2 = sqrtNinteen[arrayLength - 3]		# 1 second reading from far right to left
# newNumerator = 1

# for x in range(arrayLength - 4, -2, -1):
# 	numerator = (denominator2 * denominator1 + newNumerator) # 2 + 1 = 3
# 	newNumerator = denominator1
# 	denominator1 = numerator #
# 	denominator2 = sqrtNinteen[x]

# finalAddition = int(math.sqrt(13))

# print newNumerator, denominator1, denominator2


# primes = [2]
# isPrime = True

# for x in xrange(3,1000, 2):
# 	for y in xrange(2, x / 2 + 1):
# 		if x % y == 0:
# 			isPrime = False
# 			break
# 	if isPrime:
# 		primes.append(x)

# 	isPrime = True

# print primes

# max = 0

# for d in primes:
# 	if math.sqrt(d) % 1. == 0:
# 		# print('Perfect sqaure')
# 		continue
# 	for x in range(2,100000000):
# 		result1 = (math.sqrt(d * (math.pow(x,2) -1)) + d * x) / d
# 		result2 = -(math.sqrt(d * (math.pow(x,2) -1)) - d * x) / d
# 		if result1 % 1 == 0:
# 			# if x > max:
# 			# 	max = x
# 			print d, x, result1, result2
# 			break


# print (math.sqrt(d * (math.pow(649,2) - 1)) + d * 649) / d



#================================================
# Brute Force Solution

# primes = [2]
# isPrime = True

# for x in xrange(3,1000, 2):
# 	for y in xrange(2, x / 2 + 1):
# 		if x % y == 0:
# 			isPrime = False
# 			break
# 	if isPrime:
# 		primes.append(x)
		
# 	isPrime = True

# print primes

# max = 0.0

# for d in primes[133:]:
# 	if math.sqrt(d) % 1. == 0:
# 		# print('Perfect sqaure')
# 		continue
# 	for y in range(1, 100000000):
# 		x = Decimal(math.sqrt(1. + d * math.pow(y, 2.)))
# 		result = x % 1

# 		if result == 0.0:
# 			# if x > max:
# 			# 	max = x
# 			print('D: %d  X: %d' % (d, x))
# 			break
# 	else:
# 		print('No solution for %d' % (d))
#================================================

# subtractCounter = 1
# result = 0

# for d in xrange(2,100):
# 	if math.sqrt(d) % 1. == 0:
# 		print('Perfect sqaure')
# 		continue	
# 	for offset in xrange(1,100000000):
# 		for x in xrange(offset + 1,100000000):
# 			result = math.pow(x,2) - d * math.pow(x - offset, 2)
# 			# print ('Result %d D: %d X: %d' % (result, d, x))
# 			if result == 1:
# 				print ('Result D: %d X: %d' % (d, x))
# 				break
# 			if result < 0:
# 				break
# 		if (result == 1):
# 			break
# 		else:
# 			continue

# print getcontext()
# print Decimal(math.sqrt(13))

# d = 13
# increment = d
# result = d
# counter = 0

# for x in xrange(1, 181):
# 	increment = increment + 2 * d
# 	result += increment
# 	counter += 1
# 	print Decimal(math.sqrt(result + 1))

# for d in range(9, 10):
# 	for y in range(1, 100):
# 		print (math.pow(d * (y * y), .5))

# x = 1 + 778 * math.pow(78104744, 2)
# x = math.sqrt(x) % 1
# print x

# 2178548310.43
# 2178548338.32
# 2178548366.21
# 2178548394.11
# 2178548422.0
# 2178548449.89
# 2178548477.79

# 4.74607298394e+18
# 4.74607310547e+18
# 4.746073227e+18
# 4.74607334853e+18
# 4.74607347006e+18
