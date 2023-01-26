package com.example.demo.equipement.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.equipement.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipementRepository extends JpaRepository<Equipement,Integer> {


    @Query("SELECT t FROM Equipement t WHERE t.nom_equipement = ?1")
    public List<Equipement> findByNom_equipement(String name);

    @Query("SELECT t FROM Equipement t WHERE t.date_recu_equipement between ?1 and ?2")
    public List<Equipement> findByDate_equipement(String date1,String date2);
}
