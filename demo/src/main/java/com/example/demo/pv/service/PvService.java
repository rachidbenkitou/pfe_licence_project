package com.example.demo.pv.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.mobilite.entity.Mobilite;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import com.example.demo.pv.entity.Pv;

import java.util.List;

public interface PvService {


    public List<Pv> findAll();
    public void save(Pv pv);

    public Pv findById(int theId);

    public void deleteById(int theId);

    public List<Pv> searchBy(int id);

    public List<Pv> searchByDate(String date1,String date2);
}
