package com.example.demo.entretien.dao;

import com.example.demo.entretien.entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntretienRepository extends JpaRepository<Entretien,Integer> {




    @Query("SELECT t FROM Entretien t WHERE t.type_entretien = ?1")
    public List<Entretien> findByType_entretien(String type);

    @Query("SELECT t FROM Entretien t WHERE t.date_entretien between ?1 and ?2")
    public List<Entretien> findByDate_entretien(String date1,String date2);



}
