package com.example.demo.user.pdf;

import com.example.demo.entretien.entity.Entretien;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class EntretienPDFExporter1Element {

    private Entretien entretien;
    public EntretienPDFExporter1Element(Entretien entretien){
        this.entretien=entretien;
    }

    private void writeTableHeader(PdfPTable table) throws BadElementException, IOException {
        Image logo=Image.getInstance("https://th.bing.com/th/id/R.de799c7d70362f795c58368dce9759e1?rik=zgq3ttCZoHswRA&pid=ImgRaw&r=0&sres=1&sresct=1");
        logo.scaleAbsolute(50,50);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        font.setSize(9);

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Avant", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Apres", font));
        table.addCell(cell);



        cell.setPhrase(new Phrase("Responsable", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Equipement", font));
        table.addCell(cell);


        cell.setPhrase(new Phrase("Photo", font));
        table.addCell(cell);


        cell.setPhrase(new Phrase("Operateur", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Piece", font));
        table.addCell(cell);
    }


    private void writeTableData(PdfPTable table) throws BadElementException, IOException {

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        font.setSize(9);


            table.addCell(new Phrase(String.valueOf(entretien.getId_entretien()),font));
            table.addCell(new Phrase(entretien.getDate_entretien(),font));
            table.addCell(new Phrase(entretien.getType_entretien(),font));
            table.addCell(new Phrase(entretien.getEtat_avant_entretien(),font));
            table.addCell(new Phrase(entretien.getEtat_apres_entretien(),font));
            table.addCell(new Phrase(entretien.getResponsableAtelier().getNom_resp_atelier(),font));
            table.addCell(new Phrase(entretien.getEquipement().getNom_equipement(),font));

            table.addCell(new Phrase(entretien.getOperateur().getNom_operateur(),font));
            table.addCell(new Phrase(entretien.getPieceRechange().toString(),font));
    }


    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Image logo=Image.getInstance("C:\\3PFE Gestion AAT\\logo.png");
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
        Paragraph p = new Paragraph("\n\nInformations sur l'entretien de l'équipement "+entretien.getEquipement().getNom_equipement(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);


        Paragraph p2 = new Paragraph("Signature", font1);
        p2.setAlignment(-100);
        p2.setAlignment(Element.ALIGN_BOTTOM);


        document.add(logo);
        document.add(p1);
        document.add(p);

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);table.setWidths(new float[] {0.9f,1.5f,1.5f,1.5f,1.5f,1.5f,1.5f,2f,2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.add(p2);
        document.close();

    }
}
