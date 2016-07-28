#!/usr/bin/python
import sys

if len(sys.argv) > 2 or len(sys.argv) < 2:
	print sys.argv[0] + "accepts 1 argument"
	sys.exit(1)
accounts = {}
with open(sys.argv[1],'r') as f:
	for i in f:
		temp = i.split(" ", 1)
		accounts[temp[0]] = temp[1]
	accounts.keys().sort()
	print "ID    Name"
	for i in accounts:
		print str(i) + "    " + accounts[i]
	
