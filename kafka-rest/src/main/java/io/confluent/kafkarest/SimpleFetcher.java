/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.kafkarest;

import kafka.javaapi.FetchRequest;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.consumer.SimpleConsumer;

public class SimpleFetcher {

  private SimpleConsumer consumer;
  private SimpleConsumerPool ownerPool;

  public SimpleFetcher(SimpleConsumer consumer, SimpleConsumerPool ownerPool) {
    this.consumer = consumer;
    this.ownerPool = ownerPool;
  }

  public String clientId() {
    return consumer.clientId();
  }

  public FetchResponse fetch(final FetchRequest request) {
    return consumer.fetch(request);
  }

  public void close() throws Exception {
    ownerPool.release(this);
  }

}
