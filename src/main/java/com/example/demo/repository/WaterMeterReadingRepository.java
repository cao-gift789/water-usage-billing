package com.example.demo.repository;

import com.example.demo.model.WaterMeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterMeterReadingRepository extends JpaRepository<WaterMeterReading, Integer> {
}
