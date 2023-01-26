package com.example.demo.demandeur.entity;


import com.example.demo.materiel_demandeur.entity.livraison_materiel_demandeur;
import com.example.demo.mobilite.entity.Mobilite;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="demandeur")
@Data
public class Demandeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_demandeur")
    private int id_demandeur;

    @Column(name="nom_demandeur")
    private String nom_demandeur;

    @Column(name="prenom_demandeur")
    private String prenom_demandeur;


    @OneToMany(fetch=FetchType.LAZY,mappedBy = "demandeur",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Mobilite> mobilites;


    public void add(Mobilite TheMobilite){
        if(mobilites==null){
            mobilites=new ArrayList<>();
        }

        mobilites.add(TheMobilite);
        TheMobilite.setDemandeur(this);
    }


    @OneToMany(fetch=FetchType.LAZY,mappedBy = "demandeur",cascade = {CascadeType.ALL})
    List<livraison_materiel_demandeur> livraison;


    public List<Mobilite> getMobilites() {
        if(mobilites==null) mobilites=new ArrayList<>();
        return mobilites;
    }

    public List<livraison_materiel_demandeur> getLivraison() {
        if(livraison==null) livraison=new ArrayList<>();
        return livraison;
    }

    public int getId_demandeur() {
        return id_demandeur;
    }

    public void setId_demandeur(int id_demandeur) {
        this.id_demandeur = id_demandeur;
    }

    public String getNom_demandeur() {
        return nom_demandeur;
    }

    public void setNom_demandeur(String nom_demandeur) {
        this.nom_demandeur = nom_demandeur;
    }

    public String getPrenom_demandeur() {
        return prenom_demandeur;
    }

    public void setPrenom_demandeur(String prenom_demandeur) {
        this.prenom_demandeur = prenom_demandeur;
    }

    public void setMobilites(List<Mobilite> mobilites) {
        this.mobilites = mobilites;
    }

    public void setLivraison(List<livraison_materiel_demandeur> livraison) {
        this.livraison = livraison;
    }
}
