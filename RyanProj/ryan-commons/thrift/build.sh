#!/bin/bash
rm -frv gen-*
./thrift -gen java -gen js message.thrift
./thrift -gen java -gen cpp airpush.thrift
cp -R gen-java/* ../src/main/java
echo "copy finished..."
