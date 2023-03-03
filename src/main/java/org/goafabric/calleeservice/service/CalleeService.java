package org.goafabric.calleeservice.service;

import org.goafabric.calleeservice.logic.CalleeLogic;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/callees")
@RolesAllowed("standard_role")
@Produces(MediaType.APPLICATION_JSON)
public class CalleeService {
    private final CalleeLogic calleeLogic;

    public CalleeService(CalleeLogic calleeLogic) {
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
