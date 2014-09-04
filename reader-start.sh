#!/bin/bash

java -cp /home/pi/ArduinoListener/src/ Runner  &> /dev/null

pid=`ps aux | grep Runner | awk '{print $2}'`
if $pid != null; then
 echo $pid
 else
 echo "program did not start"
fi