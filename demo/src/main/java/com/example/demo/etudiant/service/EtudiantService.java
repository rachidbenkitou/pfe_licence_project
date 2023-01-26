package com.example.demo.etudiant.service;

import com.example.demo.etudiant.entity.Etudiant;
import com.example.demo.etudiant.entity.EtudiantnotfoundException;

import java.util.List;

public interface EtudiantService {

    public List<Etudiant> findAll();
    public void save(Etudiant etudiant);

    public Etudiant findById(int theId);

    public void deleteById(int theId);

    public Etudiant get(int id) throws EtudiantnotfoundException;


}
