package com.example.demo.matiere_premiere.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.matiere_premiere.entity.MatierePremiere;

import java.util.List;

public interface Matiere_premiereService {

    public List<MatierePremiere> findAll();

    public void save(MatierePremiere matierePremiere);

    public MatierePremiere findById(int theId);

    public void deleteById(int theId);

    public List<MatierePremiere> searchBy(String name);

    public List<MatierePremiere> searchByDate(String date1,String date2);
}
