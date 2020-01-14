#!/usr/bin/env bash

BASEDIR=$(dirname "$0")
PINPOINT_DIR=${BASEDIR}/../tracers/tracer-pinpoint

${PINPOINT_DIR}/bin/stop-collector.sh
${PINPOINT_DIR}/bin/stop-web.sh
${PINPOINT_DIR}/bin/stop-hbase.sh

sleep 3s

${PINPOINT_DIR}/bin/start-hbase.sh
${PINPOINT_DIR}/bin/init-hbase.sh
${PINPOINT_DIR}/bin/start-collector.sh
${PINPOINT_DIR}/bin/start-web.sh