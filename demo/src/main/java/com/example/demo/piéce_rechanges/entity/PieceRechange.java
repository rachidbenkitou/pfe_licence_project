package com.example.demo.piéce_rechanges.entity;

import com.example.demo.entretien.entity.Entretien;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

@Entity
@Table(name="piece_rechange")
@Data
public class PieceRechange {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_piece")
    private int id_piece;

    @Column(name="nom_piece")
    private String nom_piece;

    @Column(name="quantite_piece")
    private int quantite_piece;

    @Column(name="prix_piece")
    private double prix_piece;

    @Column(name="designation_piece")
    private String designation_piece;

    @Column(name="date_recu_piece")
    private String date_recu_piece;


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "demande_piece_entretien",
            joinColumns=@JoinColumn(name="id_piece"),
            inverseJoinColumns=@JoinColumn(name = "id_entretien")
    )
    private Set<Entretien> entretiens=new HashSet<>();

    public int getId_piece() {
        return id_piece;
    }

    public void setId_piece(int id_piece) {
        this.id_piece = id_piece;
    }

    public String getNom_piece() {
        return nom_piece;
    }

    public void setNom_piece(String nom_piece) {
        this.nom_piece = nom_piece;
    }

    public int getQuantite_piece() {
        return quantite_piece;
    }

    public void setQuantite_piece(int quantite_piece) {
        this.quantite_piece = quantite_piece;
    }

    public double getPrix_piece() {
        return prix_piece;
    }

    public void setPrix_piece(double prix_piece) {
        this.prix_piece = prix_piece;
    }

    public String getDesignation_piece() {
        return designation_piece;
    }

    public void setDesignation_piece(String designation_piece) {
        this.designation_piece = designation_piece;
    }

    public String getDate_recu_piece() {
        return date_recu_piece;
    }

    public void setDate_recu_piece(String date_recu_piece) {
        this.date_recu_piece = date_recu_piece;
    }

    public Set<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(Set<Entretien> entretiens) {
        this.entretiens = entretiens;
    }


    @Override
    public String toString() {
        return this.nom_piece;
    }
}
