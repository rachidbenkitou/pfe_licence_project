package com.example.demo.produit.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.produit.dao.ProduitRepository;
import com.example.demo.produit.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements  ProduitService{

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    @Override
    public Produit save(Produit produit) {
       Produit produit1=produitRepository.save(produit);
       return produit1;
    }

    @Override
    public Produit findById(int theId) {
        Optional<Produit> result=produitRepository.findById(theId);

        Produit produit=null;
        if(result.isPresent()){
            produit=result.get();
        }
        else {
            throw  new RuntimeException("Le produit qui a Id :"+theId+"n'existe pas");
        }
        return produit;
    }

    @Override
    public void deleteById(int theId) {
        produitRepository.deleteById(theId);

    }

    @Override
    public List<Produit> searchBy(String name) {
        return produitRepository.findByNom_produit(name);
    }


}
