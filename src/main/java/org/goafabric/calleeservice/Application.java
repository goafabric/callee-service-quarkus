package org.goafabric.calleeservice;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

@QuarkusMain
@Slf4j
public class Application {

    public static void main(String... args) {
        Quarkus.run(args);
        log.info("Hello from Quarkus");
    }

}
