package com.example.demo.user.entity;

import com.example.demo.mobilite.entity.Mobilite;
import com.example.demo.role.entity.Role;


import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "nom_user")
    private String nom_user;

    @Column(name = "prenom_user")
    private  String prenom_user;

    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }
}
/*
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "user",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Mobilite> mobilites;


    public void add(Mobilite theMobilite){
        if(mobilites==null){
            mobilites=new ArrayList<>();
        }

        mobilites.add(theMobilite);
        theMobilite.setUser(this);
    }


    @OneToMany(fetch=FetchType.LAZY,mappedBy = "user")
    Set<valider_entretien_utilisateur> validation_en;


    @OneToMany(fetch=FetchType.LAZY,mappedBy = "user")
    Set<valider_mobilite_utilisateur> validation_M;



    public List<Mobilite> getMobilites() {
        return mobilites;
    }

    public void setMobilites(List<Mobilite> mobilites) {
        this.mobilites = mobilites;
    }

    public Set<valider_entretien_utilisateur> getValidation_en() {
        return validation_en;
    }

    public void setValidation_en(Set<valider_entretien_utilisateur> validation_en) {
        this.validation_en = validation_en;
    }

    public Set<valider_mobilite_utilisateur> getValidation_M() {
        return validation_M;
    }

    public void setValidation_M(Set<valider_mobilite_utilisateur> validation_M) {
        this.validation_M = validation_M;
    }

 */