def greedy(amount, denoms):
    result = []
    while (amount > 0):
        if (amount >= denoms[0]):
            num = amount // denoms[0]
            amount -= (num * denoms[0])
            result.append([denoms[0], num])
        denoms = denoms[1:]
    return result

print greedy(1437, [500, 200, 100, 50, 25,10,5,1])
