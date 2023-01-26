package com.example.demo.materiel.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.materiel.entity.Materiel;

import java.util.List;

public interface MaterielService {

    public List<Materiel> findAll();

    public void save(Materiel materiel);

    public Materiel findById(int theId);

    public void deleteById(int theId);

    public List<Materiel> searchBy(String name);

    public List<Materiel> searchByDate(String date1,String date2);

}
