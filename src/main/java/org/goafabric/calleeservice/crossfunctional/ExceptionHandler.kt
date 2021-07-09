package org.goafabric.calleeservice.crossfunctional

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ExceptionHandler : ExceptionMapper<Exception> {
    private val log: Logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    override fun toResponse(e: Exception): Response {
        val status: Response.Status
        status = if (e is IllegalArgumentException) {
            Response.Status.PRECONDITION_FAILED
        } else if (e is IllegalStateException) {
            Response.Status.PRECONDITION_FAILED
        } else {
            Response.Status.BAD_REQUEST
        }
        log.error(e.message, e)
        return Response.status(status)
            .entity("an error occured: " + e.message).build()
    }
}