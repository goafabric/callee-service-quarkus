package org.goafabric.calleeservice.service;

import org.goafabric.calleeservice.logic.CalleeLogic;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/callees")
public class CalleeService {
    @Inject
    private CalleeLogic calleeLogic;

    @GET
    @Path("/isAlive")
    //Produces APPLICATION_JSON not allowed here
    public Boolean isAlive() {
        return calleeLogic.isAlive();
    }

    @GET
    @Path("/sayMyName")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayMyName(@QueryParam("name") String name) {
        return calleeLogic.sayMyName(name);
    }

    @GET
    @Path("/sayMyOtherName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayMyOtherName(@PathParam("name") String name) {
        return calleeLogic.sayMyOtherName(name);
    }
}