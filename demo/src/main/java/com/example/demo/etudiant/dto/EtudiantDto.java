package com.example.demo.etudiant.dto;

import com.example.demo.filiere.entity.Filiere;
import com.example.demo.produit.entity.Produit;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EtudiantDto {


    private String nom;
    private String Prenom;
    private String filiere;
    private String produit;

    @DateTimeFormat
    private Date date;




    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
