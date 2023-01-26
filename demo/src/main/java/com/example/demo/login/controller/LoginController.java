package com.example.demo.login.controller;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.service.EntretienService;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.equipement.service.EquipementService;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.opérateur.service.OperateurService;
import com.example.demo.pdf.service.PdfService;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
import com.example.demo.responsable_atelier.service.ResponsableAtelierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private EntretienService entretienService;
/*

    @GetMapping("/")
    public String DefaultPage(Model model){
        List<Entretien> entretienList=entretienService.findAll();
        model.addAttribute("entretienList",entretienList);

        return "entretien/list-entretien";
    }*/
    @GetMapping ("/login")
    public String LoginPage(){
        return "loginP/login-page";
    }
}
