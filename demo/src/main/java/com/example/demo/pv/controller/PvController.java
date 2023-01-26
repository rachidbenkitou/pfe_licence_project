package com.example.demo.pv.controller;


import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.mobilite.entity.Mobilite;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import com.example.demo.pv.entity.Pv;
import com.example.demo.pv.pdf.PvPDFExporter;
import com.example.demo.pv.pdf.PvPDFExporter1Element;
import com.example.demo.pv.service.PvService;
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
@RequestMapping("/pvs")
public class PvController {

@Autowired
private PvService pvService;

    @GetMapping("/form")
    public String showForm(Model model){

         Pv pv = new Pv();
        model.addAttribute("pv",pv);



        return "pv/form-pv";
    }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("pv") @Validated Pv pv, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pv/list-pv";
        }
        else {
            pvService.save(pv);
            return "redirect:/pvs/list";
        }
    }


    @GetMapping("/list")
    public String showEntretiensList(Model model){

        List<Pv> pvList=pvService.findAll();
        model.addAttribute("pvList",pvList);

        return "pv/list-pv";

    }


    @GetMapping("/delete/{entretienId}")
    public String deleteEntretienl(@PathVariable int entretienId){

        pvService.deleteById(entretienId);

        return "redirect:/pvs/list";
    }


    @GetMapping("/edit/{entretienId}")
    public String showEditForm(@PathVariable  int entretienId ,Model model){

        Pv pv=pvService.findById(entretienId);
        model.addAttribute("pv",pv);


        return "pv/form-pv";
    }




    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=procés-verbal_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Pv> pvList =pvService.findAll();

        PvPDFExporter exporter = new PvPDFExporter(pvList);
        exporter.export(response);

    }


    @GetMapping("/export/pdf/{entretienId}")
    public void exportToPDF1Element(HttpServletResponse response ,@PathVariable  int entretienId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=procés-verbal_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Pv pv =pvService.findById(entretienId);
        PvPDFExporter1Element exporter = new PvPDFExporter1Element(pv);
        exporter.export(response);

    }


    @GetMapping("/search")
    public String searchByType(@RequestParam("idpv") String idpv,Model model){

        if(idpv.trim().isEmpty()){
            return "redirect:/mobilites/list";
        }else {
            List<Pv> pvList=pvService.searchBy(Integer.parseInt(idpv));

            model.addAttribute("pvList",pvList);

            return "pv/list-pv";
        }
    }


    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam("date1") String date1,@RequestParam("date2") String date2,Model model){

        if(date1.trim().isEmpty() && date2.trim().isEmpty()){
            return "redirect:/pvs/list";
        }else {
            List<Pv> pvList=pvService.searchByDate(date1,date2);

            model.addAttribute("pvList",pvList);

            return "pv/list-pv";
        }
    }



}
