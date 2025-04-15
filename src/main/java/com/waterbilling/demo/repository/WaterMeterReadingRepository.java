package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.model.WaterMeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterMeterReadingRepository extends JpaRepository<WaterMeterReading, Integer> {
    List<WaterMeterReading> findAllByWaterMeter(WaterMeter waterMeter);

    @Query("SELECT w FROM WaterMeterReading w " +
            "WHERE w.waterMeter.waterMeterId = :waterMeterId " +
            "AND w.dateRecorded = (SELECT MAX(w2.dateRecorded) FROM WaterMeterReading w2 WHERE w2.waterMeter.waterMeterId = :waterMeterId)")
    WaterMeterReading findLatestByWaterMeterId(@Param("waterMeterId") Integer waterMeterId);
}
