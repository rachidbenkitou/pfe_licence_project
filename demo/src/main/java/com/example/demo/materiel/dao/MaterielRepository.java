package com.example.demo.materiel.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.materiel.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterielRepository extends JpaRepository<Materiel,Integer> {


    @Query("SELECT t FROM Materiel t WHERE t.nom_materiel = ?1")
    public List<Materiel> findByNom_entretien(String name);

    @Query("SELECT t FROM Materiel t WHERE t.date_recu_materiel between ?1 and ?2")
    public List<Materiel> findByDate_materiel(String date1,String date2);
}
