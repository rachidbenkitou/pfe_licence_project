package com.example.demo.piéce_rechanges.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource( path = "pieceRechanges")
public interface PieceRechangesRepository extends JpaRepository<PieceRechange,Integer> {


    @Query("SELECT t FROM PieceRechange t WHERE t.nom_piece = ?1")
    public List<PieceRechange> findByNom_piéce(String name);

    @Query("SELECT t FROM PieceRechange t WHERE t.date_recu_piece between ?1 and ?2")
    public List<PieceRechange> findByDate_piece(String date1,String date2);

}
