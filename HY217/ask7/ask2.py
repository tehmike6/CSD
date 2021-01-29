import numpy as np
from scipy.stats import norm
import matplotlib.pyplot as plt

s = 1.18321595662

# def f(x):
#     denominator = s * np.sqrt(2 * np.pi)
#     nominator = np.exp(-0.5 * (((x - 4) / s) ** 2))
#     return nominator / denominator
#
#
# x = np.linspace(start=-4, stop=12, num=250)
#
# y = f(x)  # This is already vectorized, that is, y will be a vector!
# plt.plot(x, y)
# plt.show()

# Askisi 2.b
x = np.linspace(start=0, stop=200, num=450)

plt.plot(x, norm.pdf(x, 50, 1))  # alternative look
plt.show()