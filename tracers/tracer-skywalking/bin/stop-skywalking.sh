#!/usr/bin/env bash

this="${BASH_SOURCE-$0}"
while [ -h "$this" ]; do
  ls=$(ls -ld "$this")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '.*/.*' >/dev/null; then
    this="$link"
  else
    this=$(dirname "$this")/"$link"
  fi
done

# convert relative path to absolute path
bin=$(dirname "$this")
script=$(basename "$this")
bin=$(
  cd "$bin" >/dev/null
  pwd
)
this="$bin/$script"

CURRENT_DIR=$bin
BASE_DIR=$(dirname "$bin")

SKYWALKING_DIR=$BASE_DIR/skywalking
SKYWALKING_UNTAR=apache-skywalking-apm-bin

function func_clear_log() {
  if [ -d $SKYWALKING_DIR/$SKYWALKING_UNTAR/logs ]; then
    echo "start delete skywalking log.."
    echo "rm -rf $SKYWALKING_DIR/$SKYWALKING_UNTAR/logs"
    rm -rf $SKYWALKING_DIR/$SKYWALKING_UNTAR/logs
  fi
}

function func_stop_process() {
  ps aux | grep 'OAPServerStartUp' | grep -v grep | awk '{print $2}' | xargs kill -9
  ps aux | grep 'skywalking-webapp.jar' | grep -v grep | awk '{print $2}' | xargs kill -9
}

func_stop_process
func_clear_log
