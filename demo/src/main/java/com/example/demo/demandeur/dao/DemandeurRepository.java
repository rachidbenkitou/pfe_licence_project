package com.example.demo.demandeur.dao;

import com.example.demo.demandeur.entity.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeurRepository extends JpaRepository<Demandeur,Integer> {
}
