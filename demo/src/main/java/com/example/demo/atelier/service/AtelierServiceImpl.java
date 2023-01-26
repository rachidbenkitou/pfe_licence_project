package com.example.demo.atelier.service;

import com.example.demo.atelier.dao.AtelierRepository;
import com.example.demo.atelier.entity.Atelier;
import com.example.demo.entretien.entity.Entretien;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtelierServiceImpl implements AtelierService{

    private AtelierRepository atelierRepository;

    public AtelierServiceImpl(AtelierRepository TheAtelierRepository){
        atelierRepository=TheAtelierRepository;
    }

    @Override
    public List<Atelier> findAll() {
        return atelierRepository.findAll();
    }

    @Override
    public Atelier findById(int theId) {
        Optional<Atelier> result=atelierRepository.findById(theId);

        Atelier theAtelier=null;
        if(result.isPresent()){
            theAtelier=result.get();
        }
        else {
            throw  new RuntimeException("L'atelier qui a Id :"+theId+"n'existe pas");
        }
        return theAtelier;
    }

    @Override
    public void save(Atelier atelier) {
        atelierRepository.save(atelier);
    }

    @Override
    public void deleteById(int theId) {

        atelierRepository.deleteById(theId);
    }

    @Override
    public List<Atelier> searchBy(String name) {
        return atelierRepository.findByNom_atelier(name);
    }
}
