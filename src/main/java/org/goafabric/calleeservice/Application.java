package org.goafabric.calleeservice;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Application {

    public static void main(String... args) {
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            if ((args.length > 0) && ("-check-integrity".equals(args[0]))) { Quarkus.asyncExit(); }
            Quarkus.waitForExit();
            return 0;
        }
    }
}
