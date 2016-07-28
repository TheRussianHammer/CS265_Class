#!/usr/bin/python
import sys

file = ""
if len(sys.argv) > 2 or len(sys.argv) < 2:
	c = sys.stdin
	file = c.readline()[:-1]
else:
	file = sys.argv[1]
accounts = {}
with open(file,'r') as f:
	for i in f:
		temp = i.split(" ", 1)
		accounts[temp[0]] = temp[1]
	accounts.keys().sort()
	print "ID    Name"
	for i in accounts:
		print str(i) + "    " + accounts[i]
	
