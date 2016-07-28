#!/usr/bin/python

import sys

def parsePostFix(expr):
    expr = expr.strip()
    stack = []
    PEMDAS = ['/','*','^','+','-'] # PEMDAS is an algebraic acronym
    temp =""
    y = ""
    for i in expr:
        if i != " " :
            temp += i
        elif temp not in PEMDAS:
            stack.append(temp)
            temp = ""
        else:
            try:
                y = stack.pop()
                if temp  == "^":
                    result = evaluateExpr(stack.pop(),y,"**")
                else:
                    result = evaluateExpr(stack.pop(),y,temp)
                stack.append(result)
                temp = ""
            except IndexError:
                return "-E-" 
    try:
        y = stack.pop()
    except IndexError:
        return "-E-"    

    if len(stack) > 1:
        return "-E-"

    try:
        if temp == "^":
            return evaluateExpr(stack.pop(),y,"**")
        return evaluateExpr(stack.pop(),y,temp)
    except IndexError:
        return "-E-"

def evaluateExpr(operandA,operandB,operator):
    return str( eval( str( float( operandA ) ) + operator + \
                      str( float( operandB ) ) ) )


 
while 1:
	try:
		line = sys.stdin.readline()
	except KeyboardInterrupt:
		break
	if not line:
		break
	print (parsePostFix(line))
 
