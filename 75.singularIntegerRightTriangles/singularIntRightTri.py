'''
It turns out that 12 cm is the smallest length of wire that can be bent to form an 
integer sided right angle triangle in exactly one way, but there are many more examples.

12 cm: (3,4,5)
24 cm: (6,8,10)
30 cm: (5,12,13)
36 cm: (9,12,15)
40 cm: (8,15,17)
48 cm: (12,16,20)

In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer 
sided right angle triangle, and other lengths allow more than one solution to be 
found; for example, using 120 cm it is possible to form exactly three different 
integer sided right angle triangles.

120 cm: (30,40,50), (20,48,52), (24,45,51)

Given that L is the length of the wire, for how many values of L <= 1,500,000 can 
exactly one integer sided right angle triangle be formed?
-----------------------------

1. Not sure how to determine which integers form right angle
	triangles, but once you find one there is a simple scalar
	multiple that will give you another number
	=> 12cm x2, x3, x4 ... will all be solutions for example
2. A solution has multiple triangles if the solution is divisible by
	more than one previous solution and the results are different
	=> solution(12) * 10 != solution(30) * 4
	=> solution(12) * 4 == solution(24) * 2
'''

import math

listOfResults = []
# breakingPoint = 15000
# limit = breakingPoint / 3

# for a in xrange(1,limit):
# 	for b in xrange(a,limit):
# 		c = math.sqrt(a**2 + b**2)
# 		result = a + b + c

# 		if result > breakingPoint:
# 			break
# 		elif c % 1 != 0:
# 			pass
# 		else:
# 			# print a, b, c
# 			listOfResults.append(result)
# print len(listOfResults)

# increase = 0
# start = 3

# for x in xrange(3, 50000, 2):
# 	listOfResults.append(x)

# for x in listOfResults:
# 	a = math.sqrt(x)
# 	if a % 1.0 == 0.0:
# 		b = listOfResults.index(x) + 1
# 		c = math.sqrt(a**2 + b**2)
# 		sum = a + b + c
# 		print sum

base = 3 # Starting number when calculating differences between squares 2^2 - 1^2
baseInitialIncrease = 5 # First jump is by 5 from 3 to 8: 4^2 - 1^2
baseIncrease = 2 # All following jumps are 5 + 2*x

seriesStart = 2 # Difference between squares starts increasing by 2
seriesIncrease = 2 # All following it increase by 2 + 2*x

limit = 120
count = 0

while count < limit:
	# Build a list of differences up to a certain limit
	listOfDifferences = [] # clear the list
	for x in xrange(base, limit, seriesStart):
		listOfDifferences.append(x)
		# print listOfDifferences

	# Check if the difference is a square, if so figure out the total length
	for y in listOfDifferences:
		a = math.sqrt(y) 
		# Check to see if the difference in a square
		if a % 1.0 == 0.0:
			# TO-DO Figure how to get b each time
			b = listOfDifferences.index(y) + (count + 1) # The index 
			c = math.sqrt(a**2 + b**2)
			sum = a + b + c
			print sum

	if count == 0:
		base = base + baseInitialIncrease
	else:
		base = base + baseIncrease

	seriesStart = seriesStart + seriesIncrease
	count = count + 1