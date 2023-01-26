package com.example.demo.pv.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.piéce_rechanges.entity.PieceRechange;
import com.example.demo.pv.dao.PvRepository;
import com.example.demo.pv.entity.Pv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PvServiceImpl implements  PvService{
    @Autowired
    private PvRepository pvRepository;

    @Override
    public List<Pv> findAll() {
        return pvRepository.findAll();
    }

    @Override
    public void save(Pv pv) {
        pvRepository.save(pv);

    }

    @Override
    public Pv findById(int theId) {
        Optional<Pv> result=pvRepository.findById(theId);

        Pv pv=null;
        if(result.isPresent()){
            pv=result.get();
        }
        else {
            throw  new RuntimeException("Le pv qui a Id :"+theId+"n'existe pas");
        }
        return pv;
    }

    @Override
    public void deleteById(int theId) {

        pvRepository.deleteById(theId);
    }

    @Override
    public List<Pv> searchBy(int id) {
        return pvRepository.findById_pv(id);
    }


    @Override
    public List<Pv> searchByDate(String date1, String date2) {
        return pvRepository.findByDate_pv(date1,date2);
    }
}
