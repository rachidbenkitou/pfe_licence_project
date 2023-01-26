package com.example.demo.responsable_atelier.service;

import com.example.demo.responsable_atelier.dao.ResponsableAtelierRepository;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResponsableAtelierServiceImpl implements ResponsableAtelierService{

    @Autowired
    private ResponsableAtelierRepository responsableAtelierRepository;
    @Override
    public List<ResponsableAtelier> findAll() {
        return responsableAtelierRepository.findAll();
    }
}
