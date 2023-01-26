package com.example.demo.mobilite.controller;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.entretien.pdf.FormPdfEntretien;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.materiel.entity.Materiel;
import com.example.demo.materiel.service.MaterielService;
import com.example.demo.mobilite.entity.Mobilite;
import com.example.demo.mobilite.pdf.FormPdfMobilite;
import com.example.demo.mobilite.pdf.MobilitePDFExporter;
import com.example.demo.mobilite.pdf.MobilitePDFExporter1Element;
import com.example.demo.mobilite.service.MobiliteService;
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
@RequestMapping("/mobilites")
public class MobiliteController {
    @Autowired
    private MobiliteService mobiliteService;

    @Autowired
    private MaterielService materielService;


    @GetMapping("/list")
    public String showEntretiensList(Model model){

        List<Mobilite> mobiliteList=mobiliteService.findAll();
        model.addAttribute("mobiliteList",mobiliteList);

        return "mobilite/list-mobilite";

    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){

        Mobilite mobilite=new Mobilite();
        model.addAttribute("mobilite",mobilite);

        List<Materiel> materielList=materielService.findAll();
        model.addAttribute("materielList",materielList);

        return "mobilite/form-mobilite";
    }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("mobilite") @Validated Mobilite mobilite, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "mobilite/list-mobilite";

        }
        else {
            mobiliteService.save(mobilite);
            return "redirect:/mobilites/list";
        }
    }


    @GetMapping("/delete/{entretienId}")
    public String deleteEntretien(@PathVariable int entretienId){

        mobiliteService.deleteById(entretienId);

        return "redirect:/mobilites/list";
    }


    @GetMapping("/edit/{entretienId}")
    public String showEditForm(@PathVariable  int entretienId ,Model model){
        Mobilite mobilite=mobiliteService.findById(entretienId);
        model.addAttribute("mobilite",mobilite);

        List<Materiel> materielList=materielService.findAll();
        model.addAttribute("materielList",materielList);



        return "mobilite/form-mobilite";
    }



    @GetMapping("/search")
    public String searchByType(@RequestParam("id") String id,Model model){

        if(id.trim().isEmpty()){
            return "redirect:/mobilites/list";
        }else {
            List<Mobilite> mobiliteList=mobiliteService.searchBy(Integer.parseInt(id));

            model.addAttribute("mobiliteList",mobiliteList);

            return "mobilite/list-mobilite";
        }
    }


    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam("date1") String date1,@RequestParam("date2") String date2,Model model){

        if(date1.trim().isEmpty() && date2.trim().isEmpty()){
            return "redirect:/mobilites/list";
        }else {
            List<Mobilite> mobiliteList=mobiliteService.searchByDate(date1,date2);

            model.addAttribute("mobiliteList",mobiliteList);

            return "mobilite/list-mobilite";
        }
    }



    @GetMapping("/demande")
    public String showDemandeE(Model model){
        model.addAttribute("pdfM",new FormPdfMobilite());
        return "Demande/DemandeMobilite";

    }


    @PostMapping("/demande/pdf")
    public void exportToPDF(HttpServletResponse response, @ModelAttribute("nomD") String I, @ModelAttribute("prenomD") String D, @ModelAttribute("horaire") String H, @ModelAttribute("idM") String CA, @ModelAttribute("nomM") String C, @ModelAttribute("numSource") String A,@ModelAttribute("numDestination") String AA, @ModelAttribute("description") String E, Model model) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);




        FormPdfMobilite exporter = new FormPdfMobilite();

        exporter.setNomD(I);
        exporter.setPrenomD(D);
        exporter.setHoraire(H);
        exporter.setNomM(C);
        exporter.setIdM(CA);
        exporter.setNumSource(A);
        exporter.setNumDestination(AA);
        exporter.setDescription(E);
        exporter.export(response);

    }



    @GetMapping("/export/pdf/{entretienId}")
    public void exportToPDF1Element(HttpServletResponse response ,@PathVariable  int entretienId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=mobilites_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Mobilite mobilite =mobiliteService.findById(entretienId);

        MobilitePDFExporter1Element exporter = new MobilitePDFExporter1Element(mobilite);
        exporter.export(response);

    }


    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=mobilites_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Mobilite> mobiliteList =mobiliteService.findAll();
        MobilitePDFExporter exporter = new MobilitePDFExporter(mobiliteList);
        exporter.export(response);

    }
}
