'''
It turns out that 12 cm is the smallest length of wire that 
can be bent to form an integer sided right angle triangle in 
exactly one way, but there are many more examples.

12 cm: (3,4,5)
24 cm: (6,8,10)
30 cm: (5,12,13)
36 cm: (9,12,15)
40 cm: (8,15,17)
48 cm: (12,16,20)

In contrast, some lengths of wire, like 20 cm, cannot be bent to form 
an integer sided right angle triangle, and other lengths allow more 
than one solution to be found; for example, using 120 cm it is 
possible to form exactly three different integer sided right 
angle triangles.

120 cm: (30,40,50), (20,48,52), (24,45,51)

Given that L is the length of the wire, for how many values 
of L <= 1,500,000 can exactly one integer sided right angle 
triangle be formed?
-----------------------------

1. Not sure how to determine which integers form right angle
	triangles, but once you find one there is a simple scalar
	multiple that will give you another number
	=> 12cm x2, x3, x4 ... will all be solutions for example
2. A solution has multiple triangles if the solution is divisible by
	more than one previous solution and the results are different
	=> solution(12) * 10 != solution(30) * 4
	=> solution(12) * 4 == solution(24) * 2
-----------------------------
Got to the point where I had to look up an algorithm to calculate
pythagorean triples. Still looking for the best one to implement. 
The first one I tried didn't calcualte all the triples, so my count
was off.
	-> Going to try Tree of primitive Pythagorean triples:
	(https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples)
	- > TO-DO: Need to make it recursive
'''

import math

# listOfResults = []
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

# base = 3 # Starting number when calculating differences between squares 2^2 - 1^2
# baseInitialIncrease = 5 # First jump is by 5 from 3 to 8: 4^2 - 1^2
# baseIncrease = 2 # All following jumps are 5 + 2*x

# seriesStart = 2 # Difference between squares starts increasing by 2
# seriesIncrease = 2 # All following it increase by 2 + 2*x

# limit = 120
# count = 0

# while count < limit:
# 	# Build a list of differences up to a certain limit
# 	listOfDifferences = [] # clear the list
# 	for x in xrange(base, limit, seriesStart):
# 		listOfDifferences.append(x)
# 		# print listOfDifferences

# 	# Check if the difference is a square, if so figure out the total length
# 	for y in listOfDifferences:
# 		a = math.sqrt(y) 
# 		# Check to see if the difference in a square
# 		if a % 1.0 == 0.0:
# 			# TO-DO Figure how to get b each time
# 			b = listOfDifferences.index(y) + (count + 1) # The index 
# 			c = math.sqrt(a**2 + b**2)
# 			sum = a + b + c
# 			print sum

# 	if count == 0:
# 		base = base + baseInitialIncrease
# 	else:
# 		base = base + baseIncrease

# 	seriesStart = seriesStart + seriesIncrease
# 	count = count + 1

# =============================================

# listOfSquares = []
# answers = set()
# limit = 15
# indexJump = 1
# keepGoing = True

# for x in xrange(1, limit):
# 	listOfSquares.append(x**2)

# listOfSquaresLength = len(listOfSquares)

# while keepGoing:
# 	for x in xrange(0, listOfSquaresLength):
# 		cIndex = x + indexJump
		
# 		if cIndex > listOfSquaresLength - 1:
# 			print cIndex
# 			print 'break'
# 			break
# 		else:
# 			bSquared = listOfSquares[x]
# 			cSquared = listOfSquares[cIndex]
# 			aSquared = cSquared - bSquared
# 			a = math.sqrt(aSquared)
			
# 			if a % 1.0 == 0.0:
# 				result = []
# 				b = math.sqrt(bSquared)	
# 				c = math.sqrt(cSquared)
# 				length = a + b + c
# 				# result = sorted([a, b, c, length])
# 				# result = ",".join([str(a),str(b),str(c),str(length)])
				
# 				# if result in answers:
# 				# 	pass
# 				# else:
# 				# 	answers.append(result)

