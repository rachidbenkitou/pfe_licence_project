package com.example.demo.opérateur.service;

import com.example.demo.opérateur.dao.OperateurRepository;
import com.example.demo.opérateur.entity.Operateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperateurServiceImpl implements OperateurService{

    @Autowired
    private OperateurRepository operateurRepository;
    @Override
    public List<Operateur> findAll() {
        return operateurRepository.findAll();
    }

    @Override
    public void save(Operateur operateur) {
        operateurRepository.save(operateur);

    }

    @Override
    public Operateur findById(int theId) {
        Optional<Operateur> result=operateurRepository.findById(theId);

        Operateur operateur=null;
        if(result.isPresent()){
            operateur=result.get();
        }
        else {
            throw  new RuntimeException("L'equipement qui a Id :"+theId+"n'existe pas");
        }
        return operateur;
    }

    @Override
    public void deleteById(int theId) {
        operateurRepository.deleteById(theId);

    }
}
