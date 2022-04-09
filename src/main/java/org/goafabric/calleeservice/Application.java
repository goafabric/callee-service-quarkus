package org.goafabric.calleeservice;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.security.spi.runtime.AuthorizationController;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

@QuarkusMain
@Slf4j
public class Application {

    public static void main(String... args) {
        Quarkus.run(args);
    }

    @Getter
    @Alternative
    @ApplicationScoped @Priority(Interceptor.Priority.LIBRARY_AFTER)
    static class SecurityConfiguration extends AuthorizationController {
        @ConfigProperty(name = "security.authentication.enabled", defaultValue = "true")
        boolean isAuthorizationEnabled;
    }
}
