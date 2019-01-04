/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc.protocol.thrift;

import mongoutil.data.MongoQueryData;
import org.apache.dubbo.rpc.gen.thrift.*;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;

public class DemoImpl {

  public boolean echoBool(boolean arg) {

    return arg;
  }

  public byte echoByte(byte arg) {

    return arg;
  }

  public short echoI16(short arg) {

    return arg;
  }

  public int echoI32(int arg) {

    return arg;
  }

  public long echoI64(long arg) {

    return arg;
  }

  public double echoDouble(double arg) {

    return arg;
  }

  public String echoString(String arg) {

    return arg;
  }


  public Verify echoDemoData(DemoData arg) {
    System.out.println("Provider2");
    arg.group = "return group";
    arg.group2 = "return group2";
    arg.demoStruct.group = "return group";
    arg.demoStruct.group2 = "return group2";

    Bson query =
      and(
        eq("mobile", "13761098891"),
        eq("itu", "+86")
      );
    Verify verify = null;
    try {
      verify = (Verify) MongoQueryData.getObject(
        "mongodb://mms:Palm2017Drive@staging:57055/mms?ssl=true",
        "mms",
        "verify",
        query,
        Verify.class
      );
      System.out.println(verify.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return verify;
  }
}
