package com.example.demo.etudiant.service;

import com.example.demo.etudiant.dao.EtudiantRepository;
import com.example.demo.etudiant.entity.Etudiant;
import com.example.demo.etudiant.entity.EtudiantnotfoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService{
    @Autowired
    private EtudiantRepository etudiantRepository;


    @Override
    public List<Etudiant> findAll() {

        return etudiantRepository.findAll();
    }

    @Override
    public void save(Etudiant etudiant) {
        etudiantRepository.save(etudiant);

    }

    @Override
    public Etudiant findById(int theId) {
        Optional<Etudiant> result=etudiantRepository.findById(theId);

        Etudiant theEtudiant=null;
        if(result.isPresent()){
            theEtudiant=result.get();
        }
        else {
            throw  new RuntimeException("L'étudeinat qui a Id :"+theId+"n'existe pas");
        }
        return theEtudiant;
    }

    @Override
    public void deleteById(int theId) {
        etudiantRepository.deleteById(theId);

    }


    public Etudiant get(int id) throws EtudiantnotfoundException {
        Optional<Etudiant> result = etudiantRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EtudiantnotfoundException("could not find any Etudiant with ID:"+id);
    }


}
