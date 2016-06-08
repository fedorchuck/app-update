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

import com.github.fedorchuck.appupdate.update.Folder;
import com.github.fedorchuck.appupdate.log.Log;
import com.github.fedorchuck.appupdate.model.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.net.*;

import static com.github.fedorchuck.appupdate.Variables.DOWNLOAD_DIRECTORY;
import static com.github.fedorchuck.appupdate.Variables.DOWNLOAD_DIST;
import static com.github.fedorchuck.appupdate.log.Level.*;

public class Server {
    private Log log = new Log(this.getClass());

    /**
     * @param url to server, example
     * {@code http://localhost:8080/client?token=d19455ee-2c96-11e6-b67b-9e71128cae77&version=0.0.1}
     * @return {@link Response} from server.
     */
    public Response get(String url) {
        log.write("ask server: " + url,INFO);

        HttpClient httpClient = new HttpClient();
        Gson gson = new Gson();

        Response response = null;
        //noinspection TryWithIdenticalCatches
        try {
            InputStreamReader data = new InputStreamReader(httpClient.getSource(url).getInputStream());
            BufferedReader br = new BufferedReader(data);
            response = gson.fromJson(br, Response.class);
        } catch (ProtocolException e) {
            log.write(e, ERROR);
        } catch (MalformedURLException e) {
            log.write(e, ERROR);
        } catch (IOException e) {
            log.write(e, ERROR);
        } catch (IllegalStateException e) {
            log.write(e, ERROR);
        } catch (JsonSyntaxException e) {
            log.write(e, ERROR);
            response = new Response("","","","check url","doc url");
        }
        return response;
    }

    public void download(String zipUrl){
        log.write("Start download new dis. from: "+zipUrl,INFO);
        log.write("Destination directory: "+ DOWNLOAD_DIRECTORY,INFO);

        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(zipUrl);
            URLConnection urlCon = url.openConnection();

            System.out.println(urlCon.getContentType());
            log.write("content type: "+urlCon.getContentType(),INFO);

            Folder.replaceOrCreate(DOWNLOAD_DIRECTORY);

            is = urlCon.getInputStream();
            fos = new FileOutputStream(DOWNLOAD_DIRECTORY+DOWNLOAD_DIST);

            byte[] buffer = new byte[1000];
            int bytesRead = is.read(buffer);

            while (bytesRead > 0) {
                fos.write(buffer, 0, bytesRead);
                bytesRead = is.read(buffer);
            }

        } catch (FileNotFoundException e) {
            log.write(e,ERROR);
        } catch (IOException e) {
            log.write(e,ERROR);
            //TODO: delete destDir
            log.write(e,FATAL);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ignored) {}
        }

    }

    public boolean validate(Response response) {
        URL url;

        try {
            url = new URL(response.getUrlToUpdate());
        } catch (MalformedURLException e) {
            return false;
        }

        try {
            url.toURI();
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
    }
}