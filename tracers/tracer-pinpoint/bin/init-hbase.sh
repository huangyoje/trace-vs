#!/usr/bin/env bash

_bin=`dirname "${BASH_SOURCE-$0}"`
_bin=`cd "$_bin">/dev/null; pwd`

echo "init hbase ..."
quickstart_base=$_bin/..
quickstart_base=`cd "$quickstart_base">/dev/null; pwd`

"$_bin"/../hbase/hbase/bin/hbase shell $quickstart_base/conf/hbase/init-hbase.txt