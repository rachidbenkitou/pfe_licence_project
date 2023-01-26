package com.example.demo.pdf.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.service.EntretienService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    @Autowired
    private EntretienService entretienService;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\HP\\Desktop\\pdf";
        List<Entretien> entretienList=entretienService.findAll();

        //Load file and compile it
        File file= ResourceUtils.getFile("classpath:entretien.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(entretienList);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdBy","Rachid Benkitou");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\entretiens.html");

        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\entretiens.pdf");

        }
        return  "report generated in path : "+path;
    }
}
