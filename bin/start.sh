#!/usr/bin/env bash

BASE_DIR=$(dirname "$0")
LOGS_DIR=$BASE_DIR/../logs

UNIT_TIME=5
CHECK_COUNT=36
CLOSE_WAIT_TIME=`expr $UNIT_TIME \* $CHECK_COUNT`

tracer=$1

$BASE_DIR/start-${tracer}.sh

function func_check_process
{
        echo "---check $1 process status.---"

        pid=`cat $PID_DIR/$PID_FILE 2>/dev/null`
        process_status=0
        if [ ! -z $pid ]; then
                process_status=`ps aux | grep $pid | grep $IDENTIFIER | grep -v grep | wc -l`

                if [ ! $process_status -eq 0 ]; then
                        echo "already running $COLLECTOR_IDENTIFIER process. pid=$pid."
                fi
        fi

        if [ $process_status -eq 0 ]; then
                process_status=`ps aux | grep $IDENTIFIER | grep -v grep | wc -l`

                if [ ! $process_status -eq 0 ]; then
                        echo "already running $COLLECTOR_IDENTIFIER process. $IDENTIFIER."
                fi
        fi

        if [ ! $process_status -eq 0 ]; then
                echo "already running $COLLECTOR_IDENTIFIER process. bye."
                exit 1
        fi
}

function func_init_log
{
        echo "---initialize $1 logs---"

        if [ ! -d $LOGS_DIR ]; then
                echo "mkdir $LOGS_DIR"
                mkdir $LOGS_DIR
        fi

        if [ ! -d $( get_server_log_directory $1 ) ]; then
                echo $( get_server_log_directory $1 )
                mkdir $( get_server_log_directory $1 )
        fi

        if [ ! -d $( get_server_pid_directory $1 ) ]; then
                echo "mkdir $( get_server_pid_directory $1 )"
                mkdir $( get_server_pid_directory $1 )
        fi

        if [ -f  $( get_server_log_file $1 ) ]; then
                echo "rm $( get_server_log_file $1 )."
                rm $( get_server_log_file $1 )
        fi

        if [ -f  $( get_server_pid_file $1 ) ]; then
                echo "rm $( get_server_pid_file $1 )."
                rm $( get_server_pid_file $1 )
        fi
}

function get_server_log_directory() {
    echo "$LOGS_DIR/$1"
}

function get_server_log_file() {
    echo "$LOGS_DIR/$1/app.log"
}

function get_server_pid_directory() {
    echo "$LOGS_DIR/$1/pid"
}

function get_server_pid_file() {
    echo "$LOGS_DIR/$1/pid/$1.pid"
}

function get_server_directory() {
    echo "$BASE_DIR/../$1"
}

function func_check_running
{
    process_status="true"
    if [ -z $process_status ]; then
        echo "false"
    else
        echo "true"
    fi
}

function func_do_start
{
    pid=`nohup $BASE_DIR/../mvnw -f $( get_server_directory $1 )/pom.xml -P ${tracer} spring-boot:run  > \
    $( get_server_log_file $1 ) 2>&1 & echo $!`

    echo $pid > $( get_server_pid_file $1 )

    echo "---$1 initialization started. pid=$pid.---"g

    end_count=0
    check_running=$( func_check_running )
    while [ "$check_running" == "false" ]; do
        wait_time=`expr $end_count \* $UNIT_TIME`
        echo "starting $1. $wait_time /$CLOSE_WAIT_TIME sec(close wait limit)."

        if [ $end_count -ge $CHECK_COUNT ]; then
            break
        fi

        sleep $UNIT_TIME
        end_count=`expr $end_count + 1`

        check_running=$( func_check_running )
    done

    if [[ "$check_running" == "true" ]]; then
        echo "---$1 initialization completed. pid=$pid.---"
    else
        echo "---$1 initialization failed. pid=$pid.---"
        kill -9 $pid
    fi
}

function start_server() {
    func_init_log $1
    func_do_start $1
    echo ""
}

servers=`cat $BASE_DIR/servers 2>/dev/null`
for server in $servers; do
        start_server "${server}"
done