#!/usr/bin/env bash
#
#/**
# * Copyright 2007 The Apache Software Foundation
# *
# * Licensed to the Apache Software Foundation (ASF) under one
# * or more contributor license agreements.  See the NOTICE file
# * distributed with this work for additional information
# * regarding copyright ownership.  The ASF licenses this file
# * to you under the Apache License, Version 2.0 (the
# * "License"); you may not use this file except in compliance
# * with the License.  You may obtain a copy of the License at
# *
# *     http://www.apache.org/licenses/LICENSE-2.0
# *
# * Unless required by applicable law or agreed to in writing, software
# * distributed under the License is distributed on an "AS IS" BASIS,
# * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# * See the License for the specific language governing permissions and
# * limitations under the License.
# */
# 
# Run a wasp command on all slave hosts.
# Modelled after $HBASE_HOME/bin/hbase-daemons.sh

usage="Usage: wasp-daemons.sh [--config <wasp-confdir>] \
 [--hosts fserversfile] [start|stop] command args..."

# if no args specified, show usage
if [ $# -le 1 ]; then
  echo $usage
  exit 1
fi

bin=`dirname "${BASH_SOURCE-$0}"`
bin=`cd "$bin">/dev/null; pwd`

. $bin/wasp-config.sh

remote_cmd="cd ${WASP_HOME}; $bin/wasp-daemon.sh --config ${WASP_CONF_DIR} $@"
args="--hosts ${WASP_FSERVERS} --config ${WASP_CONF_DIR} $remote_cmd"

command=$2
case $command in
  (zookeeper)
    exec "$bin/zookeepers.sh" $args
    ;;
  (master-backup)
    exec "$bin/master-backup.sh" $args
    ;;
  (*)
    exec "$bin/fservers.sh" $args
    ;;
esac

