package org.goafabric.calleeservice.logic;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayOutputStream;

@ApplicationScoped
public class OpenPDFCreatorLogic {

    public byte[] create() {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        //create a PDF writer instance and pass output streamtry {
        var out = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, out); //new FileOutputStream("./helloworld.pdf"));

        document.open();
        document.addAuthor("Author-Name");
        document.addCreationDate();

        document.add(new Paragraph("Hello World -- Page 1"));
        document.add(new Paragraph("This is my first PDF."));

        document.newPage();

        document.add(new Paragraph("Hello World -- Page 2"));
        document.close();
        return out.toByteArray();
    }
}
