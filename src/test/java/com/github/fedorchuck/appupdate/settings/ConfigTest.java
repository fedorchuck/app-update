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

package com.github.fedorchuck.appupdate.settings;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.*;

public class ConfigTest {
    @Test
    public void get() throws Exception {
        Config config = new Config();
        String path = new File("").getAbsolutePath()+"/src/test/java/com/github/fedorchuck/appupdate/resources/config";
        Map<String, List<String>> actual = config.get(path);
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("com.github.fedorchuck.appupdate.doNotDelete:",
                Arrays.asList("/home/example/app/config","/home/example/app/database"));
        expected.put("com.github.fedorchuck.appupdate.delay:", Collections.singletonList("9000"));
        expected.put("com.github.fedorchuck.appupdate.serverUrl:",
                Collections.singletonList("http://localhost:8080/client?token=d1945ee0-2c96-11e6-b67b-9e71128cae77&version=0.0.3"));
        Assert.assertEquals(expected,actual);
        path = new File("").getAbsolutePath()+"/src/test/java/com/github/fedorchuck/appupdate/resources/bad-config";
        actual = config.get(path);
        Assert.assertEquals(expected,actual);
    }

}