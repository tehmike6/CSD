import numpy as np
from scipy.stats import poisson
from scipy.stats import binom
import matplotlib.pyplot as plt

x= np.arange(10)
for mu in (0.15, 0.95, 1, 1.8, 2.6):
    plt.plot(x, poisson.pmf(k=x, mu=mu), ls='-')
plt.show()

for mu in ((10, 0.3), (100, 0.1), (1000, 0.1), (10000, 0.01)):
    if mu[0] >= 500:
        x= np.arange(300)
    else:
        x = np.arange(mu[0])
    plt.plot(x, poisson.pmf(k=x, mu=mu[0] * mu[1]), ls='-')
    plt.plot(x, binom.pmf(k=x, n=mu[0], p=mu[1]))
    plt.show()
