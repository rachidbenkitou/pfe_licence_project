package com.example.demo.mobilite.pdf;





import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormPdfMobilite {
    private String nomD;
    private String prenomD;
    private String horaire;
    private String idM;
    private String nomM;

   private String numSource;
   private String numDestination;
    private String description;


    public String getNomD() {
        return nomD;
    }

    public void setNomD(String nomD) {
        this.nomD = nomD;
    }

    public String getPrenomD() {
        return prenomD;
    }

    public void setPrenomD(String prenomD) {
        this.prenomD = prenomD;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getIdM() {
        return idM;
    }

    public void setIdM(String idM) {
        this.idM = idM;
    }

    public String getNomM() {
        return nomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }

    public String getNumSource() {
        return numSource;
    }

    public void setNumSource(String numSource) {
        this.numSource = numSource;
    }

    public String getNumDestination() {
        return numDestination;
    }

    public void setNumDestination(String numDestination) {
        this.numDestination = numDestination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void export(HttpServletResponse response)throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Image logo=Image.getInstance("C:\\application_web_gestion_entretiens_produits_mobilités\\logo.png");
        logo.scaleAbsolute(75,65);
        logo.setAbsolutePosition(80,750);

        Font font12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font12.setSize(9);
        font12.setColor(Color.darkGray);
        Paragraph p12 = new Paragraph("\n\n\n\n\nAcadémie des Arts Traditionnels de Casablanca", font12);
        p12.setAlignment(Paragraph.ALIGN_LEFT);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.BLACK);
        Font F1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        F1.setSize(11);
        Font T= FontFactory.getFont(FontFactory.HELVETICA);
        T.setSize(9);



        //TITRES
        Paragraph p1 = new Paragraph("Demande Mobilté", font);
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        // get la date d'aujourdhui
        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();
        formater = new SimpleDateFormat(" dd MMMM yyyy ");
        Paragraph p3= new Paragraph(formater.format(aujourdhui), F1);
        p3.setAlignment(Paragraph.ALIGN_CENTER);


        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(10);
        font1.setColor(Color.darkGray);

        Paragraph p22 = new Paragraph("\n\n\n\n\n\n\n\n\n\nSignature :", font1);
        p22.setAlignment(-100);
        p22.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p33 = new Paragraph("\n\nDirecteur :", font1);
        p3.setAlignment(-100);
        p3.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p44 = new Paragraph("\n\nChef de Service :", font1);
        p44.setAlignment(-100);
        p44.setAlignment(Element.ALIGN_BOTTOM);


        Paragraph p55 = new Paragraph("\n\nChef de Departemet :", font1);
        p55.setAlignment(-100);
        p55.setAlignment(Element.ALIGN_BOTTOM);


        document.add(logo);
        document.add(p12);
        document.add(p1);

        document.add(p3);

//creation de la table 0
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f,1.5f,1.5f});
        table.setSpacingBefore(10);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(10);
        FontFactory.getFont(FontFactory.HELVETICA);
        F1.setColor(Color.BLACK);


        //creation de la table 2
        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100f);
        table2.setWidths(new float[] {3.5f,3.5f});
        table2.setSpacingBefore(10);
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(10);
        cell.setPhrase(new Phrase("Nom Demandeur", F1));
        table2.addCell(cell);
        table2.addCell( "\n   "+getNomD());

        cell.setPhrase(new Phrase("Prenom Demandeur", F1));
        table2.addCell(cell);
        table2.addCell( "\n   "+getPrenomD());

        cell.setPhrase(new Phrase("Numéro Matériel", F1));
        table2.addCell(cell);
        table2.addCell( "\n   "+getIdM());

        cell.setPhrase(new Phrase("Nom Matériel", F1));
        table2.addCell(cell);
        table2.addCell( "\n   "+getNomM());

        cell.setPhrase(new Phrase("Numéro Salle Source", F1));
        table2.addCell(cell);
        table2.addCell( "\n   "+getNumSource());

        cell.setPhrase(new Phrase("Numéro Salle Destination", F1));
        table2.addCell(cell);
        table2.addCell( "\n   "+getNumDestination());

        cell.setPhrase(new Phrase("Description", F1));
        table2.addCell(cell);
        table2.addCell( "\n   "+getDescription());

        //L'ajout des tableaux au pdf

        document.add(table);
        document.add(table2);
        document.add(p22);
        document.add(p33);
        document.add(p44);
        document.add(p55);
        document.close();
    }
}

