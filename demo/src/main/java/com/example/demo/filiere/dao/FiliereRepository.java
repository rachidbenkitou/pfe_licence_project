package com.example.demo.filiere.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.filiere.entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere,Integer> {
    @Query("SELECT t FROM Filiere t WHERE t.nom_filiere = ?1")
    public List<Filiere> findByNom_filiere(String name);
}
