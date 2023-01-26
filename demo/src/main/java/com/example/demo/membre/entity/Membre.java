package com.example.demo.membre.entity;

import com.example.demo.commission.entity.Commission;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="membre")
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_membre")
    private int id_membre;

    @Column(name="nom_membre")
    private String nom_membre;

    @Column(name="prenom_membre")
    private String prenom_membre;


    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "appartenir_commission_membre",
            joinColumns=@JoinColumn(name="id_membre"),
            inverseJoinColumns=@JoinColumn(name = "id_commision")
    )
    private List<Commission> commissions;

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getNom_membre() {
        return nom_membre;
    }

    public void setNom_membre(String nom_membre) {
        this.nom_membre = nom_membre;
    }

    public String getPrenom_membre() {
        return prenom_membre;
    }

    public void setPrenom_membre(String prenom_membre) {
        this.prenom_membre = prenom_membre;
    }

    public List<Commission> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<Commission> commissions) {
        this.commissions = commissions;
    }

    @Override
    public String toString() {
        return this.nom_membre+" "+this.prenom_membre;
    }
}
