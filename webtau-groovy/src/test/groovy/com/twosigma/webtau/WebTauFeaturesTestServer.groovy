/*
 * Copyright 2018 TWO SIGMA OPEN SOURCE, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twosigma.webtau

import com.twosigma.webtau.http.testserver.TestServer
import com.twosigma.webtau.http.testserver.TestServerJsonResponse
import com.twosigma.webtau.http.testserver.TestServerResponse
import com.twosigma.webtau.utils.JsonUtils
import com.twosigma.webtau.utils.ResourceUtils

class WebTauFeaturesTestServer {
    private TestServer testServer

    WebTauFeaturesTestServer() {
        testServer = new TestServer()
        testServer.registerGet("/search", new TestServerHtmlResponse(ResourceUtils.textContent("search.html")))
        testServer.registerGet("/finders-and-filters", new TestServerHtmlResponse(ResourceUtils.textContent("finders-and-filters.html")))
        testServer.registerGet("/with-cookies", new TestServerHtmlResponse(ResourceUtils.textContent("cookies.html")))
        testServer.registerGet("/weather", new TestServerJsonResponse("{\"temperature\": 88}"))

        testServer.registerPost("/employee", json([id: 'id-generated-2']))
        testServer.registerGet("/employee/id-generated-2", json([firstName: 'FN', lastName: 'LN']))
    }

    void start(int port) {
        testServer.start(port)
    }

    void stop() {
        testServer.stop()
    }

    static void main(String[] args) {
        def testServer = new WebTauFeaturesTestServer()
        testServer.start(8180)
    }

    private static TestServerResponse json(Map response) {
        return new TestServerJsonResponse(JsonUtils.serialize(response))
    }
}