# 				answers.add(length)
# 	print 'test'
	
# 	# print len(answers)

# 	if indexJump > listOfSquaresLength - 1:
# 		keepGoing = False
# 	else:
# 		indexJump = indexJump + 1
# 		# print indexJump

# # for x in answers:
# # 	print x[3]

# # print answers

# limit = 150000

# setOfSquares = set()
# answers = set()
# result = []

# for x in xrange(1,limit):
# 	setOfSquares.add(x**2)

# for i in xrange(1,limit-1):
# 	for n in xrange(i+1,limit):
# 		result = i * (n - i) + i*n
# 		if result in setOfSquares:
# 			cSquared = n**2
# 			bSquared = cSquared - result
# 			aSquared = cSquared - bSquared
# 			c = n
# 			b = math.sqrt(bSquared)
# 			a = math.sqrt(aSquared)
# 			length = a + b + c
# 			# sortedSet = sorted([a,b,c,length])
# 			# answer = ",".join([str(sortedSet[0]),str(sortedSet[1]),str(sortedSet[2]),str(sortedSet[3])])
# 			answers.add(length)
# print 'answers'

# limit = 200
# setOfSquares = set()
# keepGoing = True
# test = 1
# # count = 0
# uniqueLengths = []
# uniqueLengthsDict = {}
# result = 0

# for x in xrange(2,limit):
# 	setOfSquares.add(x**2)

# while keepGoing:
# 	if test in setOfSquares:
# 		aSquared = test
# 		a = math.sqrt(aSquared)
# 		b = (((aSquared + 1) / 2) - 1)
# 		bSquared = b**2
# 		c = math.sqrt(aSquared + bSquared)
# 		length = a + b + c
# 		# print a,b,c
# 		if length > limit:
# 			keepGoing = False
# 		else:
# 			uniqueLengths.append(length)
# 	test = test + 2

# print uniqueLengths

# keepGoing = True

# for x in uniqueLengths:
# 	# uniqueLengthsDict[x] = 1
# 	result = 0
# 	while keepGoing:
# 		result = result + x

# 		if result > limit:
# 			break

# 		if result in uniqueLengthsDict:
# 			currentCount = uniqueLengthsDict[result]
# 			uniqueLengthsDict[result] = currentCount + 1
# 		else:
# 			uniqueLengthsDict[result] = 1

# print uniqueLengthsDict

# print len(uniqueLengths)

a11 = 1
a12 = 2
a13 = 2
a21 = -2
a22 = -1
a23 = -2
a31 = 2
a32 = 2
a33 = 3

b11 = 1
b12 = 2
b13 = 2
b21 = 2
b22 = 1
b23 = 2
b31 = 2
b32 = 2
b33 = 3

c11 = -1
c12 = -2
c13 = -2
c21 = 2
c22 = 1
c23 = 2
c31 = 2
c32 = 2
c33 = 3

pythagoreanLengths = []
a = 3
b = 4
c = 5

keepGoing = True
count = 0
limit = 1500000

while keepGoing:
	if count == 0:
		newA = a * a11 + b * a21 + c * a31
		newB = a * a12 + b * a22 + c * a32
		newC = a * a13 + b * a23 + c * a33
	elif count == 1:
		newA = a * b11 + b * b21 + c * b31
		newB = a * b12 + b * b22 + c * b32
		newC = a * b13 + b * b23 + c * b33	
	elif count == 2:
		newA = a * c11 + b * c21 + c * c31
		newB = a * c12 + b * c22 + c * c32
		newC = a * c13 + b * c23 + c * c33		
	else:
		keepGoing = False

	length = newA + newB + newC

	if length > limit:
		count = count + 1
		a = 3
		b = 4
		c = 5
	else:
		newLength = length
		while newLength < limit:
			pythagoreanLengths.append(newLength)
			newLength = newLength * 2
		a = newA
		b = newB
		c = newC

print len(pythagoreanPrimitives)