package com.example.demo.opérateur.entity;


import com.example.demo.entretien.entity.Entretien;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="operateur")
@Data
public class Operateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_operateur")
    private int id_operateur;

    @Column(name="nom_operateur")
    private String nom_operateur;

    @Column(name="prenom_operateur")
    private String prenom_operateur;

    @Column(name="type_operateur")
    private String type_operateur;

    @Column(name="telephone_operateur")
    private String telephone_operateur;


    @OneToMany(mappedBy = "operateur",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Entretien> entretiens;


    public void add(Entretien theEntretien){
        if(entretiens==null){
            entretiens=new ArrayList<>();
        }

        entretiens.add(theEntretien);
        theEntretien.setOperateur(this);
    }

    public int getId_operateur() {
        return id_operateur;
    }

    public void setId_operateur(int id_operateur) {
        this.id_operateur = id_operateur;
    }

    public String getNom_operateur() {
        return nom_operateur;
    }

    public void setNom_operateur(String nom_operateur) {
        this.nom_operateur = nom_operateur;
    }

    public String getPrenom_operateur() {
        return prenom_operateur;
    }

    public void setPrenom_operateur(String prenom_operateur) {
        this.prenom_operateur = prenom_operateur;
    }

    public String getType_operateur() {
        return type_operateur;
    }

    public void setType_operateur(String type_operateur) {
        this.type_operateur = type_operateur;
    }

    public String getTelephone_operateur() {
        return telephone_operateur;
    }

    public void setTelephone_operateur(String telephone_operateur) {
        this.telephone_operateur = telephone_operateur;
    }

    public List<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }
}
