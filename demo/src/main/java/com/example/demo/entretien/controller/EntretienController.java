package com.example.demo.entretien.controller;

import com.example.demo.entretien.entity.DemandeEntretienForm;
import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.entretien.pdf.FormPdfEntretien;
import com.example.demo.entretien.service.EntretienService;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.equipement.service.EquipementService;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.opérateur.service.OperateurService;
import com.example.demo.pdf.service.PdfService;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import com.example.demo.piéce_rechanges.service.Piece_rechangeService;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
import com.example.demo.responsable_atelier.service.ResponsableAtelierService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/entretiens")
public class EntretienController {
    @Autowired
    private EntretienService entretienService;

    @Autowired
    private ResponsableAtelierService responsableAtelierService;

    @Autowired
    private EquipementService equipementService;


    @Autowired
    private Piece_rechangeService pieceRechangeService;

    @Autowired
    private OperateurService operateurService;
    @Autowired
    private PdfService pdfService;


    @GetMapping("/list")
    public String showEntretiensList(Model model){

        List<Entretien> entretienList=entretienService.findAll();
        model.addAttribute("entretienList",entretienList);

        return "entretien/list-entretien";

    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){

        Entretien entretiens=new Entretien();
        model.addAttribute("entretien",entretiens);

        List<ResponsableAtelier> responsableAtelierLists=responsableAtelierService.findAll();
        model.addAttribute("responsableAtelierList",responsableAtelierLists);

        List<Equipement> equipementList=equipementService.findAll();
        model.addAttribute("equipementList",equipementList);

        List<Operateur> operateurList=operateurService.findAll();
        model.addAttribute("operateurList",operateurList);


        List<PieceRechange> pieceRechangeList=pieceRechangeService.findAll();
        model.addAttribute("pieceRechangeList",pieceRechangeList);


        return "entretien/form-entretien";
    }



    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("entretien") @Validated Entretien entretien, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "entretien/list-entretien";

        }
        else {
            entretienService.save(entretien);
            return "redirect:/entretiens/list";

        }

    }


    @GetMapping("/delete/{entretienId}")
    public String deleteEntretien(@PathVariable int entretienId){

        entretienService.deleteById(entretienId);

        return "redirect:/entretiens/list";
    }







@GetMapping("/search")
    public String searchByType(@RequestParam("type") String type,Model model){

        if(type.trim().isEmpty()){
            return "redirect:/entretiens/list";
        }else {
            List<Entretien> entretienList=entretienService.searchBy(type);

            model.addAttribute("entretienList",entretienList);

            return "entretien/list-entretien";
        }
}


    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam("date1") String date1,@RequestParam("date2") String date2,Model model){

        if(date1.trim().isEmpty() && date2.trim().isEmpty()){
            return "redirect:/entretiens/list";
        }else {
            List<Entretien> entretienList=entretienService.searchByDate(date1,date2);

            model.addAttribute("entretienList",entretienList);

            return "entretien/list-entretien";
        }
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entretiens_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Entretien> entretienList =entretienService.findAll();

        EntretienPDFExporter exporter = new EntretienPDFExporter(entretienList);
        exporter.export(response);

    }
    @GetMapping("/edit/{entretienId}")
    public String showEditForm(@PathVariable  int entretienId ,Model model){

        Entretien entretien=entretienService.findById(entretienId);
        model.addAttribute("entretien",entretien);
        List<ResponsableAtelier> responsableAtelierLists=responsableAtelierService.findAll();
        model.addAttribute("responsableAtelierList",responsableAtelierLists);
        List<Equipement> equipementList=equipementService.findAll();
        model.addAttribute("equipementList",equipementList);
        List<Operateur> operateurList=operateurService.findAll();
        model.addAttribute("operateurList",operateurList);

        List<PieceRechange> pieceRechangeList=pieceRechangeService.findAll();
        model.addAttribute("pieceRechangeList",pieceRechangeList);

        return "entretien/form-entretien";
    }
    @GetMapping("/export/pdf/{entretienId}")
    public void exportToPDF1Element(HttpServletResponse response ,@PathVariable  int entretienId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entretiens_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Entretien entretien =entretienService.findById(entretienId);

        EntretienPDFExporter1Element exporter = new EntretienPDFExporter1Element(entretien);
        exporter.export(response);

    }


    @GetMapping("/demande")
    public String showDemandeE(Model model){
        model.addAttribute("pdf",new FormPdfEntretien());
        return "Demande/DemandeEntretien";

    }


    @PostMapping("/demande/pdf")
    public void exportToPDF(HttpServletResponse response, @ModelAttribute("nomR") String I, @ModelAttribute("prenomR") String D, @ModelAttribute("horaire") String H, @ModelAttribute("idE") String CA, @ModelAttribute("nomE") String C, @ModelAttribute("typeE") String A, @ModelAttribute("description") String E, Model model) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);




        FormPdfEntretien exporter = new FormPdfEntretien();


       exporter.setNomR(I);
       exporter.setPrenomR(D);
       exporter.setHoraire(H);
       exporter.setNomE(C);
       exporter.setIdE(CA);
       exporter.setTypeE(A);
       exporter.setDescription(E);
       exporter.setHoraire(H);
       exporter.export(response);

    }




    }



