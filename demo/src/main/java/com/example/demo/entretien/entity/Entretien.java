package com.example.demo.entretien.entity;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="entretien")

public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_entretien")
    private int id_entretien;

    @Column(name="date_entretien")

    private String date_entretien;

    @Column(name="type_entretien")
    private String type_entretien;

    @Column(name="etat_avant_entretien")
    private String etat_avant_entretien;

    @Column(name="etat_apres_entretien")
    private String etat_apres_entretien;


    /*
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;
   */
    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_resp_atelier")
    private ResponsableAtelier responsableAtelier;



    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_equipement")
    private Equipement equipement;


    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_operateur")
    private Operateur operateur;


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "demande_piece_entretien",
            joinColumns=@JoinColumn(name="id_entretien"),
            inverseJoinColumns=@JoinColumn(name = "id_piece")
    )
    private List<PieceRechange> pieceRechange=new ArrayList<>();


    public ResponsableAtelier getResponsableAtelier() {
        if(responsableAtelier==null) responsableAtelier=new ResponsableAtelier();
        return responsableAtelier;
    }

    public Equipement getEquipement() {
        if(equipement==null) equipement=new Equipement();
        return equipement;
    }

    public Operateur getOperateur() {
        if(operateur==null) operateur=new Operateur();
        return operateur;
    }




    /*
    public List<valider_entretien_utilisateur> getValidation_en() {
        if(validation_en==null) validation_en=new ArrayList<>();
        return validation_en;
    }

    */



    public int getId_entretien() {
        return id_entretien;
    }

    public String getDate_entretien() {
        return date_entretien;
    }

    public String getType_entretien() {
        return type_entretien;
    }

    public String getEtat_avant_entretien() {
        return etat_avant_entretien;
    }

    public String getEtat_apres_entretien() {
        return etat_apres_entretien;
    }

    public void setId_entretien(int id_entretien) {
        this.id_entretien = id_entretien;
    }

    public void setDate_entretien(String date_entretien) {
        this.date_entretien = date_entretien;
    }

    public void setType_entretien(String type_entretien) {
        this.type_entretien = type_entretien;
    }

    public void setEtat_avant_entretien(String etat_avant_entretien) {
        this.etat_avant_entretien = etat_avant_entretien;
    }

    public void setEtat_apres_entretien(String etat_apres_entretien) {
        this.etat_apres_entretien = etat_apres_entretien;
    }

    public void setResponsableAtelier(ResponsableAtelier responsableAtelier) {
        this.responsableAtelier = responsableAtelier;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }

    public List<PieceRechange> getPieceRechange() {
        return pieceRechange;
    }

    public void setPieceRechange(List<PieceRechange> pieceRechange) {
        this.pieceRechange = pieceRechange;
    }


  /*
    public void setValidation_en(List<valider_entretien_utilisateur> validation_en) {
        this.validation_en = validation_en;
    }

     */

}
