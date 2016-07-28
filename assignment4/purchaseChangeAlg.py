def purchaseChange( amount, de ):
	cash = 0
	regCash = cashInReg()
	for i in range(len(de)):
		cash += denoms[i] * de[i]
	if cash > regCash:
		return None

	change = cash - amount

	if change < 0:
		return None

	changeDenoms = [0]*4
	for i in range(len(denoms)):
		if reg[3-i] > 0:
			changeDenoms[3-i] = change // denoms[3-i]
			change -= change // denoms[3-i] * denoms[3-i]
	return changeDenoms
