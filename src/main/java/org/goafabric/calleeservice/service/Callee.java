package org.goafabric.calleeservice.service;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Callee {
    private String message;
}
