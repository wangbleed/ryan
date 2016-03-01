#!/bin/sh

cat pid.txt|while read line
do
	kill -9 $line
	rm pid.txt
done
