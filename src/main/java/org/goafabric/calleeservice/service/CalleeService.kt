package org.goafabric.calleeservice.service

import org.goafabric.calleeservice.logic.CalleeLogic
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/callees")
@RolesAllowed("standard_role")
@Produces(MediaType.APPLICATION_JSON)
class CalleeService(val calleeLogic: CalleeLogic) {
    @GET
    @Path("sayMyName")
    fun sayMyName(@QueryParam("name") name : String ) : Callee {
        return calleeLogic.sayMyName(name);
    }

    @GET
    @Path("sayMyOtherName/{name}")
    fun sayMyOtherName(@PathParam("name") name : String) : Callee {
        return calleeLogic.sayMyOtherName(name);
    }

    @GET
    @Path("setSleepTime")
    fun setSleepTime(@QueryParam("sleepTime") sleepTime : Long) : Callee {
        return calleeLogic.setSleepTime(sleepTime);
    }

}