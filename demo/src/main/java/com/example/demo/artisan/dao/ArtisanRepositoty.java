package com.example.demo.artisan.dao;

import com.example.demo.artisan.entity.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtisanRepositoty extends JpaRepository<Artisan,Integer> {
}
