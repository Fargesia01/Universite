import matplotlib.pyplot as plt
import pandas as pd

# Read the CSV file
data = pd.read_csv('data/D16_A18/file20_D16_A18.csv')

# Extract the values from the columns
steps = data['step']
thing1 = data['mic0']
thing2 = data['mic1']
thing3 = data['mic2']
thing4 = data['mic3']

# Plot all four things on the same graph
plt.plot(steps, thing1, label='mic0')
plt.plot(steps, thing2, label='mic1')
plt.plot(steps, thing3, label='mic2')
plt.plot(steps, thing4, label='mic3')

# Customize the plot
plt.xlabel('Steps')
plt.ylabel('Microphones values')
plt.title('microPlot')
plt.legend()
plt.savefig('microLAST.png')
# Show the plot
plt.show()
