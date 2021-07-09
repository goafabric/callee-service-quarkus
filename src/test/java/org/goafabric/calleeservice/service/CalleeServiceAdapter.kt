package org.goafabric.calleeservice.service

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import org.goafabric.calleeservice.service.Callee
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

@Path("/callees")
@RegisterRestClient
interface CalleeServiceAdapter {
    @GET
    @Path("sayMyName")
    fun sayMyName(@QueryParam("name") name: String): Callee

    @GET
    @Path("sayMyOtherName/{name}")
    fun sayMyOtherName(@PathParam("name") name: String): Callee

    @GET
    @Path("setSleepTime")
    fun setSleepTime(@QueryParam("sleepTime") sleepTime: Long): Callee
}