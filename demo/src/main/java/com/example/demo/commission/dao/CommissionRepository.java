package com.example.demo.commission.dao;

import com.example.demo.commission.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission,Integer> {
}
