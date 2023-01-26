package com.example.demo.produit.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.produit.entity.Produit;

import java.util.List;

public interface ProduitService {


    public List<Produit> findAll();
    public Produit save(Produit produit);

    public Produit findById(int theId);

    public void deleteById(int theId);

    public List<Produit> searchBy(String type);



}
