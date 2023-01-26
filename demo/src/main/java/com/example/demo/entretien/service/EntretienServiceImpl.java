package com.example.demo.entretien.service;

import com.example.demo.entretien.dao.EntretienRepository;
import com.example.demo.entretien.entity.Entretien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntretienServiceImpl implements EntretienService{

    @Autowired
    private EntretienRepository entretienRepository;

    public List<Entretien> findAll() {
        return entretienRepository.findAll();
    }

    public void save(Entretien entretien) {
        entretienRepository.save(entretien);
    }

    public Entretien findById(int theId) {
        Optional<Entretien> result=entretienRepository.findById(theId);

        Entretien theEntretien=null;
        if(result.isPresent()){
            theEntretien=result.get();
        }
        else {
            throw  new RuntimeException("L'equipement qui a Id :"+theId+"n'existe pas");
        }
        return theEntretien;


    }

    public void deleteById(int theId) {
        entretienRepository.deleteById(theId);

    }

    @Override
    public List<Entretien> searchBy(String type) {
        return entretienRepository.findByType_entretien(type);
    }

    @Override
    public List<Entretien> searchByDate(String date1, String date2) {
        return entretienRepository.findByDate_entretien(date1,date2);
    }


}
