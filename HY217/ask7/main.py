


def periklis(x):
    heads = 0
    tails = 0
    for j in x:
        tmp = np.random.randint(0, 2)
        if tmp == 0:
            tails += 1
        else:
            heads += 1
    return heads - tails


def arsinoi(x):
    heads = 0
    tails = 0
    for j in x:
        tmp = np.random.randint(0, 2)
        if tmp == 0:
            tails += 1
        else:
            heads += 1
    return tails - heads


# x = np.linspace(start=1.  # lower limit
#                 , stop=5000  # upper limit
#                 , num=5000  # generate 51 points between 0 and 3
#                 )
# y = periklis(x)  # This is already vectorized, that is, y will be a vector!
# plt.plot(x, y)
# plt.show()

import numpy as np
import numpy.random
import scipy
import matplotlib.pyplot as plt

list = []
for n in range(1, 5001):
    heads = 0
    tails = 0
    for j in range(1, n+1):
        tmp = np.random.randint(0, 5)
        if tmp == 0:
            tails += 1
        else:
            heads += 1
    list.append((n, tails - heads))
    # print("To kerdos tou Perikli einai: " + str(heads - tails))
    # print("To kerdos tis Arsinois einai: " + str(tails - heads))
    # print()

for n in list:
    plt.scatter(n[0], n[1])

plt.show()