package com.example.demo.filiere.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.filiere.dao.FiliereRepository;
import com.example.demo.filiere.entity.Filiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereServiceImp implements FiliereService{

    @Autowired
    private FiliereRepository filiereRepository;

    @Override
    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    @Override
    public void save(Filiere filiere) {
        filiereRepository.save(filiere);
    }

    @Override
    public Filiere findById(int theId) {
        Optional<Filiere> result=filiereRepository.findById(theId);

        Filiere filiere=null;
        if(result.isPresent()){
            filiere=result.get();
        }
        else {
            throw  new RuntimeException("La filiere qui a Id :"+theId+"n'existe pas");
        }
        return filiere;
    }

    @Override
    public void deleteById(int theId) {

        filiereRepository.deleteById(theId);
    }

    @Override
    public List<Filiere> searchBy(String name) {
        return filiereRepository.findByNom_filiere(name);
    }
}
