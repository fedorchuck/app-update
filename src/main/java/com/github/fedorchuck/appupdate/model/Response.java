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

package com.github.fedorchuck.appupdate.model;

import java.util.Objects;

@SuppressWarnings("ALL")
public class Response {
	private String accessToken;
	private String vercion;
    private String urlToUpdate;
    private String message;
    private String documentationUrl;

    public Response() {
    }

    public Response(String accessToken, String vercion, String urlToUpdate, String message, String documentationUrl) {
        this.accessToken = accessToken;
        this.vercion = vercion;
        this.urlToUpdate = urlToUpdate;
        this.message = message;
        this.documentationUrl = documentationUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getVercion() {
        return vercion;
    }

    public void setVercion(String vercion) {
        this.vercion = vercion;
    }

    public String getUrlToUpdate() {
        return urlToUpdate;
    }

    public void setUrlToUpdate(String urlToUpdate) {
        this.urlToUpdate = urlToUpdate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(accessToken, response.accessToken) &&
                Objects.equals(vercion, response.vercion) &&
                Objects.equals(urlToUpdate, response.urlToUpdate) &&
                Objects.equals(message, response.message) &&
                Objects.equals(documentationUrl, response.documentationUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, vercion, urlToUpdate, message, documentationUrl);
    }

    @Override
    public String toString() {
        return "Response{" +
                "accessToken='" + accessToken + '\'' +
                ", vercion='" + vercion + '\'' +
                ", urlToUpdate='" + urlToUpdate + '\'' +
                ", message='" + message + '\'' +
                ", documentationUrl='" + documentationUrl + '\'' +
                '}';
    }
}
