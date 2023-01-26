package com.example.demo.atelier.entity;


import com.example.demo.artisan.entity.Artisan;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.filiere.entity.Filiere;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="atelier")

public class Atelier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_atelier")
    private int id_atelier;

    @Column(name="nom_atelier")
    private String nom_atelier;

    @Column(name="description_atelier")
    private String description_atelier;



    @OneToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy = "atelier"
            )
    private List<Artisan> artisans;


    @OneToOne(fetch=FetchType.LAZY,mappedBy = "atelier",cascade = {CascadeType.ALL})
    private Filiere filiere ;

    public Filiere filiereIsExist(){
        if(filiere==null)
            filiere=new Filiere();
        return filiere;
    }







    public void add(Artisan theArtisan){
        if(artisans==null){
            artisans=new ArrayList<>();
        }

        artisans.add(theArtisan);
        theArtisan.setAtelier(this);
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "exister_atelier_equipement",
            joinColumns=@JoinColumn(name="id_atelier"),
            inverseJoinColumns=@JoinColumn(name = "id_equipement")
    )
    private List<Equipement> equipements;



    //Add default values to properties if they are empty

    public List<Artisan> getArtisans() {
        if(artisans==null) artisans=new ArrayList<>();
        return artisans;
    }

    public Filiere getFiliere() {
        if(filiere==null) filiere=new Filiere();
        return filiere;
    }

    public List<Equipement> getEquipements() {
        if (equipements==null) equipements=new ArrayList<>();
        return equipements;
    }

    public int getId_atelier() {
        return id_atelier;
    }

    public void setId_atelier(int id_atelier) {
        this.id_atelier = id_atelier;
    }

    public String getNom_atelier() {
        return nom_atelier;
    }

    public void setNom_atelier(String nom_atelier) {
        this.nom_atelier = nom_atelier;
    }

    public String getDescription_atelier() {
        return description_atelier;
    }

    public void setDescription_atelier(String description_atelier) {
        this.description_atelier = description_atelier;
    }

    public void setArtisans(List<Artisan> artisans) {
        this.artisans = artisans;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }
}
