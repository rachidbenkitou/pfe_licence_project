package com.example.demo.produit.entity;


import com.example.demo.commission.entity.Commission;

import com.example.demo.etudiant.entity.Etudiant;
import com.example.demo.matiere_premiere.entity.MatierePremiere;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="produit")

public class Produit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produit")
    private int id_produit;


    @Column(name="nom_produit")
    private String nom_produit;


    @Column(name="designation_produit")
    private String designation_produit;

    @Column(name="prix_produit")
    private double prix_produit;

    @Column(name="quantite_produit")
    private int quantite_produit;

    @Column(name="type_produit")
    private String type_produit;



    private String image_produit;





    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_commision")
    private Commission commission;

    /*
    @ManyToMany
    @JoinTable(
            name = "creer_produit_etudiant",
            joinColumns=@JoinColumn(name="id_produit"),
            inverseJoinColumns=@JoinColumn(name = "id_etudiant")
    )
    private List<Etudiant> etudiants;
*/


    @ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name = "utiliser_produit_matiere",
            joinColumns=@JoinColumn(name="id_produit"),
            inverseJoinColumns=@JoinColumn(name = "id_matiere")
    )
    private List<MatierePremiere> matierePremieres;



    @ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name = "creer_etudiant_produit",
            joinColumns=@JoinColumn(name="id_produit"),
            inverseJoinColumns=@JoinColumn(name = "id_etudiant")
    )
    private List<Etudiant> etudiants;


    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getDesignation_produit() {
        return designation_produit;
    }

    public void setDesignation_produit(String designation_produit) {
        this.designation_produit = designation_produit;
    }

    public double getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(double prix_produit) {
        this.prix_produit = prix_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public List<MatierePremiere> getMatierePremieres() {
        return matierePremieres;
    }

    public void setMatierePremieres(List<MatierePremiere> matierePremieres) {
        this.matierePremieres = matierePremieres;
    }


    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }
}
