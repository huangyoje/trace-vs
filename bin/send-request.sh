#!/usr/bin/env bash

curl http://127.0.0.1:9802/echo\?param\=hello

curl http://127.0.0.1:9802/current-mysql-time

curl http://127.0.0.1:9802/send-kafka\?param\=hello