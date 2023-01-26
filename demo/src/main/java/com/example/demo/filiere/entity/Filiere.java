package com.example.demo.filiere.entity;


import com.example.demo.atelier.entity.Atelier;
import com.example.demo.etudiant.entity.Etudiant;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="filiere")


public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_filiere")
    private int id_filiere;

    @Column(name="nom_filire")
    private String nom_filiere;


    @OneToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "id_atelier")
    private Atelier atelier;
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "filiere",
            cascade = {CascadeType.ALL})

    private List<Etudiant> etudiants;


    public void add(Etudiant theEtudiant){
        if(etudiants==null){
            etudiants=new ArrayList<>();
        }

        etudiants.add(theEtudiant);
        theEtudiant.setFiliere(this);
    }

    @OneToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "id_resp_atelier")
    private ResponsableAtelier responsableAtelier;


    public int getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(int id_filiere) {
        this.id_filiere = id_filiere;
    }

    public String getNom_filiere() {
        return nom_filiere;
    }

    public void setNom_filiere(String nom_filiere) {
        this.nom_filiere = nom_filiere;
    }

    public Atelier getAtelier() {
        return atelier;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public ResponsableAtelier getResponsableAtelier() {
        return responsableAtelier;
    }

    public void setResponsableAtelier(ResponsableAtelier responsableAtelier) {
        this.responsableAtelier = responsableAtelier;
    }
}
