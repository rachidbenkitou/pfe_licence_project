package com.example.demo.mobilite.entity;

import com.example.demo.demandeur.entity.Demandeur;
import com.example.demo.materiel.entity.Materiel;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.user.entity.User;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="mobilite")
@Data
public class Mobilite {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mobilite")
    private int id_mobilite;

    @Column(name="date_mobilite")
    private String date_mobilite;

/*

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

 */




    @ManyToOne(
            fetch=FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_materiel")
    private Materiel materiel;



    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_demandeur")
    private Demandeur demandeur;





    public int getId_mobilite() {
        return id_mobilite;
    }

    public void setId_mobilite(int id_mobilite) {
        this.id_mobilite = id_mobilite;
    }

    public String getDate_mobilite() {
        return date_mobilite;
    }

    public void setDate_mobilite(String date_mobilite) {
        this.date_mobilite = date_mobilite;
    }

    /*
    public User getUtilisateur() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 */

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }


}
