package org.goafabric.calleeservice.logic;

import com.lowagie.text.Anchor;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@ApplicationScoped
public class PDFCreator {

    public void create()  {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // step 1: creation of a document-object
            Document document = new Document();
            // step 2:
            // we create 3 different writers that listen to the document
            PdfWriter pdf = PdfWriter.getInstance(document, baos);

            // step 3: we open the document
            document.open();
            // step 4: we add a paragraph to the document
            document.add(new Paragraph("Hello World"));
            // we make references
            Anchor pdfRef = new Anchor("see Hello World in PDF.");
            pdfRef.setReference(".//HelloWorldPdf.pdf");


            // we add the references, but only to the HTML page:

            pdf.pause();
            document.add(pdfRef);
            document.add(Chunk.NEWLINE);
            pdf.resume();

            // step 5: we close the document
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
