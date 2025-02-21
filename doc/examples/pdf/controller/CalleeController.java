package org.goafabric.calleeservice.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.goafabric.calleeservice.logic.CalleeLogic;

@Path("/callees")
@RolesAllowed("standard_role")
@Produces(MediaType.APPLICATION_JSON)
public class CalleeController {
    private final CalleeLogic calleeLogic;

    public CalleeController(CalleeLogic calleeLogic) {
        this.calleeLogic = calleeLogic;
    }

    @GET
    @Path("sayMyName")
    public Callee sayMyName(@QueryParam("name") String name) {
        return calleeLogic.sayMyName(name);
    }

    @GET
    @Path("sayMyOtherName/{name}")
    public Callee sayMyOtherName(@PathParam("name") String name) {
        return calleeLogic.sayMyOtherName(name);
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Callee save(Callee callee) {
        return calleeLogic.save(callee);
    }

}
