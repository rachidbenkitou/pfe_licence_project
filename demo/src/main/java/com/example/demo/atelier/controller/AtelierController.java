package com.example.demo.atelier.controller;

import com.example.demo.atelier.entity.Atelier;
import com.example.demo.atelier.service.AtelierService;
import com.example.demo.entretien.entity.Entretien;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.equipement.service.EquipementService;
import com.example.demo.filiere.entity.Filiere;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ateliers")
public class AtelierController {

    @Autowired
    private AtelierService atelierService;


    public AtelierController(AtelierService TheAtelierService){
        atelierService=TheAtelierService;
    }


    //Add mapping for "/list"

    @GetMapping("/list")
    public String listAteliers(Model theModel){
        //Get Employees From Database

        List<Atelier> atelierList=atelierService.findAll();

        //Add list of ateliers to the Spring Model
        theModel.addAttribute("atelierList",atelierList);

        return "atelier/list-atelier";
        //ateliers/list-ateliers

    }

    @GetMapping("/form")
    public String showFormForAdd(Model model){
        Atelier atelier=new Atelier();
        model.addAttribute("atelier",atelier);
        return "atelier/form-atelier";
    }


    @PostMapping("/save")
    public String saveAtelier(@ModelAttribute("atelier") @Validated Atelier atelier, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "atelier/list-atelier";

        }
        else {
            atelierService.save(atelier);


            return "redirect:/ateliers/list";

        }

    }


    @GetMapping("/delete/{atelierId}")
    public String deleteAtelier(@PathVariable int atelierId){

        atelierService.deleteById(atelierId);

        return "redirect:/ateliers/list";
    }


    @GetMapping("/search")
    public String searchByNameAtelier(@RequestParam("name") String name,Model model){

        if(name.trim().isEmpty()){
            return "redirect:/ateliers/list";
        }else {
            List<Atelier> atelierList=atelierService.searchBy(name);

            model.addAttribute("atelierList",atelierList);

            return "atelier/list-atelier";
        }
    }


    @GetMapping("/edit/{atelierId}")
    public String showEditForm(@PathVariable  int atelierId ,Model model){
        Atelier atelier=atelierService.findById(atelierId);
        model.addAttribute("atelier",atelier);

        return "atelier/form-atelier";
    }

}
