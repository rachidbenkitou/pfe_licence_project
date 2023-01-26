package com.example.demo.responsable_atelier.entity;


import com.example.demo.entretien.entity.Entretien;
import com.example.demo.filiere.entity.Filiere;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="responsable_atelier")

public class ResponsableAtelier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_resp_atelier")
    private int id_resp_atelier;


    @Column(name="nom_resp_atelier")
    private String nom_resp_atelier;


    @Column(name="prenom_resp_atelier")
    private String prenom_resp_atelier;

    @OneToOne(fetch=FetchType.LAZY,mappedBy = "responsableAtelier")
    private Filiere filiere;


    @OneToMany(fetch=FetchType.LAZY,mappedBy = "responsableAtelier",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Entretien> entretiens;


    public void add(Entretien theEntretien){
        if(entretiens==null){
            entretiens=new ArrayList<>();
        }

        entretiens.add(theEntretien);
        theEntretien.setResponsableAtelier(this);
    }

    public Filiere getFiliere() {
        if(filiere==null) filiere=new Filiere();
        return filiere;
    }

    public List<Entretien> getEntretiens() {
        if(entretiens==null) entretiens=new ArrayList<>();
        return entretiens;
    }

    public int getId_resp_atelier() {
        return id_resp_atelier;
    }

    public void setId_resp_atelier(int id_resp_atelier) {
        this.id_resp_atelier = id_resp_atelier;
    }

    public String getNom_resp_atelier() {
        return nom_resp_atelier;
    }

    public void setNom_resp_atelier(String nom_resp_atelier) {
        this.nom_resp_atelier = nom_resp_atelier;
    }

    public String getPrenom_resp_atelier() {
        return prenom_resp_atelier;
    }

    public void setPrenom_resp_atelier(String prenom_resp_atelier) {
        this.prenom_resp_atelier = prenom_resp_atelier;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }
}
