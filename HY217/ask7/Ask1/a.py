import numpy as np
from enum import Enum
import matplotlib.pyplot as plt


class Coin(Enum):
    Tails = 0
    Heads = 1


def coinFlip():
    return np.random.randint(0, 2)


def favoredCoinFlip():
    return np.random.randint(0, 4)


PeriklisEarnings = []  # Initialization of the list that holds the earnings of Periklis
for n in range(0, 1000):  # We start from throw 1 to 1000
    headsC = 0
    tailsC = 0
    for j in range(0, n):  # This simulates every throw for 0-n times
        coin = favoredCoinFlip()  # Random Coin Flip
        if coin == Coin.Tails:
            tailsC += 1
        else:
            headsC += 1
    PeriklisEarnings.append((n, headsC - tailsC))  # A tuple of n that is the times that the coin was flipped
    # and the subtraction of the counters that shows the gain or loss
    # of Periklis

for n in PeriklisEarnings:
    plt.scatter(n[0], n[1])
plt.show()
