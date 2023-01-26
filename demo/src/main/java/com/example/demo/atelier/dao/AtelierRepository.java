package com.example.demo.atelier.dao;

import com.example.demo.atelier.entity.Atelier;
import com.example.demo.entretien.entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtelierRepository extends JpaRepository<Atelier,Integer> {

    @Query("SELECT t FROM Atelier t WHERE t.nom_atelier = ?1")
    public List<Atelier> findByNom_atelier(String name);
}
