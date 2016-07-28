#!/bin/bash
for file in *; do
	echo $file `wc -l -w < $file`
done
