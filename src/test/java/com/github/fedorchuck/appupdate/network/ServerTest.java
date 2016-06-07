/*
 * Copyright [2016] [Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>]
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

package com.github.fedorchuck.appupdate.network;

import com.github.fedorchuck.appupdate.model.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void get() throws Exception {
        Server server = new Server();
        Response actual = server.get("http://localhost:8080/client?token=d19455ee-2c96-11e6-b67b-9e71128cae77&version=0.0.1");
        Response expected = new Response("d1945ce2-2c96-11e6-b67b-9e71128cae77","0.0.2","some url","OK","link to doc");
        Assert.assertEquals(expected,actual);
        actual = server.get("http://localhost:8080/client?token=d19455ee-0000-11e6-b67b-9e71128cae77&version=0.0.1");
        expected = new Response("","","","bad token","doc url");
        Assert.assertEquals(expected,actual);
        actual = server.get("http://example.com");
        expected = new Response("","","","check url","doc url");
        Assert.assertEquals(expected,actual);
    }

}