package org.goafabric.calleeservice

import io.quarkus.runtime.annotations.QuarkusMain
import kotlin.jvm.JvmStatic
import io.quarkus.runtime.Quarkus

@QuarkusMain
object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Quarkus.run(*args)
    }
}