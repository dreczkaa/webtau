/*
 * Copyright 2020 webtau maintainers
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

package scenarios

import static org.testingisdocumenting.webtau.WebTauGroovyDsl.*
import static webtau.CliCommands.*

def repl = createLazyResource("repl") {
    return [
        process: webtauCli.runInBackground("repl")
    ]
}

scenario('simple groovy repl') {
    repl.process.send("2 + 2\n")

    repl.process.output.waitTo contain("4")

    repl.process.output.should contain("4")
    repl.process.clearOutput()
    repl.process.output.shouldNot contain("4")

    repl.process.send("cfg\n")
    repl.process.output.waitTo contain("url:")
}