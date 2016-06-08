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

import com.github.fedorchuck.appupdate.Folder;
import com.github.fedorchuck.appupdate.model.Response;
import org.junit.*;

import java.io.File;

import static com.github.fedorchuck.appupdate.Variables.DOWNLOAD_DIRECTORY;

public class ServerTest {
    private static Server server = new Server();

    @BeforeClass
    public static void before(){
        File trash = new File(DOWNLOAD_DIRECTORY);
        trash.deleteOnExit();
    }

    @Test
    public void get() throws Exception {
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

    @Test
    public void download() {
        File actual = new File(DOWNLOAD_DIRECTORY +"newDist.zip");
        if (actual.exists())
            //noinspection ResultOfMethodCallIgnored
            actual.delete();

        server.download("https://www.dropbox.com/s/r3re0nhvkwgbt6z/font-awesome-4.6.2.zip?dl=1");

        if (!actual.exists())
            Assert.fail();
        else
            Assert.assertTrue("Downloaded",true);
    }

    @Test
    public void completeServerTest() {
        File actual = new File(DOWNLOAD_DIRECTORY +"newDist.zip");
        if (actual.exists())
            //noinspection ResultOfMethodCallIgnored
            actual.delete();

        Response response = server.get("http://localhost:8080/client?token=d1945ee0-2c96-11e6-b67b-9e71128cae77&version=0.0.3");
        String url = response.getUrlToUpdate();
        server.download(url);

        if (!actual.exists())
            Assert.fail();
        else
            Assert.assertTrue("Downloaded",true);
    }

    @AfterClass
    public static void tearDown(){
        File trash = new File(DOWNLOAD_DIRECTORY +"newDist.zip");
        trash.deleteOnExit();
        trash = new File(DOWNLOAD_DIRECTORY);
        Folder.delete(trash);
    }
}