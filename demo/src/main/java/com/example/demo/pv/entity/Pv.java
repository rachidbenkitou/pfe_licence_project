package com.example.demo.pv.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="pv")

public class Pv {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pv")
    private int id_pv;

    @Column(name="id_produit")
    private int id_produit;


    @Column(name="id_etudiant")
    private String id_etudiant;

    @Column(name="note")
    private double note;

    @Column(name="prix")
    private double prix;

    @Column(name="decision")
    private String decision;


    @Column(name="description")
    private String description;

    @Column(name="date_pv")
    private String date_pv;

    public String getDate_pv() {
        return date_pv;
    }

    public void setDate_pv(String date_pv) {
        this.date_pv = date_pv;
    }

    public int getId_pv() {
        return id_pv;
    }

    public void setId_pv(int id_pv) {
        this.id_pv = id_pv;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(String id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
