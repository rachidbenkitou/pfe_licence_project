package com.example.demo.produit.controller;


import com.example.demo.commission.dao.CommissionRepository;
import com.example.demo.commission.entity.Commission;
import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.etudiant.entity.Etudiant;
import com.example.demo.etudiant.service.EtudiantService;

import com.example.demo.matiere_premiere.entity.MatierePremiere;
import com.example.demo.matiere_premiere.service.Matiere_premiereService;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.produit.FileUpload.FileUploadUtil;
import com.example.demo.produit.entity.Produit;
import com.example.demo.produit.pdf.ProduitPDFExporter;
import com.example.demo.produit.pdf.ProduitPDFExporter1Element;
import com.example.demo.produit.service.ProduitService;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private EtudiantService etudiantService;




    @Autowired
    private Matiere_premiereService matiere_premiereService;

    @Autowired
    private CommissionRepository commissionRepository;


    @GetMapping("/list")
    public String showEntretiensList(Model model){

        List<Produit> produitList=produitService.findAll();
        model.addAttribute("produitList",produitList);

        return "produit/list-produit";

    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){

       Produit produit=new Produit();
        model.addAttribute("produit",produit);






        List<Commission> commissionList=commissionRepository.findAll();
        model.addAttribute("commissionList",commissionList);

        List<Etudiant> etudiantList=etudiantService.findAll();
        model.addAttribute("etudiantList",etudiantList);






        List<MatierePremiere> matierePremiereList=matiere_premiereService.findAll();
        model.addAttribute("matierePremiereList",matierePremiereList);




        return "produit/form-produit";
    }




    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("produit") @Validated Produit produit, BindingResult bindingResult
    ,@RequestParam("image") MultipartFile multipartFile) throws IOException{

        if(bindingResult.hasErrors()){
            return "produit/list-produit";

        }
        else {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            produit.setImage_produit(fileName);
            Produit produit1= produitService.save(produit);
            try{

                //String uploadDir = "product-photos/" + produit1.getId_produit();
                String uploadDir = "C:\\application_web_gestion_entretiens_produits_mobilités\\produits-images\\" + produit1.getId_produit();
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }catch (Exception e){
                System.out.println(e);
            }
            return "redirect:/produits/list";
        }
    }


    @GetMapping("/edit/{entretienId}")
    public String showEditForm(@PathVariable int entretienId , Model model){
        Produit produit=produitService.findById(entretienId);
        model.addAttribute("produit",produit);

        List<Commission> commissionList=commissionRepository.findAll();
        model.addAttribute("commissionList",commissionList);

        List<Etudiant> etudiantList=etudiantService.findAll();
        model.addAttribute("etudiantList",etudiantList);

        List<MatierePremiere> matierePremiereList=matiere_premiereService.findAll();
        model.addAttribute("matierePremiereList",matierePremiereList);

        return "produit/form-produit";
    }


    @GetMapping("/delete/{entretienId}")
    public String deleteEntretien(@PathVariable int entretienId){

        produitService.deleteById(entretienId);

        return "redirect:/produits/list";
    }


    @GetMapping("/search")
    public String searchByType(@RequestParam("name") String name,Model model){

        if(name.trim().isEmpty()){
            return "redirect:/produits/list";
        }else {
            List<Produit> produitList=produitService.searchBy(name);

            model.addAttribute("produitList",produitList);

            return "produit/list-produit";
        }
    }



    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=produits_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Produit> produitList =produitService.findAll();

        ProduitPDFExporter exporter = new ProduitPDFExporter(produitList);
        exporter.export(response);

    }


    @GetMapping("/export/pdf/{entretienId}")
    public void exportToPDF1Element(HttpServletResponse response ,@PathVariable  int entretienId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=produits_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Produit produit =produitService.findById(entretienId);

        ProduitPDFExporter1Element exporter = new ProduitPDFExporter1Element(produit);
        exporter.export(response);

    }
}
