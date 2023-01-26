package com.example.demo.etudiant.controller;

import com.example.demo.etudiant.entity.Etudiant;
import com.example.demo.etudiant.service.EtudiantService;

import com.example.demo.filiere.entity.Filiere;
import com.example.demo.filiere.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController1 {
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private FiliereService filiereService;


    @GetMapping("/list")
    public String listEtudiants(Model theModel){
        //Get Filieres From Database

        List<Etudiant> theEtudiant=etudiantService.findAll();

        //Add list of filieres to the Spring Model
        theModel.addAttribute("listEtudiants",theEtudiant);

        return "etudiant/list-etudiant";
        //ateliers/list-ateliers

    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){

    Etudiant etudiants=new Etudiant();
    model.addAttribute("etudiant",etudiants);

    List<Filiere> filiereList=filiereService.findAll();
    model.addAttribute("filiereLists",filiereList);

    return "etudiant/form-etudiant";
        }

        @GetMapping("/edit/{studentId}")
        public String showEditForm(@PathVariable  int studentId ,Model model){

            Etudiant etudiant=etudiantService.findById(studentId);

            model.addAttribute("etudiant",etudiant);


             List<Filiere>filiereList=filiereService.findAll();
            model.addAttribute("filiereLists",filiereList);


            return "etudiant/form-etudiant";

        }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("etudiant") @Validated Etudiant theEtudiant, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "etudiant/show-list";
        }
        else {
            etudiantService.save(theEtudiant);


            return "redirect:/students/list";

        }

    }

    @GetMapping("/delete/{etudiantId}")
    public String deleteEtudiant(@PathVariable int etudiantId){

        etudiantService.deleteById(etudiantId);

        return "redirect:/students/list";
    }





}
