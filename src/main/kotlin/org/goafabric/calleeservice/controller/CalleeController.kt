package org.goafabric.calleeservice.controller

import io.quarkiverse.mcp.server.Tool
import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.goafabric.calleeservice.controller.dto.Callee
import org.goafabric.calleeservice.logic.CalleeLogic

@Path("/callees")
@RolesAllowed("standard_role")
@Produces(MediaType.APPLICATION_JSON)
class CalleeController(private val calleeLogic: CalleeLogic) {
    @GET
    @Path("sayMyName")
    @Tool(description = "say my name")
    fun sayMyName(@QueryParam("name") name: String): Callee {
        return calleeLogic.sayMyName(name)
    }

    @GET
    @Path("sayMyOtherName/{name}")
    @Tool(description = "say my other name")
    fun sayMyOtherName(@PathParam("name") name: String): Callee {
        return calleeLogic.sayMyOtherName(name)
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    fun save(callee: Callee): Callee {
        return calleeLogic.save(callee)
    }
}
