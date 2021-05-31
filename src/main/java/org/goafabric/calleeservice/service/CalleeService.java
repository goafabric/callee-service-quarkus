package org.goafabric.calleeservice.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/callees")
public class CalleeService {

    @GET
    @Path("/isAlive")
    //Produces APPLICATION_JSON not allowed here
    public Boolean isAlive() {
        return Boolean.TRUE;
    }

    @GET
    @Path("/sayMyName")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayMyName(@QueryParam("name") String name) {
        return name;
    }

    @GET
    @Path("/sayMyOtherName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayMyOtherName(@PathParam("name") String name) {
        return name;
    }
}
