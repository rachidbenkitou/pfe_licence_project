package com.example.demo.etudiant.dao;

import com.example.demo.etudiant.entity.Etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {


}
