#!/usr/bin/python
import sys

def average(L):
	'''accepts a list of numeric values, and returns their average'''
	result = 0
	count = 0
	for i in L:
		result += i
		count += 1
	return result/count

'''Using with is a safer way of handling files
 as it will close the file after
 execution or when an exception is thrown'''	
if len(sys.argv) > 2 or len(sys.argv) < 2:
	print sys.argv[0] + " accepts 1 argument"
	sys.exit(1)
with open(sys.argv[1],'r') as f:
	#reads each line and sets data to student list
	#prints current student info
	print("Name     Average")
	for i in f:
		student = i.split(',',i.count(','))	
		print(student[0] + "     " + \
 		str( average([int(z) for z in student[1:]])))	
