package com.example.demo.entretien.service;

import com.example.demo.entretien.entity.Entretien;

import java.util.List;

public interface EntretienService {

    public List<Entretien> findAll();
    public void save(Entretien entretien);

    public Entretien findById(int theId);

    public void deleteById(int theId);

    public List<Entretien> searchBy(String type);

    public List<Entretien> searchByDate(String date1,String date2);

}
