package com.example.demo.materiel_demandeur.entity;

import com.example.demo.demandeur.entity.Demandeur;
import com.example.demo.materiel.entity.Materiel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
public class livraison_materiel_demandeur {

    @EmbeddedId
    livraison_materiel_demandeurKey id;

    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @MapsId("matrielId")
    @JoinColumn(name = "id_materiel")
    Materiel materiel;


    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @MapsId("demandeurId")
    @JoinColumn(name = "id_demandeur")
    Demandeur demandeur;

    private Date date_livraison;
    private String localisation_livraison;

    public livraison_materiel_demandeurKey getId() {
        return id;
    }

    public void setId(livraison_materiel_demandeurKey id) {
        this.id = id;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getLocalisation_livraison() {
        return localisation_livraison;
    }

    public void setLocalisation_livraison(String localisation_livraison) {
        this.localisation_livraison = localisation_livraison;
    }

}
