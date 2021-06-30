package org.goafabric.calleeservice.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/callees")
@RegisterRestClient(baseUri = "http://localhost:50900")
public interface CalleeServiceAdapter {
    @GET
    @Path("sayMyName")
    Callee sayMyName(@QueryParam("name") String name);

    @GET
    @Path("sayMyOtherName/{name}")
    Callee sayMyOtherName(@PathParam("name") String name);

    @GET
    @Path("setSleepTime")
    Callee setSleepTime(@QueryParam("sleepTime") Long sleepTime);
}
