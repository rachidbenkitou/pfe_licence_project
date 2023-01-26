package com.example.demo.piéce_rechanges.controller;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import com.example.demo.piéce_rechanges.pdf.PiecePDFExporter;
import com.example.demo.piéce_rechanges.pdf.PiecePDFExporter1Element;
import com.example.demo.piéce_rechanges.service.Piece_rechangeService;
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
@RequestMapping("pieces")
public class Piece_rechangeController {

    @Autowired
    private Piece_rechangeService piece_rechangeService;


    @GetMapping("/list")
    public String showEntretiensList(Model model){

        List<PieceRechange> pieceRechangeList=piece_rechangeService.findAll();
        model.addAttribute("pieceRechangeList",pieceRechangeList);

        return "piece/list-piece";

    }

    @GetMapping("/delete/{entretienId}")
    public String deleteEntretien(@PathVariable int entretienId){

        piece_rechangeService.deleteById(entretienId);

        return "redirect:/pieces/list";
    }


    @GetMapping("/form")
    public String showFormForAdd(Model model){

        PieceRechange pieceRechange=new PieceRechange();
        model.addAttribute("pieceRechange",pieceRechange);
        return "piece/form-piece";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("pieceRechange") @Validated PieceRechange pieceRechange, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "piece/list-piece";

        }
        else {
            piece_rechangeService.save(pieceRechange);


            return "redirect:/pieces/list";

        }

    }



    @GetMapping("/edit/{entretienId}")
    public String showEditForm(@PathVariable  int entretienId ,Model model){
        PieceRechange pieceRechange=piece_rechangeService.findById(entretienId);
        model.addAttribute("pieceRechange",pieceRechange);

        return "piece/form-piece";
    }



    @GetMapping("/search")
    public String searchByType(@RequestParam("name") String name,Model model){

        if(name.trim().isEmpty()){
            return "redirect:/pieces/list";
        }else {
            List<PieceRechange> pieceRechangeList=piece_rechangeService.searchBy(name);

            model.addAttribute("pieceRechangeList",pieceRechangeList);

            return "piece/list-piece";
        }
    }


    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam("date1") String date1,@RequestParam("date2") String date2,Model model){

        if(date1.trim().isEmpty() && date2.trim().isEmpty()){
            return "redirect:/pieces/list";
        }else {
            List<PieceRechange> pieceRechangeList=piece_rechangeService.searchByDate(date1,date2);

            model.addAttribute("pieceRechangeList",pieceRechangeList);

            return "piece/list-piece";
        }
    }


    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=piece-rechanges_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<PieceRechange> pieceRechangeList =piece_rechangeService.findAll();

        PiecePDFExporter exporter = new PiecePDFExporter(pieceRechangeList);
        exporter.export(response);

    }


    @GetMapping("/export/pdf/{entretienId}")
    public void exportToPDF1Element(HttpServletResponse response ,@PathVariable  int entretienId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=piece-rechanges_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        PieceRechange pieceRechange =piece_rechangeService.findById(entretienId);

        PiecePDFExporter1Element exporter = new PiecePDFExporter1Element(pieceRechange);
        exporter.export(response);

    }
}
