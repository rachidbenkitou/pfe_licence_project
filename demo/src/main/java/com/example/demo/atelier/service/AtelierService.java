package com.example.demo.atelier.service;

import com.example.demo.atelier.entity.Atelier;
import com.example.demo.entretien.entity.Entretien;

import java.util.List;

public interface AtelierService {



    public List<Atelier> findAll();
    public Atelier findById(int theId);



    public void save(Atelier atelier);



    public void deleteById(int theId);

    public List<Atelier> searchBy(String name);

}
