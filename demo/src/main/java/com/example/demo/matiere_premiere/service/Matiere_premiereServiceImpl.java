package com.example.demo.matiere_premiere.service;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.matiere_premiere.dao.MatiereRepository;
import com.example.demo.matiere_premiere.entity.MatierePremiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Matiere_premiereServiceImpl implements Matiere_premiereService{

    @Autowired
    private MatiereRepository matiereRepository;
    @Override
    public List<MatierePremiere> findAll() {
        return matiereRepository.findAll();
    }

    @Override
    public void save(MatierePremiere matierePremiere) {
        matiereRepository.save(matierePremiere);

    }

    @Override
    public MatierePremiere findById(int theId) {
        Optional<MatierePremiere> result=matiereRepository.findById(theId);

        MatierePremiere matierePremiere=null;
        if(result.isPresent()){
            matierePremiere=result.get();
        }
        else {
            throw  new RuntimeException("La matiere premiere qui a Id :"+theId+"n'existe pas");
        }
        return matierePremiere;
    }

    @Override
    public void deleteById(int theId) {
        matiereRepository.deleteById(theId);

    }

    @Override
    public List<MatierePremiere> searchBy(String name) {
        return matiereRepository.findByNom_matiere(name);
    }

    @Override
    public List<MatierePremiere> searchByDate(String date1, String date2) {
        return matiereRepository.findByDate_matierePremiere(date1,date2);
    }
}
