import numpy as np
import array as ar
from scipy.stats import poisson
from scipy.stats import binom
import matplotlib.pyplot as plt

size = 10000


def FindPoints(array):
    points = 0
    for i in range(0, 225):
        tmp = array[i]
        if i in range(56, 104):
            points += 1 * tmp
        elif i in range(104, 144):
            points += 3 * tmp
        elif i in range(144, 176):
            points += 5 * tmp
        elif i in range(176, 200):
            points += 10 * tmp
        elif i in range(200, 216):
            points += 15 * tmp
        elif i in range(216, 224):
            points += 25 * tmp
        elif i == 224:
            points += 50 * tmp
    return points


targetMap = ar.array("i", range(225))
tmpMap = ar.array("i", range(225))

# Area with 0 points    0-55
# Area with 1 points    56-103
# Area with 3 points    104-143
# Area with 5 points    144-175
# Area with 10 points   176-199
# Area with 15 points   200-215
# Area with 25 points   216-223
# Area with 50 points   224

for item in targetMap:
    targetMap[item] = 0
counterC = 0
counterD = 0
counterE = 0

map = []

for i in range(0, size):
    for k in range(0, 225):
        tmpMap[k] = 0
    for j in range(0, 6):
        tmp = np.random.randint(0, 225)
        targetMap[tmp] = targetMap[tmp] + 1
        tmpMap[tmp] = tmpMap[tmp] + 1
    if FindPoints(tmpMap) > 200:
        counterC += 1
    if FindPoints(tmpMap) < 50:
        counterD += 1
    if FindPoints(tmpMap) == 100:
        counterE += 1
    map.append(FindPoints(tmpMap))
# a
points_50 = targetMap[224] / (6.0 * size)
print(points_50)

# b
points_5 = 0
for item in range(144, 176):
    points_5 += targetMap[item]

points_5 = points_5 / (6.0 * size)
print(points_5)

# c
print(counterC / (1.0 * size))

# d
print(counterD / (1.0 * size))

# e
print(counterE / (1.0 * size))


myBins = []
for i in range(0, 151):
    myBins.append(i)

map = []
def importToArray(index, value):
    print(index)
    for i in range(0, targetMap[index] + 1):
        map.append(value)


for i in range(0, 225):
    if i in range(56, 104):
        importToArray(i, 1)
    elif i in range(104, 144):
        importToArray(i, 3)
    elif i in range(144, 176):
        importToArray(i, 5)
    elif i in range(176, 200):
        importToArray(i, 10)
    elif i in range(200, 216):
        importToArray(i, 15)
    elif i in range(216, 224):
        importToArray(i, 25)
    elif i == 224:
        importToArray(i, 50)
    else:
        importToArray(i, 0)

plt.hist(map)
plt.show()

# teliko istograma
plt.hist(map,bins=myBins)
plt.show()