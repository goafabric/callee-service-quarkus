package org.goafabric.calleeservice.service;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusIntegrationTest
public class CalleeServiceIT {
    @Inject
    @RestClient
    CalleeServiceAdapter calleeServiceAdapter;

    @Test
    public void sayMyName() {
        calleeServiceAdapter.sayMyName("Heisenberg");
    }

    @Test
    public void sayMyOtherName() {
        calleeServiceAdapter.sayMyOtherName("Heisenberg");
    }

    @Test
    public void setSleepTime() {
        calleeServiceAdapter.setSleepTime(0L);
    }
}