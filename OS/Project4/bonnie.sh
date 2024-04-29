#!/bin/sh

# File system directory to benchmark
DIR=/mnt
# Random seed to run identical benchmark (can be adjusted)
SEED=42
# File size in left-shifts [MiB]
s=0
mkdir -p ./out/powersave ./out/performance

for mode in powersave performance
do
    sudo cpupower frequency-set -g $mode
    while [ $s -lt 14 ]
    do
	if [ ! -s "./out/${mode}/$((1<<s)).log" ]
	then
		sudo bonnie++-1.98/bonnie++ -d "$DIR" -s $((1<<s)):256:0:0 -n 0 -r $(printf "$((1<<s))/2\n" | bc | sed -e 's/^0/0.5/') -u root:root -x 5 -z $SEED | tee -a ./out/${mode}/$((1<<s)).log
	else
		printf "Skipping ${mode}/$((1<<s))\n"
	fi
	s=$((s+1))
    done
    s=0
done
sudo cpupower frequency-set -g ondemand
