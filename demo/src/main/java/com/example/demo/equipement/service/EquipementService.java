package com.example.demo.equipement.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.equipement.entity.Equipement;

import java.util.List;

public interface EquipementService {

    public List<Equipement> findAll();
    public void save(Equipement equipement);

    public Equipement findById(int theId);

    public void deleteById(int theId);

    public List<Equipement> searchBy(String name);

    public List<Equipement> searchByDate(String date1,String date2);
}
