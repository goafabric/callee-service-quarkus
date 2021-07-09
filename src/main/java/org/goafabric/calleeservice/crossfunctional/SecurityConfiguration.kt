package org.goafabric.calleeservice.crossfunctional

import io.quarkus.security.spi.runtime.AuthorizationController
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.annotation.Priority
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Alternative
import javax.interceptor.Interceptor

@Alternative
@Priority(Interceptor.Priority.LIBRARY_AFTER)
@ApplicationScoped
class SecurityConfiguration(
    @ConfigProperty(name = "security.authentication.enabled", defaultValue = "true")
    private val authorizationEnabled : Boolean) : AuthorizationController() {

    override fun isAuthorizationEnabled(): Boolean {
        return this.authorizationEnabled
    }
}