package com.example.demo.equipement.controller;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.equipement.pdf.EquipementPDFExporter;
import com.example.demo.equipement.pdf.EquipementPDFExporter1Element;
import com.example.demo.equipement.service.EquipementService;
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
@RequestMapping("/equipements")
public class EquipementController {
    @Autowired
    private EquipementService equipementService;


    @GetMapping("/list")
    public String showEquipementList(Model model){

        List<Equipement> equipementList=equipementService.findAll();
        model.addAttribute("equipementList",equipementList);

        return "equipement/list-equipement";

    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){

        Equipement equipement=new Equipement();
        model.addAttribute("equipement",equipement);
        return "equipement/form-equipement";
    }



    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("equipement") @Validated Equipement equipement, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "equipement/form-equipement";

        }
        else {
            equipementService.save(equipement);


            return "redirect:/equipements/list";

        }



    }

    @GetMapping("/delete/{equipementId}")
    public String deleteEquipement(@PathVariable int equipementId){

        equipementService.deleteById(equipementId);
        return "redirect:/equipements/list";
    }



    @GetMapping("/edit/{equipementId}")
    public String showEditForm(@PathVariable  int equipementId ,Model model){
        Equipement equipement=equipementService.findById(equipementId);
        model.addAttribute("equipement",equipement);

        return "equipement/form-equipement";
    }

    @GetMapping("/export/pdf/{equipementId}")
    public void exportToPDF1Element(HttpServletResponse response , @PathVariable  int equipementId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=equipements_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Equipement equipement =equipementService.findById(equipementId);

        EquipementPDFExporter1Element exporter = new EquipementPDFExporter1Element(equipement);
        exporter.export(response);

    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=equipements_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Equipement> equipementList =equipementService.findAll();

        EquipementPDFExporter exporter = new EquipementPDFExporter(equipementList);
        exporter.export(response);

    }


    @GetMapping("/search")
    public String searchByType(@RequestParam("name") String name,Model model){

        if(name.trim().isEmpty()){
            return "redirect:/equipements/list";
        }else {
            List<Equipement> equipementList=equipementService.searchBy(name);

            model.addAttribute("equipementList",equipementList);

            return "equipement/list-equipement";
        }
    }


    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam("date1") String date1,@RequestParam("date2") String date2,Model model){

        if(date1.trim().isEmpty() && date2.trim().isEmpty()){
            return "redirect:/equipements/list";
        }else {
            List<Equipement> equipementList=equipementService.searchByDate(date1,date2);

            model.addAttribute("equipementList",equipementList);

            return "equipement/list-equipement";
        }
    }



}
