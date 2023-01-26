package com.example.demo.piéce_rechanges.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.piéce_rechanges.entity.PieceRechange;

import java.util.List;

public interface Piece_rechangeService {

    public List<PieceRechange> findAll();
    public void save(PieceRechange pieceRechange);

    public PieceRechange findById(int theId);

    public void deleteById(int theId);

    public List<PieceRechange> searchBy(String name);

    public List<PieceRechange> searchByDate(String date1,String date2);
}
