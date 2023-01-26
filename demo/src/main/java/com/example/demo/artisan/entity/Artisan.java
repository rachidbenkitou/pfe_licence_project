package com.example.demo.artisan.entity;


import com.example.demo.atelier.entity.Atelier;
import com.example.demo.opérateur.entity.Operateur;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="artisan")

public class Artisan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_artisan")
    private int id_artisan;

    @Column(name="nom_artisan")
    private String nom_artisan;


    @Column(name="prenom_artisan")
    private String prenom_artisan;


    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch=FetchType.LAZY)
    @JoinColumn(name="id_atelier")
    private Atelier atelier;



    //Add values to properties if they are empty
    public Atelier getAtelier() {
        if(atelier==null) atelier=new Atelier();
        return atelier;
    }


    public int getId_artisan() {
        return id_artisan;
    }

    public void setId_artisan(int id_artisan) {
        this.id_artisan = id_artisan;
    }

    public String getNom_artisan() {
        return nom_artisan;
    }

    public void setNom_artisan(String nom_artisan) {
        this.nom_artisan = nom_artisan;
    }

    public String getPrenom_artisan() {
        return prenom_artisan;
    }

    public void setPrenom_artisan(String prenom_artisan) {
        this.prenom_artisan = prenom_artisan;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }
}
