#!/bin/bash
grep -v "import org.slf4j.Logger" $1 > $1.bak
rm $1
mv $1.bak $1