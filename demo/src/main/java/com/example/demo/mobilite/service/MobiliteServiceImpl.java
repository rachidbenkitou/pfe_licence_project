package com.example.demo.mobilite.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.mobilite.dao.MobiliteRepository;
import com.example.demo.mobilite.entity.Mobilite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobiliteServiceImpl implements MobiliteService{

    @Autowired
    private MobiliteRepository mobiliteRepository;

    @Override
    public List<Mobilite> findAll() {
        return mobiliteRepository.findAll();
    }

    @Override
    public void save(Mobilite mobilite) {
        mobiliteRepository.save(mobilite);

    }

    @Override
    public Mobilite findById(int theId) {
        Optional<Mobilite> result=mobiliteRepository.findById(theId);

        Mobilite mobilite=null;
        if(result.isPresent()){
            mobilite=result.get();
        }
        else {
            throw  new RuntimeException("Mobilite qui a Id :"+theId+"n'existe pas");
        }
        return mobilite;
    }

    @Override
    public void deleteById(int theId) {
        mobiliteRepository.deleteById(theId);

    }

    @Override
    public List<Mobilite> searchBy(int id) {
        return mobiliteRepository.findById_mobilite(id);
    }

    @Override
    public List<Mobilite> searchByDate(String date1, String date2) {
        return mobiliteRepository.findByDate_mobilite(date1,date2);
    }
}
