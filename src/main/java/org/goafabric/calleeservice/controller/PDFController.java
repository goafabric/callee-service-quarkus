package org.goafabric.calleeservice.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.goafabric.calleeservice.logic.OpenPDFCreatorLogic;

@Path("/pdfs")
@RolesAllowed("standard_role")
@Produces(MediaType.APPLICATION_JSON)
public class PDFController {
    private final OpenPDFCreatorLogic openPdfCreatorLogic;

    public PDFController(OpenPDFCreatorLogic openPdfCreatorLogic) {
        this.openPdfCreatorLogic = openPdfCreatorLogic;
    }

    @GET
    @Path("createpdf")
    public Response createPdf() {
        return Response.ok(openPdfCreatorLogic.create())
                .type("application/pdf")
                .header("Content-Disposition", "inline; filename=\"your-file.pdf\"")
                .build();
    }
}
