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

import com.github.fedorchuck.appupdate.log.Level;
import com.github.fedorchuck.appupdate.log.Log;
import com.github.fedorchuck.appupdate.model.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class Server {
    private Log log = new Log(this.getClass());

    /**
     * @param url to server, example
     * {@code http://localhost:8080/client?token=d19455ee-2c96-11e6-b67b-9e71128cae77&version=0.0.1}
     * @return {@link Response} from server.
     */
    public Response get(String url) {
        log.write("ask server: " + url,Level.INFO);

        HttpClient httpClient = new HttpClient();
        Gson gson = new Gson();

        Response response = null;
        //noinspection TryWithIdenticalCatches
        try {
            InputStreamReader data = new InputStreamReader(httpClient.getSource(url).getInputStream());
            BufferedReader br = new BufferedReader(data);
            response = gson.fromJson(br, Response.class);
        } catch (ProtocolException e) {
            log.write(e, Level.ERROR);
        } catch (MalformedURLException e) {
            log.write(e, Level.ERROR);
        } catch (IOException e) {
            log.write(e, Level.ERROR);
        } catch (IllegalStateException e) {
            log.write(e, Level.ERROR);
        } catch (JsonSyntaxException e) {
            log.write(e, Level.ERROR);
            response = new Response("","","","check url","doc url");
        }
        return response;
    }
}