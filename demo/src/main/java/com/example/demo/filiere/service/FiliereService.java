package com.example.demo.filiere.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.filiere.entity.Filiere;

import java.util.List;

public interface FiliereService
{
    public List<Filiere> findAll();



    public void save(Filiere filiere);

    public Filiere findById(int theId);

    public void deleteById(int theId);

    public List<Filiere> searchBy(String name);
}
