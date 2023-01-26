package com.example.demo.pv.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.pv.entity.Pv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PvRepository extends JpaRepository<Pv,Integer> {
    @Query("SELECT t FROM Pv t WHERE t.id_pv = ?1")
    public List<Pv> findById_pv(int id);

    @Query("SELECT t FROM Pv t WHERE t.date_pv between ?1 and ?2")
    public List<Pv> findByDate_pv(String date1,String date2);

}
