package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.WaterMeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterMeterReadingRepository extends JpaRepository<WaterMeterReading, Integer> {
}
