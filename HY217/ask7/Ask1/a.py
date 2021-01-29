import numpy as np
import matplotlib.pyplot as plt

list = []
for n in range(1, 1001):
    heads = 0
    tails = 0
    for j in range(1, n+1):
        tmp = np.random.randint(0, 4)
        if tmp == 0:
            tails += 1
        else:
            heads += 1
    list.append((n, heads - tails))
    # print("To kerdos tou Perikli einai: " + str(heads - tails))
    # print("To kerdos tis Arsinois einai: " + str(tails - heads))
    # print()

for n in list:
    plt.scatter(n[0], n[1])

plt.show()