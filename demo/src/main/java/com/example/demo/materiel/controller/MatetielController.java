package com.example.demo.materiel.controller;


import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.materiel.entity.Materiel;
import com.example.demo.materiel.pdf.MaterielPDFExporter;
import com.example.demo.materiel.pdf.MaterielPDFExporter1Element;
import com.example.demo.materiel.service.MaterielService;
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
@RequestMapping("/materiels")
public class MatetielController {
    @Autowired
    private MaterielService materielService;




    @GetMapping("/list")
    public String showMaterielsList(Model model){

        List<Materiel> materielList=materielService.findAll();
        model.addAttribute("materielList",materielList);

        return "materiel/list-materiel";

    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){

        Materiel materiel=new Materiel();
        model.addAttribute("materiel",materiel);
        return "materiel/form-materiel";
    }



    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("materiel") @Validated Materiel materiel, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "materiel/list-materiel";

        }
        else {
            materielService.save(materiel);
            return "redirect:/materiels/list";
        }
    }


    @GetMapping("/delete/{materielId}")
    public String deleteMateriel(@PathVariable int materielId){

        materielService.deleteById(materielId);

        return "redirect:/materiels/list";
    }


    @GetMapping("/search")
    public String searchByType(@RequestParam("name") String name,Model model){

        if(name.trim().isEmpty()){
            return "redirect:/materiels/list";
        }else {
            List<Materiel> materielList=materielService.searchBy(name);

            model.addAttribute("materielList",materielList);

            return "materiel/list-materiel";
        }
    }


    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam("date1") String date1,@RequestParam("date2") String date2,Model model){

        if(date1.trim().isEmpty() && date2.trim().isEmpty()){
            return "redirect:/materiels/list";
        }else {
            List<Materiel> materielList=materielService.searchByDate(date1,date2);

            model.addAttribute("materielList",materielList);

            return "materiel/list-materiel";
        }
    }


    @GetMapping("/edit/{materielId}")
    public String showEditForm(@PathVariable  int materielId ,Model model){
        Materiel materiel=materielService.findById(materielId);
        model.addAttribute("materiel",materiel);

        return "materiel/form-materiel";
    }


    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Materiel> materielList =materielService.findAll();

        MaterielPDFExporter exporter = new MaterielPDFExporter(materielList);
        exporter.export(response);

    }


    @GetMapping("/export/pdf/{materielId}")
    public void exportToPDF1Element(HttpServletResponse response ,@PathVariable  int materielId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Materiel materiel =materielService.findById(materielId);

        MaterielPDFExporter1Element exporter = new MaterielPDFExporter1Element(materiel);
        exporter.export(response);

    }
}
