package com.example.demo.filiere.controller;


import com.example.demo.atelier.entity.Atelier;
import com.example.demo.atelier.service.AtelierService;
import com.example.demo.filiere.entity.Filiere;
import com.example.demo.filiere.service.FiliereService;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
import com.example.demo.responsable_atelier.service.ResponsableAtelierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;



    @Autowired
    private AtelierService atelierService;

    @Autowired
    private ResponsableAtelierService responsableAtelierService;


    @GetMapping("/list")
    public String listFilieres(Model theModel){
        //Get Filieres From Database

        List<Filiere> theFilieres=filiereService.findAll();

        //Add list of filieres to the Spring Model
        theModel.addAttribute("listFilieres",theFilieres);

        return "filiere/list-filiere";
        //ateliers/list-ateliers

    }

    @GetMapping("/delete/{filiereId}")
    public String deleteEntretien(@PathVariable int filiereId){

        filiereService.deleteById(filiereId);

        return "redirect:/filieres/list";
    }

@GetMapping("/form")
    public String showFiliereForm(Model model){
        model.addAttribute("filiere", new Filiere());

        List<ResponsableAtelier> responsableAtelierList=responsableAtelierService.findAll();
        model.addAttribute("responsableAtelierList",responsableAtelierList);


        List<Atelier> atelierList=atelierService.findAll();
        model.addAttribute("atelierList",atelierList);

        return "filiere/form-filiere";
}

    @GetMapping("/edit/{filiereId}")
    public String showEditForm(@PathVariable  int filiereId ,Model model){
        Filiere filiere=filiereService.findById(filiereId);
        model.addAttribute("filiere",filiere);

        List<ResponsableAtelier> responsableAtelierLists=responsableAtelierService.findAll();
        model.addAttribute("responsableAtelierList",responsableAtelierLists);
        List<Atelier> atelierList=atelierService.findAll();
        model.addAttribute("atelierList",atelierList);
        return "filiere/form-filiere";
    }

@PostMapping("save")
    public String saveFiliere(Filiere filiere){
      filiereService.save(filiere);
        return"redirect:/filieres/list";

}


    @GetMapping("/search")
    public String searchByType(@RequestParam("name") String name, Model model){

        if(name.trim().isEmpty()){
            return "redirect:/filieres/list";
        }else {
            List<Filiere> filiereList=filiereService.searchBy(name);
            model.addAttribute("listFilieres",filiereList);
            return "filiere/list-filiere";
        }
    }

}
