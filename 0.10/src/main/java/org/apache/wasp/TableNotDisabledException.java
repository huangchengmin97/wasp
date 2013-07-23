/**
 *
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
package org.apache.wasp;

import org.apache.hadoop.hbase.DoNotRetryIOException;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Thrown if a table should be offline but is not
 */
public class TableNotDisabledException extends DoNotRetryIOException {

  private static final long serialVersionUID = -3138166767515171860L;

  /** default constructor */
  public TableNotDisabledException() {
    super();
  }

  /**
   * Constructor
   * 
   * @param s
   *          message
   */
  public TableNotDisabledException(String s) {
    super(s);
  }

  /**
   * @param tableName
   *          Name of table that is not disabled
   */
  public TableNotDisabledException(byte[] tableName) {
    this(Bytes.toString(tableName));
  }
}