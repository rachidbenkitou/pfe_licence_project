package com.example.demo.equipement.entity;

import com.example.demo.atelier.entity.Atelier;
import com.example.demo.entretien.entity.Entretien;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="equipement")
@Data
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_equipement")
    private int id_equipement;

    @Column(name="nom_equipement")
    private String nom_equipement;

    @Column(name="marque_equipement")
    private String marque_equipement;

    @Column(name="designation_equipement")
    private String designation_equipement;

    @Column(name="description_equipement")
    private String description_equipement;

    @Column(name="prix_equipement")
    private double prix_equipement;

    @Column(name="date_recu_equipement")
    private String date_recu_equipement;



    @Column(name="quantite_equipement")
    private int quantite_equipement;




    @OneToMany(fetch=FetchType.LAZY,mappedBy = "equipement",
            cascade = {CascadeType.ALL})
    private List<Entretien> entretiens;


    public void add(Entretien theEntretien){
        if(entretiens==null){
            entretiens=new ArrayList<>();
        }

        entretiens.add(theEntretien);
        theEntretien.setEquipement(this);
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "exister_atelier_equipement",
            joinColumns=@JoinColumn(name="id_equipement"),
            inverseJoinColumns=@JoinColumn(name = "id_atelier")
    )
    private List<Atelier> ateliers;



    public List<Entretien> getEntretiens() {
        if(entretiens==null) entretiens=new ArrayList<>();
        return entretiens;
    }

    public List<Atelier> getAteliers() {
        if (ateliers==null) ateliers=new ArrayList<>();
        return ateliers;
    }

    public int getId_equipement() {
        return id_equipement;
    }

    public void setId_equipement(int id_equipement) {
        this.id_equipement = id_equipement;
    }

    public String getNom_equipement() {
        return nom_equipement;
    }

    public void setNom_equipement(String nom_equipement) {
        this.nom_equipement = nom_equipement;
    }

    public String getMarque_equipement() {
        return marque_equipement;
    }

    public void setMarque_equipement(String marque_equipement) {
        this.marque_equipement = marque_equipement;
    }

    public String getDesignation_equipement() {
        return designation_equipement;
    }

    public void setDesignation_equipement(String designation_equipement) {
        this.designation_equipement = designation_equipement;
    }

    public String getDescription_equipement() {
        return description_equipement;
    }

    public void setDescription_equipement(String description_equipement) {
        this.description_equipement = description_equipement;
    }

    public double getPrix_equipement() {
        return prix_equipement;
    }

    public void setPrix_equipement(double prix_equipement) {
        this.prix_equipement = prix_equipement;
    }



    public int getQuantite_equipement() {
        return quantite_equipement;
    }

    public void setQuantite_equipement(int quantite_equipement) {
        this.quantite_equipement = quantite_equipement;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    public void setAteliers(List<Atelier> ateliers) {
        this.ateliers = ateliers;
    }




    public String getDate_recu_equipement() {
        return date_recu_equipement;
    }

    public void setDate_recu_equipement(String date_recu_equipement) {
        this.date_recu_equipement = date_recu_equipement;
    }
}
