import numpy as np
import math
import os
import csv

NBR_FILES = 60
NBR_ANGLES = 20
NBR_DIST = 4

file_path = ""

def get_mic(data, nbr):
	done  = False
	sound = []

	for input_value in data:
		value = int(input_value[nbr])
		if (value >= 20 and not done):
			sound.append(value)
		if (value < 20 and len(sound) >= 20):
			done = True
		elif (len(sound) < 20 and not done and value < 20):
			sound = []
	return (sound)

def parse_file(data):
	sounds = [[]] * 4
	values = np.zeros((2, 4))
	data = data[1:]
	for i in range(4):
		sounds[i] = get_mic(data, i+1)
		values[0][i] = max(sounds[i])
		values[1][i] = sum(sounds[i]) / len(sounds[i])
	return (values)

def main():
	outfile = open("../algorithm/angle_all.csv", "w")
	if (outfile == None):
		print("Error opening outfile\n")
		quit
	#outfile.write("Dist,")
	#outfile.write("mic0_max,mic1_max,mic2_max,mic3_max,mic0_mean,mic1_mean,mic2_mean,mic3_mean")
	#outfile.write("\n")
	for d in range(1, NBR_DIST):
		for a in range(NBR_ANGLES):
			for f in range(NBR_FILES):
				file_path="data/D"+str(d * 16)+"_A"+str(a * 18)+"/file"+str(f)+"_D"+str(d * 16)+"_A"+str(a * 18)+".csv"
				if (not os.path.exists(file_path)):
					continue 
				with open(file_path, 'r') as csvfile:
					reader = csv.reader(csvfile)
					data = list(reader)
					values = parse_file(data)
					outfile.write(str(a * 18)+",")
					outfile.write(str(values[0][0])+",")
					outfile.write(str(values[0][1])+",")
					outfile.write(str(values[0][2])+",")
					outfile.write(str(values[0][3])+",")
					outfile.write(str(values[1][0])+",")
					outfile.write(str(values[1][1])+",")
					outfile.write(str(values[1][2])+",")
					outfile.write(str(values[1][3]))
					outfile.write("\n")
					#print("File : " + str(f) + ", angle: " + str(a*18) + ", dist: " + str(d*16))
					

# Example usage
if __name__ == "__main__":
	main()
