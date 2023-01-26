package com.example.demo.produit.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.produit.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Integer> {

    @Query("SELECT t FROM Produit t WHERE t.nom_produit = ?1")
    public List<Produit> findByNom_produit(String name);
}
