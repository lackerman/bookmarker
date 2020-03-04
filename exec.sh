#!/bin/sh

# https://medium.com/@chrishantha/java-flight-recorder-cheat-sheet-98f5143f5f88
JVM_OPTS_JFR='-XX:StartFlightRecording=delay=5s,disk=true,dumponexit=true,filename=recording.jfr,maxsize=1024m,maxage=1d,settings=profile,path-to-gc-roots=true'

_term() {
  echo "Caught SIGTERM signal!"
  kill -TERM "$child"
  echo "Going to sleep to capture jfr recording"
  sleep 5
}

trap _term SIGTERM

echo "Doing some initial work...";
java \
    -Xlog:gc*=debug:file=gc.log:utctime,uptime,tid,level:filecount=10,filesize=128m \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=heapdump.hprof \
    ${JVM_OPTS_JFR} \
    -jar /app/app.jar &

child=$!
wait "$child"
