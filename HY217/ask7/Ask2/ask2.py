import numpy as np
from scipy.stats import norm
import matplotlib.pyplot as plt

s = 1.18321595662


# # Askisi 2.a
def f(x):
    denominator = s * np.sqrt(2 * np.pi)
    nominator = np.exp(-0.5 * (((x - 4) / s) ** 2))
    return nominator / denominator


x = np.linspace(start=-4, stop=12, num=500)

plt.plot(x, f(x))
plt.show()

# Askisi 2.b
x = np.linspace(start=-20, stop=50, num=200)

plt.plot(x, norm.pdf(x, 45, 1))
plt.show()
