package com.example.demo.matiere_premiere.dao;

import com.example.demo.entretien.entity.Entretien;

import com.example.demo.matiere_premiere.entity.MatierePremiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatiereRepository extends JpaRepository<MatierePremiere,Integer> {

    @Query("SELECT t FROM MatierePremiere t WHERE t.nom_matiere = ?1")
    public List<MatierePremiere> findByNom_matiere(String name);

    @Query("SELECT t FROM MatierePremiere t WHERE t.date_recu_matiere between ?1 and ?2")
    public List<MatierePremiere> findByDate_matierePremiere(String date1,String date2);

}
