#!/usr/bin/env bash

BASEDIR=$(dirname "$0")
SKYWALKING_DIR=${BASEDIR}/../tracers/tracer-skywalking

${SKYWALKING_DIR}/bin/stop-skywalking.sh

sleep 3s

${SKYWALKING_DIR}/bin/start-skywalking.sh