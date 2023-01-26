package com.example.demo.mobilite.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.mobilite.entity.Mobilite;

import java.util.List;

public interface MobiliteService {


    public List<Mobilite> findAll();
    public void save(Mobilite mobilite);

    public Mobilite findById(int theId);

    public void deleteById(int theId);

    public List<Mobilite> searchBy(int id);

    public List<Mobilite> searchByDate(String date1,String date2);
}
