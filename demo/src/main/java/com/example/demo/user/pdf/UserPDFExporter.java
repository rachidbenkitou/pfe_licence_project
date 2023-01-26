package com.example.demo.user.pdf;

import com.example.demo.user.entity.User;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserPDFExporter {
    private List<User> userList;



    public UserPDFExporter(List<User> userList) {
        this.userList = userList;
    }

    private void writeTableHeader(PdfPTable table) throws BadElementException, IOException {
        Image logo=Image.getInstance("C:\\application_web_gestion_entretiens_produits_mobilités\\logo.png");
        logo.scaleAbsolute(50,50);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        font.setSize(9);

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Username", font));
        table.addCell(cell);



        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);


    }
    private void writeTableData(PdfPTable table) throws BadElementException, IOException {

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        font.setSize(9);

        for (User user : userList) {
            table.addCell(new Phrase(String.valueOf(user.getUser_id()),font));
            table.addCell(new Phrase(String.valueOf(user.getUsername()),font));

            table.addCell(new Phrase(String.valueOf(user.getRoles()),font));

        }
    }




    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Image logo=Image.getInstance("C:\\application_web_gestion_entretiens_produits_mobilités\\logo.png");
        logo.scaleAbsolute(75,65);
        logo.setAbsolutePosition(80,750);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.darkGray);

        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(7);
        font1.setColor(Color.darkGray);

        Paragraph p1 = new Paragraph("\n\n\n\n\nAcadémie des Arts Traditionnels de Casablanca", font1);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p = new Paragraph("\n\nLa liste des utilisateurs", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);


        Paragraph p2 = new Paragraph("\n\n\n\n\n\n\n\n\n\nSignature :", font1);
        p2.setAlignment(-100);
        p2.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p3 = new Paragraph("\n\nDirecteur :", font1);
        p3.setAlignment(-100);
        p3.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p4 = new Paragraph("\n\nChef de Service :", font1);
        p4.setAlignment(-100);
        p4.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p5 = new Paragraph("\n\nChef de Departemet :", font1);
        p5.setAlignment(-100);
        p5.setAlignment(Element.ALIGN_BOTTOM);


        document.add(logo);
        document.add(p1);
        document.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);table.setWidths(new float[] {2f,2f,2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
        document.close();

    }
}
