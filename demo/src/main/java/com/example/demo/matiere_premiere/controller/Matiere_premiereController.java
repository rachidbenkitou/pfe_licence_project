package com.example.demo.matiere_premiere.controller;


import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.matiere_premiere.entity.MatierePremiere;
import com.example.demo.matiere_premiere.pdf.MatierePDFExporter;
import com.example.demo.matiere_premiere.pdf.MatierePDFExporter1Element;
import com.example.demo.matiere_premiere.service.Matiere_premiereService;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("matieres")
public class Matiere_premiereController {

    @Autowired
    private Matiere_premiereService matiere_premiereService;

    @GetMapping("/list")
    public String showEntretiensList(Model model){

        List<MatierePremiere> matierePremiereList=matiere_premiereService.findAll();
        model.addAttribute("matierePremiereList",matierePremiereList);

        return "matiere/list-matiere";

    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){
        MatierePremiere matierePremiere=new MatierePremiere();
        model.addAttribute("matierePremiere",matierePremiere);
        return "matiere/form-matiere";
    }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("matierePremiere") @Validated MatierePremiere matierePremiere, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "matiere/list-matiere";
        }
        else {
           matiere_premiereService.save(matierePremiere);
            return "redirect:/matieres/list";

        }

    }


    @GetMapping("/delete/{entretienId}")
    public String deleteEntretien(@PathVariable int entretienId){
        matiere_premiereService.deleteById(entretienId);
        return "redirect:/matieres/list";
    }


    @GetMapping("/edit/{entretienId}")
    public String showEditForm(@PathVariable  int entretienId ,Model model){
        MatierePremiere matierePremiere=matiere_premiereService.findById(entretienId);
        model.addAttribute("matierePremiere",matierePremiere);

        return "matiere/form-matiere";
    }


    @GetMapping("/search")
    public String searchByType(@RequestParam("name") String name,Model model){

        if(name.trim().isEmpty()){
            return "redirect:/matieres/list";
        }
        else {
            List<MatierePremiere> matierePremiereList=matiere_premiereService.searchBy(name);

            model.addAttribute("matierePremiereList",matierePremiereList);

            return "matiere/list-matiere";
        }
    }


    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam("date1") String date1,@RequestParam("date2") String date2,Model model){

        if(date1.trim().isEmpty() && date2.trim().isEmpty()){
            return "redirect:/matieres/list";
        }else {
            List<MatierePremiere> matierePremiereList=matiere_premiereService.searchByDate(date1,date2);

            model.addAttribute("matierePremiereList",matierePremiereList);

            return "matiere/list-matiere";
        }
    }


    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=matiere-premiere_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<MatierePremiere> matierePremiereList =matiere_premiereService.findAll();

        MatierePDFExporter exporter = new MatierePDFExporter(matierePremiereList);
        exporter.export(response);

    }



    @GetMapping("/export/pdf/{entretienId}")
    public void exportToPDF1Element(HttpServletResponse response ,@PathVariable  int entretienId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=matiere-premiere_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        MatierePremiere matierePremiere =matiere_premiereService.findById(entretienId);

        MatierePDFExporter1Element exporter = new MatierePDFExporter1Element(matierePremiere);
        exporter.export(response);

    }



}
