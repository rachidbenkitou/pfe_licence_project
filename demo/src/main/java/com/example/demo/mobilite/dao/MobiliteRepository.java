package com.example.demo.mobilite.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.mobilite.entity.Mobilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MobiliteRepository extends JpaRepository<Mobilite,Integer> {


    @Query("SELECT t FROM Mobilite t WHERE t.id_mobilite = ?1")
    public List<Mobilite> findById_mobilite(int id);

    @Query("SELECT t FROM Mobilite t WHERE t.date_mobilite between ?1 and ?2")
    public List<Mobilite> findByDate_mobilite(String date1,String date2);
}
