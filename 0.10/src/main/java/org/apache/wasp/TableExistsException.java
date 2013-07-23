/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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


/**
 * Thrown when a table exists but should not
 */
public class TableExistsException extends ClientConcernedException {
  private static final long serialVersionUID = 1L << 7 - 1L;

  /** default constructor */
  public TableExistsException() {
    super();
  }

  /**
   * Constructor
   * 
   * @param s
   *          message
   */
  public TableExistsException(String s) {
    super(s);
  }

  /**
   * @see org.apache.wasp.ClientConcernedException#getErrorCode()
   */
  @Override
  public int getErrorCode() {
    return SQLErrorCode.GENERAL_ERROR_1;
  }
}