package com.example.demo.repository;

import com.example.demo.model.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterMeterRepository extends JpaRepository<WaterMeter, Integer> {
}
