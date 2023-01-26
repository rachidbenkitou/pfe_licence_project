package com.example.demo.etudiant_produit.entity;

import com.example.demo.produit.entity.Produit;
import com.example.demo.etudiant.entity.Etudiant;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class EtudiantProduit {

    @EmbeddedId
    EtudiantProduitKey id;

    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @MapsId("idEtudiant")
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;

    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @MapsId("idProduit")
    @JoinColumn(name = "id_produit")
    Produit produit;


    String date_creation;

    public EtudiantProduitKey getId() {
        return id;
    }

    public void setId(EtudiantProduitKey id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }
}
