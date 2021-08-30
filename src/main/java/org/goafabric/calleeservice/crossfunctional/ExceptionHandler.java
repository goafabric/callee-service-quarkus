package org.goafabric.calleeservice.crossfunctional;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        final Response.Status status;
        if (e instanceof IllegalArgumentException) {
            status = Response.Status.PRECONDITION_FAILED;
        } else if (e instanceof IllegalStateException) {
            status = Response.Status.PRECONDITION_FAILED;
        } else {
            status = Response.Status.SERVICE_UNAVAILABLE;
        }
        
        log.error(e.getMessage(), e);
        return Response.status(status)
                .entity("an error occured: " + e.getMessage()).build();
    }
}
