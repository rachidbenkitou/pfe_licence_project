package com.example.demo.matiere_premiere.entity;

import com.example.demo.produit.entity.Produit;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="matiere_premiere")
@Data
public class MatierePremiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_matiere")
    private int id_matiere;

    @Column(name="nom_matiere")
    private String nom_matiere;

    @Column(name="designation_matiere")
    private String designation_matiere;

    @Column(name="prix_matiere")
    private double prix_matiere;

    @Column(name="quantite_matiere")
    private int quantite_matiere;

    @Column(name="date_recu_matiere")
    private String date_recu_matiere;


    @ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name = "utiliser_produit_matiere",
            joinColumns=@JoinColumn(name="id_matiere"),
            inverseJoinColumns=@JoinColumn(name = "id_produit")
    )
    private List<Produit> produits;

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public String getNom_matiere() {
        return nom_matiere;
    }

    public void setNom_matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }

    public String getDesignation_matiere() {
        return designation_matiere;
    }

    public void setDesignation_matiere(String designation_matiere) {
        this.designation_matiere = designation_matiere;
    }

    public double getPrix_matiere() {
        return prix_matiere;
    }

    public void setPrix_matiere(double prix_matiere) {
        this.prix_matiere = prix_matiere;
    }

    public int getQuantite_matiere() {
        return quantite_matiere;
    }

    public void setQuantite_matiere(int quantite_matiere) {
        this.quantite_matiere = quantite_matiere;
    }

    public String getDate_recu_matiere() {
        return date_recu_matiere;
    }

    public void setDate_recu_matiere(String date_recu_matiere) {
        this.date_recu_matiere = date_recu_matiere;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return this.getNom_matiere();
    }
}
