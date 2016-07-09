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

counter = 0
iteration = 0
a = 3
b = 4
c = 5
length = a + b + c

triple = [[a, b, c, iteration]]
listOfLengths = [length]

keepGoing = True
count = 0
limit = 1000

'''
	!!! Don't delete this!

	!!! I wrote it recursively, but Python on allows up to 999 iterations...ughh!

	Online is says to make it into an iteration. So frustrating, it took me awhile
	to think through this!
'''
def calculateTriple(triple, listOfLengths):
	# null condition
	if not triple:
		return listOfLengths

	tripleSize = len(triple) - 1

	a = triple[tripleSize][0]
	b = triple[tripleSize][1]
	c = triple[tripleSize][2]
	# Keeps track of where that triple is in the recursive function
	iteration = triple[tripleSize][3]

	if iteration == 0:
		newA = a * a11 + b * a21 + c * a31
		newB = a * a12 + b * a22 + c * a32
		newC = a * a13 + b * a23 + c * a33

		triple[tripleSize] = [a, b, c, iteration + 1]
	elif iteration == 1:
		newA = a * b11 + b * b21 + c * b31
		newB = a * b12 + b * b22 + c * b32
		newC = a * b13 + b * b23 + c * b33

		triple[tripleSize] = [a, b, c, iteration + 1]
	elif iteration == 2:
		newA = a * c11 + b * c21 + c * c31
		newB = a * c12 + b * c22 + c * c32
		newC = a * c13 + b * c23 + c * c33

		triple[tripleSize] = [a, b, c, iteration + 1]
	# Once we've expended a triple, drop it from tracking and start where we left off on the triple prior
	elif iteration == 3:
		triple = triple[:-1]
		return calculateTriple(triple, listOfLengths)
						
	addedTriple = [newA, newB, newC, 0]
	length = newA + newB + newC

	# If the length is too tall, go to the next transformation using the same triple
	if length > limit:
		return calculateTriple(triple, listOfLengths)
	# Add the new transformation and start over transforming that one
	else:
		listOfLengths.append(length)
		triple.append(addedTriple)
		# Sanity check
		# print addedTriple
		return calculateTriple(triple, listOfLengths)

print len(calculateTriple(triple, listOfLengths))

# while keepGoing:
# 	if count == 0:
# 		newA = a * a11 + b * a21 + c * a31
# 		newB = a * a12 + b * a22 + c * a32
# 		newC = a * a13 + b * a23 + c * a33
# 	elif count == 1:
# 		newA = a * b11 + b * b21 + c * b31
# 		newB = a * b12 + b * b22 + c * b32
# 		newC = a * b13 + b * b23 + c * b33	
# 	elif count == 2:
# 		newA = a * c11 + b * c21 + c * c31
# 		newB = a * c12 + b * c22 + c * c32
# 		newC = a * c13 + b * c23 + c * c33		
# 	else:
# 		keepGoing = False

# 	length = newA + newB + newC

# 	if length > limit:
# 		count = count + 1
# 		a = 3
# 		b = 4
# 		c = 5
# 	else:
# 		newLength = length
# 		while newLength < limit:
# 			pythagoreanLengths.append(newLength)
# 			newLength = newLength * 2
# 		a = newA
# 		b = newB
# 		c = newC

# print len(pythagoreanPrimitives)