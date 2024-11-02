package org.goafabric.calleeservice.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.goafabric.calleeservice.logic.CalleeLogic;
import org.goafabric.calleeservice.logic.PDFCreator;

@Path("/callees")
@RolesAllowed("standard_role")
@Produces(MediaType.APPLICATION_JSON)
public class CalleeController {
    private final CalleeLogic calleeLogic;
    @Inject
    PDFCreator pdfCreator;

    public CalleeController(CalleeLogic calleeLogic) {
        this.calleeLogic = calleeLogic;
    }

    @GET
    @Path("sayMyName")
    public Callee sayMyName(@QueryParam("name") String name) {
        pdfCreator.create();
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

    @GET
    @Path("createpdf")
    public Response createPdf() {
        return Response.ok(pdfCreator.create())
                .type("application/pdf")
                .header("Content-Disposition", "inline; filename=\"your-file.pdf\"")
                .build();
    }
}
