#!/usr/bin/python3

import math

def endingNumbers(startingNumber):
	result = 0
	while startingNumber > 0:
		digit = startingNumber % 10
		startingNumber = math.floor(startingNumber / 10)
		result += digit * digit

	if result == 1 or result == 89:
		return result
	else:
		return endingNumbers(result)

# possible values this problem can have 1 & 81 * 7 = 567 

def digitSum(num):
	result = 0
	while num > 0:
		digit = num % 10
		num = math.floor(num / 10)
		result += digit * digit
	return result

possibleValues = {}

for x in range(1,568):
	possibleValues[x] = endingNumbers(x)

result = 0

# print (possibleValues[digitSum(85)])

for i in range(1,10000000):
	startingNumberSum = digitSum(i)
	endingNumber = possibleValues[startingNumberSum]
	if endingNumber == 89 :
		result += 1

print(result)