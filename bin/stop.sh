#!/usr/bin/env bash

BASE_DIR=$(dirname "$0")
LOGS_DIR=$BASE_DIR/../logs

function get_server_pid_file() {
  echo "$LOGS_DIR/$1/pid/$1.pid"
}

function get_server_log_file() {
  echo "$LOGS_DIR/$1/app.log"
}

function func_close_process() {
  echo "---$1 stop started..---"

  PID=$(cat $(get_server_pid_file $1) 2>/dev/null)
  if [ ! -z $PID ]; then
    echo "shutting down $1. pid=$PID."
    ps aux | grep $PID | grep $1 | grep -v grep | awk '{print $2}' | xargs kill -9
  fi

  process_status=$(ps aux | grep $1 | grep -v grep | wc -l)

  if [ ! $process_status -eq 0 ]; then
    echo "shutting down $1. identifier=$1."
    ps aux | grep $1 | grep -v grep | awk '{print $2}' | xargs kill -9
  fi

  process_status=$(ps aux | grep $1 | grep -v grep | wc -l)

  if [ $process_status -eq 0 ]; then
    echo "---$1 stop completed.---"
  else
    echo "---$1 stop failed.---"
  fi
}

function func_clear_log() {
  echo "---clear $1 logs.---"

  if [ -f $(get_server_log_file $1) ]; then
    echo "rm $(get_server_log_file $1)."
    rm $(get_server_log_file $1)
  fi

  if [ -f $(get_server_pid_file $1) ]; then
    echo "rm $(get_server_pid_file $1)."
    rm $(get_server_pid_file $1)
  fi
}

function stop_server() {
  func_close_process $1
  func_clear_log $1
}

servers=$(cat $BASE_DIR/servers 2>/dev/null)
for server in $servers; do
  stop_server "${server}"
done
