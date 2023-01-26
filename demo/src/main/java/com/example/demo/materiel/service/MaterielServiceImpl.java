package com.example.demo.materiel.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.materiel.dao.MaterielRepository;
import com.example.demo.materiel.entity.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterielServiceImpl implements MaterielService{



    @Autowired
    private MaterielRepository materielRepository;

    @Override
    public List<Materiel> findAll() {
        return materielRepository.findAll();
    }

    @Override
    public void save(Materiel materiel) {
        materielRepository.save(materiel);

    }

    @Override
    public Materiel findById(int theId) {
        Optional<Materiel> result=materielRepository.findById(theId);

        Materiel materiel=null;
        if(result.isPresent()){
            materiel=result.get();
        }
        else {
            throw  new RuntimeException("Le Materiel qui a Id :"+theId+"n'existe pas");
        }
        return materiel;
    }

    @Override
    public void deleteById(int theId) {

        materielRepository.deleteById(theId);
    }

    @Override
    public List<Materiel> searchBy(String name) {
        return materielRepository.findByNom_entretien(name);
    }

    @Override
    public List<Materiel> searchByDate(String date1, String date2) {
        return materielRepository.findByDate_materiel(date1,date2);
    }
}
