package com.example.demo.pv.pdf;


import com.example.demo.entretien.entity.Entretien;
import com.example.demo.pv.entity.Pv;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.aspectj.weaver.Position;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PvPDFExporter {
    private List<Pv> pvList;



    public PvPDFExporter(java.util.List<Pv> pvList) {
        this.pvList = pvList;
    }

    private void writeTableHeader(PdfPTable table) throws BadElementException, IOException {
        Image logo=Image.getInstance("C:\\application_web_gestion_entretiens_produits_mobilités\\logo.png");
        logo.scaleAbsolute(50,50);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(10);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        font.setSize(9);

        cell.setPhrase(new Phrase("Numero PV", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NUMERO PRODUIT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NUMERO ETUDIANT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NOTE", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DECISION", font));
        table.addCell(cell);



        cell.setPhrase(new Phrase("PRIX", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DESCRIPTION", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DATE", font));
        table.addCell(cell);


    }
    private void writeTableData(PdfPTable table) throws BadElementException, IOException {

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        font.setSize(9);

        for (Pv pv : pvList) {
            table.addCell(new Phrase(String.valueOf(pv.getId_pv()),font));
            table.addCell(new Phrase(String.valueOf(pv.getId_produit()),font));
            table.addCell(new Phrase(String.valueOf(pv.getId_etudiant()),font));
            table.addCell(new Phrase(String.valueOf(pv.getNote()),font));
            table.addCell(new Phrase(pv.getDecision(),font));
            table.addCell(new Phrase(String.valueOf(pv.getPrix()),font));
            table.addCell(new Phrase(pv.getDescription(),font));
            table.addCell(new Phrase(pv.getDate_pv(),font));




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
        Paragraph p = new Paragraph("\n\nLa liste des procés verbals", font);
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

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);table.setWidths(new float[] {1.5f,1.5f,1.5f,1.5f,1.5f,1.5f,2f,2f});
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
