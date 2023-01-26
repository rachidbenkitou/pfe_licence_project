package com.example.demo.equipement.service;

import com.example.demo.equipement.dao.EquipementRepository;
import com.example.demo.equipement.entity.Equipement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementServiceImpl implements EquipementService{

    @Autowired
    private EquipementRepository equipementRepository;

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public void save(Equipement equipement) {
        equipementRepository.save(equipement);

    }

    @Override
    public Equipement findById(int theId) {
        Optional<Equipement> result=equipementRepository.findById(theId);

        Equipement equipement=null;
        if(result.isPresent()){
           equipement=result.get();
        }
        else {
            throw  new RuntimeException("L'equipement qui a Id :"+theId+"n'existe pas");
        }
        return equipement;
    }

    @Override
    public void deleteById(int theId) {
    equipementRepository.deleteById(theId);
    }

    @Override
    public List<Equipement> searchBy(String name) {
        return equipementRepository.findByNom_equipement(name);
    }

    @Override
    public List<Equipement> searchByDate(String date1, String date2) {
        return equipementRepository.findByDate_equipement(date1,date2);
    }
}
