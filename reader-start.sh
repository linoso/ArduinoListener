#!/bin/bash

java -cp /opt/ArduinoListener/src/ Runner  &> /dev/null

pid=`ps aux | grep Runner | awk '{print $2}'`
if $pid != null; then
 echo $pid
 else
 echo "program did not start"
 return 1;
fi
