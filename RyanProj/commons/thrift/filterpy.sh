#!/bin/bash
echo "# -*- coding: utf-8 -*-" > $1.bak
cat $1 >>$1.bak
rm $1
mv $1.bak $1
