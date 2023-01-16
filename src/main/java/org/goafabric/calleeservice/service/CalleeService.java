package org.goafabric.calleeservice.service;

import io.quarkus.security.Authenticated;
import org.goafabric.calleeservice.logic.CalleeLogic;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/callees")
//@RolesAllowed("standard")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
public class CalleeService {
    @Inject
    CalleeLogic calleeLogic;

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
