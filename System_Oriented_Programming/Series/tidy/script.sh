#!/bin/bash

GREEN='\033[0;32m'
NC='\033[0m'
COUNTER=0
echo "Finding all images in the specified folder..."
if [ $# -eq 1 ]; then
	PATHS=$(find $1 -name '*.jpg' -o -name '*.png' -o -name '*.jpeg')
else
	PATHS=$(find $(pwd) -name '*.jpg' -o -name '*.png')
fi
PATHS=($PATHS)
echo -e "${GREEN}Done!${NC}"
if ! test -d "photos";
then
	mkdir "photos"
	echo "Output folder created!"
fi
echo "Copying ${#PATHS[@]} images..."
for i in ${PATHS[@]} ; do
	YEAR=$(date -r $i | awk '{print $4}')
	MONTH=$(date -r $i | awk '{print $3}')
	if ! test -d "photos/$YEAR"; then
		mkdir photos/$YEAR
		mkdir photos/$YEAR/$MONTH
	elif ! test -d "photos/$YEAR/$MONTH"; then
		mkdir photos/$YEAR/$MONTH
	fi
	DATE=$(date -r $i | awk '{print $2 "-" $5}')
	cp $i photos/$YEAR/$MONTH
	FILENAME=$(basename $i)
	FILENAME="${FILENAME##*.}"
	mv photos/$YEAR/$MONTH/$(basename $i) photos/$YEAR/$MONTH/${DATE}.${FILENAME}
	COUNTER=$((${COUNTER} + 1))
	echo "Copied ${COUNTER}/${#PATHS[@]} images."
done
echo -e "${GREEN}Done!${NC}"
