package org.goafabric.calleeservice.extensions

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Provider
class ExceptionHandler : ExceptionMapper<Exception> {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun toResponse(e: Exception): Response {
        val status: Response.Status
        = when (e) {
            is IllegalArgumentException -> {
                Response.Status.PRECONDITION_FAILED
            }
            is IllegalStateException -> {
                Response.Status.PRECONDITION_FAILED
            }
            else -> {
                Response.Status.INTERNAL_SERVER_ERROR
            }
        }

        log.error(e.message, e)
        return Response.status(status)
            .entity("An error occured: " + e.message).build()    }
}
