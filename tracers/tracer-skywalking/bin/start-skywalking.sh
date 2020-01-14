#!/usr/bin/env bash

SKYWALKING_VERSION=6.6.0
SKYWALKING_FILE=apache-skywalking-apm-$SKYWALKING_VERSION.tar.gz
SKYWALKING_UNTAR=apache-skywalking-apm-bin
SKYWALKING_DL_URL=http://ftp.cuhk.edu.hk/pub/packages/apache.org/skywalking/$SKYWALKING_VERSION/$SKYWALKING_FILE
SKYWALKING_ARCHIVE_DL_URL=http://archive.apache.org/dist/skywalking/$SKYWALKING_VERSION/$SKYWALKING_FILE

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

CONF_DIR=$BASE_DIR/conf
SKYWALKING_DIR=$BASE_DIR/install
DATA_DIR=$BASE_DIR/data

function func_check_skywalking_installation() {
  if [ -d $SKYWALKING_DIR/${SKYWALKING_UNTAR} ]; then
    echo "true"
  else
    echo "false"
  fi
}

function func_download_skywalking() {
  if type curl >/dev/null 2>&1; then
    if [[ $(curl -s --head $SKYWALKING_DL_URL | head -n 1 2>&1 | grep "HTTP/1.[01] [23]..") ]]; then
      curl -O $SKYWALKING_DL_URL
    else
      curl -O $SKYWALKING_ARCHIVE_DL_URL
    fi
    echo "true"
  elif type wget >/dev/null 2>&1; then
    if [[ $(wget -S --spider $FAIL_URL 2>&1 | grep "HTTP/1.[01] [23]..") ]]; then
      wget $SKYWALKING_DL_URL
    else
      wget $SKYWALKING_ARCHIVE_DL_URL
    fi
    echo "true"
  else
    echo "false"
  fi
}

function delete_data_directory() {
  if [ -d $DATA_DIR ]; then
    rm -r $DATA_DIR
  fi
}

function func_install_skywalking() {
  if [ ! -d $SKYWALKING_DIR ]; then
    mkdir $SKYWALKING_DIR
  fi
  cd $SKYWALKING_DIR
  echo "Downloading skywalking..."
  download_successful=$(func_download_skywalking)
  if [[ "$download_successful" == "false" ]]; then
    echo "skywalking download failed - wget or curl required."
    echo "Exiting"
    exit 0
  fi
  delete_data_directory
  func_install_to_local_repo $SKYWALKING_FILE
  tar xzf $SKYWALKING_FILE
  rm $SKYWALKING_FILE
  if [ -h skywalking ]; then
    unlink skywalking
  fi
  ln -s ${SKYWALKING_UNTAR} skywalking
  #    cp $CONF_DIR/hbase/hbase-site.xml $HBASE_DIR/$HBASE_VERSION/conf/
  chmod +x $SKYWALKING_DIR/${SKYWALKING_UNTAR}/bin/*.sh
}

function func_install_to_local_repo() {
    mvn org.apache.maven.plugins:maven-install-plugin:2.3.1:install-file -Dfile=$1 -DgroupId=apache.skywalking \
    -DartifactId=skywalking-apm-bin -Dversion=${SKYWALKING_VERSION} -Dpackaging=tar.gz
}

function func_start_skywalking() {
  skywalking_already_installed=$(func_check_skywalking_installation)
  if [[ "$skywalking_already_installed" == "true" ]]; then
    echo "Skywalking already installed. Starting skywalking..."
  else
    echo "Skywalking not detected."
    func_install_skywalking
  fi
  cd $SKYWALKING_DIR/$SKYWALKING_UNTAR/bin
  ./startup.sh
  cd $CURRENT_DIR
}

func_start_skywalking

