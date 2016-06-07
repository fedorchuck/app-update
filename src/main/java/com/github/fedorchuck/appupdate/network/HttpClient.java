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

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class HttpClient {

    @SuppressWarnings("unused")
    @Deprecated
    InputStreamReader getDataFromSource(String url){
        try {
            return new InputStreamReader(getSource(url).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param urlToRead the {@link String} to parse as a URL.
     * @return httpURLConnection - connection for web-source.
     * @throws MalformedURLException if bad url
     * @throws ProtocolException problem with protocol
     * @throws IOException if cannot get source by {@param urlToRead}.
     */
    @SuppressWarnings("DuplicateThrows")
    HttpURLConnection getSource(String urlToRead) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(urlToRead);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/4.76");
        httpURLConnection.setRequestMethod("GET");
        return httpURLConnection;
    }
}