/*
 * Copyright 2020 webtau maintainers
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

package org.testingisdocumenting.webtau.featuretesting

import org.junit.BeforeClass
import org.junit.Test

import static org.testingisdocumenting.webtau.cli.CliTestUtils.supportedPlatformOnly
import static org.testingisdocumenting.webtau.featuretesting.FeaturesDocArtifactsExtractor.extractCodeSnippets

class WebTauCliFeaturesTest {
    private static WebTauEndToEndTestRunner testRunner

    @BeforeClass
    static void init() {
        testRunner = new WebTauEndToEndTestRunner()
    }

    @Test
    void "ls example"() {
        runCli('basicLs.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "foreground examples"() {
        runCli('foregroundExamples.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "foreground examples extract snippets"() {
        extractCodeSnippets(
                'foreground-cli', 'examples/scenarios/cli/foregroundExamples.groovy', [
                'withoutValidation.groovy': 'command without validation',
                'withOutputValidation.groovy': 'command with output validation',
                'withErrorValidation.groovy': 'command with error validation',
                'withExitCodeValidation.groovy': 'command with exit code validation',
                'implicitExitCodeBehindScenes.groovy': 'command implicit exit code check explicitly',
                'runResult.groovy': 'command run result'
        ])
    }

    @Test
    void "background examples"() {
        runCli('backgroundExamples.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "background examples extract snippets"() {
        extractCodeSnippets(
                'background-cli', 'examples/scenarios/cli/backgroundExamples.groovy', [
                'run.groovy': 'run in background',
                'runAndStop.groovy': 'run and stop'
        ])

        extractCodeSnippets(
                'background-send-cli', 'examples/scenarios/cli/sendInput.groovy', [
                'sendInput.groovy': 'send input',
                'sendInputShiftLeft.groovy': 'send input with shift left'
        ])
    }


    @Test
    void "simple script run"() {
        runCli('simpleRun.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "path based script run"() {
        runCli('pathBasedScript.groovy', 'webtau-with-path.cfg.groovy')
    }

    @Test
    void "error script run"() {
        runCli('errorRuns.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "implicit exit code"() {
        runCli('implicitExitCodeCheck.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "wait for output"() {
        runCli('outputWait.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "run config"() {
        runCli('cliRunConfig.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "run config extract snippets"() {
        extractCodeSnippets(
                'foreground-cli-cfg', 'examples/scenarios/cli/cliRunConfig.groovy', [
                'workingDir.groovy': 'working dir',
                'envVar.groovy': 'environment var',
                'envVarAndWorkingDir.groovy': 'env var and working dir'
        ])
    }

    @Test
    void "send input"() {
        runCli('sendInput.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "multi send input"() {
        runCli('multiTestSendInput.groovy', 'webtau.cfg.groovy')
    }

    @Test
    void "background command auto kill"() {
        runCli('backgroundCommandAutoKill.groovy', 'webtau-cli-before-first-test.cfg.groovy')
    }

    private static void runCli(String restTestName, String configFileName, String... additionalArgs) {
        supportedPlatformOnly {
            testRunner.runCli("scenarios/cli/$restTestName",
                    "scenarios/cli/$configFileName", additionalArgs)
        }
    }
}
