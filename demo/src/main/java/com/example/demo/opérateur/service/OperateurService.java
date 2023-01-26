package com.example.demo.opérateur.service;

import com.example.demo.opérateur.entity.Operateur;

import java.util.List;

public interface OperateurService {

    public List<Operateur> findAll();
    public void save(Operateur operateur);

    public Operateur findById(int theId);

    public void deleteById(int theId);
}
