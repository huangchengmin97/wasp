/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.wasp.messagequeue;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.wasp.conf.WaspConfiguration;
import com.alibaba.wasp.fserver.EntityGroup;
import com.alibaba.wasp.storage.StorageActionManager;

/**
 * Subscriber builder.
 * 
 */
public class SubscriberBuilder {

  public static final Log LOG = LogFactory.getLog(SubscriberBuilder.class);

  public SubscriberBuilder() {
  }

  public Subscriber build(EntityGroup entityGroup) {
    try {
      return new Subscriber(entityGroup, new StorageActionManager(
          entityGroup.getConf()));
    } catch (IOException e) {
      LOG.error("new HBaseActionManager in Subscriber error", e);
      return null;
    }
  }

  public Subscriber build(EntityGroup entityGroup, StorageActionManager action) {
    return new Subscriber(entityGroup, action);
  }
}