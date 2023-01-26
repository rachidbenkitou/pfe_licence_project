package com.example.demo.piéce_rechanges.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.piéce_rechanges.controller.Piece_rechangeController;
import com.example.demo.piéce_rechanges.dao.PieceRechangesRepository;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Piece_rechangeServiceImpl implements Piece_rechangeService{
    @Autowired
    private PieceRechangesRepository pieceRechangesRepository;

    @Override
    public List<PieceRechange> findAll() {
        return pieceRechangesRepository.findAll();
    }

    @Override
    public void save(PieceRechange pieceRechange) {

        pieceRechangesRepository.save(pieceRechange);
    }

    @Override
    public PieceRechange findById(int theId) {
        Optional<PieceRechange> result=pieceRechangesRepository.findById(theId);

        PieceRechange pieceRechange=null;
        if(result.isPresent()){
            pieceRechange=result.get();
        }
        else {
            throw  new RuntimeException("La piéce qui a Id :"+theId+"n'existe pas");
        }
        return pieceRechange;
    }

    @Override
    public void deleteById(int theId) {
        pieceRechangesRepository.deleteById(theId);
    }

    @Override
    public List<PieceRechange> searchBy(String name) {
        return pieceRechangesRepository.findByNom_piéce(name);
    }

    @Override
    public List<PieceRechange> searchByDate(String date1, String date2) {
        return pieceRechangesRepository.findByDate_piece(date1,date2);
    }
}
