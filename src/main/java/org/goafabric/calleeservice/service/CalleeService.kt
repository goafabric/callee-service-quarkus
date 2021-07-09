package org.goafabric.calleeservice.service

import org.goafabric.calleeservice.logic.CalleeLogic
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

@ApplicationScoped
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