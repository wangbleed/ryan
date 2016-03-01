#!/bin/sh
if
	test -e pid.txt
then
	./stopEngine.sh
fi

JAVA_OPTS="-server -Xms1024m -Xmx1024m -Xmn512m -Xss256k -XX:PermSize=128m -XX:MaxPermSize=128m -XX:SurvivorRatio=6 -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:+UseConcMarkSweepGC -XX:CMSFullGCsBeforeCompaction=0 -XX:+UseCMSCompactAtFullCollection -XX:GCTimeRatio=19  -XX:CMSInitiatingOccupancyFraction=50 -XX:LargePageSizeInBytes=128M"

java -Djava.ext.dirs=../lib $JAVA_OPTS -classpath ../conf com.courier.commons.server.InitServer &

echo $!>pid.txt
