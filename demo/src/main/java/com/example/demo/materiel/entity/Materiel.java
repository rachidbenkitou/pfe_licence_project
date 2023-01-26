package com.example.demo.materiel.entity;

import com.example.demo.materiel_demandeur.entity.livraison_materiel_demandeur;
import com.example.demo.mobilite.entity.Mobilite;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="materiel")

public class Materiel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_materiel")
    private int id_materiel;

    @Column(name="nom_materiel")
    private String nom_materiel;

    @Column(name="marque_materiel")
    private String marque_materiel;

    @Column(name="designation_materiel")
    private String designation_materiel;

    @Column(name="prix_materiel")
    private double prix_materiel;

    @Column(name="quantite_materiel")
    private int quantite_materiel;

    @Column(name="date_recu_materiel")
    private String date_recu_materiel;

    @Column(name="localisation_materiel")
    private String localisation_materiel;


    @OneToMany(cascade = {CascadeType.ALL},
            fetch=FetchType.LAZY,mappedBy = "materiel"
    )
    private List<Mobilite> mobilites;


/*
    @OneToMany(cascade = {CascadeType.ALL},
            fetch=FetchType.LAZY,mappedBy = "materiel"
            )
    private List<Mobilite> mobilites;




    public void add(Mobilite theMobilite){
        if(mobilites==null){
            mobilites=new ArrayList<>();
        }

        mobilites.add(theMobilite);
        theMobilite.setMateriel(this);
    }

*/


    @OneToMany(fetch=FetchType.LAZY,mappedBy = "materiel",cascade = {CascadeType.ALL})
    Set<livraison_materiel_demandeur> livraison;



    public int getId_materiel() {
        return id_materiel;
    }

    public void setId_materiel(int id_materiel) {
        this.id_materiel = id_materiel;
    }

    public String getNom_materiel() {
        return nom_materiel;
    }

    public void setNom_materiel(String nom_materiel) {
        this.nom_materiel = nom_materiel;
    }

    public String getMarque_materiel() {
        return marque_materiel;
    }

    public void setMarque_materiel(String marque_materiel) {
        this.marque_materiel = marque_materiel;
    }

    public String getDesignation_materiel() {
        return designation_materiel;
    }

    public void setDesignation_materiel(String designation_materiel) {
        this.designation_materiel = designation_materiel;
    }

    public double getPrix_materiel() {
        return prix_materiel;
    }

    public void setPrix_materiel(double prix_materiel) {
        this.prix_materiel = prix_materiel;
    }

    public int getQuantite_materiel() {
        return quantite_materiel;
    }

    public void setQuantite_materiel(int quantite_materiel) {
        this.quantite_materiel = quantite_materiel;
    }

    public String getDate_recu_materiel() {
        return date_recu_materiel;
    }

    public void setDate_recu_materiel(String date_recu_materiel) {
        this.date_recu_materiel = date_recu_materiel;
    }

    public String getLocalisation_materiel() {
        return localisation_materiel;
    }

    public void setLocalisation_materiel(String localisation_materiel) {
        this.localisation_materiel = localisation_materiel;
    }
/*
    public List<Mobilite> getMobilites() {
        return mobilites;
    }

    public void setMobilites(List<Mobilite> mobilites) {
        this.mobilites = mobilites;
    }

    public Set<livraison_materiel_demandeur> getLivraison() {
        return livraison;
    }

    public void setLivraison(Set<livraison_materiel_demandeur> livraison) {
        this.livraison = livraison;
    }

 */

    @Override
    public String toString() {
        return this.nom_materiel;
    }

    public List<Mobilite> getMobilites() {
        return mobilites;
    }

    public void setMobilites(List<Mobilite> mobilites) {
        this.mobilites = mobilites;
    }

    public Set<livraison_materiel_demandeur> getLivraison() {
        return livraison;
    }

    public void setLivraison(Set<livraison_materiel_demandeur> livraison) {
        this.livraison = livraison;
    }
}
