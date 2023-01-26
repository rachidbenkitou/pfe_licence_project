package com.example.demo.membre.dao;

import com.example.demo.membre.entity.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre,Integer> {
}
