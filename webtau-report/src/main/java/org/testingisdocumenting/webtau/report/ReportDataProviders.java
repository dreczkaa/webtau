/*
 * Copyright 2019 TWO SIGMA OPEN SOURCE, LLC
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

package org.testingisdocumenting.webtau.report;

import org.testingisdocumenting.webtau.reporter.WebTauTestList;
import org.testingisdocumenting.webtau.utils.ServiceLoaderUtils;

import java.util.List;
import java.util.stream.Stream;

public class ReportDataProviders {
    private static final List<ReportDataProvider> providers = ServiceLoaderUtils.load(ReportDataProvider.class);

    public static Stream<ReportCustomData> provide(WebTauTestList tests) {
        return providers.stream().flatMap(e -> e.provide(tests));
    }
}
