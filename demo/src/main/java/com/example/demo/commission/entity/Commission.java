package com.example.demo.commission.entity;

import com.example.demo.membre.entity.Membre;
import com.example.demo.produit.entity.Produit;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="commission")

public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_commision")
    private int id_commision;



    @OneToMany(fetch=FetchType.LAZY,mappedBy = "commission",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Produit> produits;


    public void add(Produit theProduit){
        if(produits==null){
            produits=new ArrayList<>();
        }

        produits.add(theProduit);
        theProduit.setCommission(this);
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "appartenir_commission_membre",
            joinColumns=@JoinColumn(name="id_commision"),
            inverseJoinColumns=@JoinColumn(name = "id_membre")
    )
    private List<Membre> membres;


    public List<Produit> getProduits() {
        if(produits==null) produits=new ArrayList<>();
        return produits;
    }

    public List<Membre> getMembres() {
        if(membres==null) membres=new ArrayList<>();
        return membres;
    }

    public int getId_commision() {
        return id_commision;
    }

    public void setId_commision(int id_commision) {
        this.id_commision = id_commision;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }

    @Override
    public String toString() {
        return membres.toString() ;
    }
}
