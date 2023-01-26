package com.example.demo.piéce_rechanges.pdf;



import com.example.demo.materiel.entity.Materiel;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
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

public class PiecePDFExporter {
    private List<PieceRechange> pieceRechangeList;



    public PiecePDFExporter(java.util.List<PieceRechange> pieceRechangeList) {
        this.pieceRechangeList = pieceRechangeList;
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

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nom", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantite", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Prix", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Desigantion", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);







    }
    private void writeTableData(PdfPTable table) throws BadElementException, IOException {

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        font.setSize(9);

        for (PieceRechange pieceRechange : pieceRechangeList) {
            table.addCell(new Phrase(String.valueOf(pieceRechange.getId_piece()),font));
            table.addCell(new Phrase(pieceRechange.getNom_piece(),font));
            table.addCell(new Phrase(String.valueOf(pieceRechange.getQuantite_piece()),font));
            table.addCell(new Phrase(String.valueOf(pieceRechange.getPrix_piece()),font));

            table.addCell(new Phrase(pieceRechange.getDesignation_piece(),font));
            table.addCell(new Phrase(String.valueOf(pieceRechange.getDate_recu_piece()),font));


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

        Font font3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font3.setSize(10);
        font3.setColor(Color.darkGray);

        Paragraph p1 = new Paragraph("\n\n\n\n\nAcadémie des Arts Traditionnels de Casablanca", font1);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p = new Paragraph("\n\nLa liste des piéces des rechanges", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);


        Paragraph p2 = new Paragraph("\n\n\n\n\n\n\n\n\n\nSignature :", font3);
        p2.setAlignment(-100);
        p2.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p3 = new Paragraph("\n\nDirecteur :", font3);
        p3.setAlignment(-100);
        p3.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p4 = new Paragraph("\n\nChef de Service :", font3);
        p4.setAlignment(-100);
        p4.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p5 = new Paragraph("\n\nChef de Departemet :", font3);
        p5.setAlignment(-100);
        p5.setAlignment(Element.ALIGN_BOTTOM);


        document.add(logo);
        document.add(p1);
        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);table.setWidths(new float[] {1.5f,1.5f,2f,2f,2f,2f});
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

