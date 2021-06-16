package org.goafabric.calleeservice.service;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
//@QuarkusIntegrationTest //see https://quarkus.io/guides/getting-started-testing
public class NativeCalleeServiceIT extends CalleeServiceTest {

    // Execute the same tests but in native mode.
}