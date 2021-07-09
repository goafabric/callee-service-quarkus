package org.goafabric.calleeservice

import io.quarkus.runtime.annotations.QuarkusMain
import kotlin.jvm.JvmStatic
import io.quarkus.runtime.Quarkus
import lombok.extern.slf4j.Slf4j

@QuarkusMain
object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Quarkus.run(*args)
    }
}