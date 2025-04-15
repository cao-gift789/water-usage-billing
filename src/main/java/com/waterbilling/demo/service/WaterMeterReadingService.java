package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.response.WaterMeterReadingResponse;
import com.waterbilling.demo.model.WaterMeterReading;
import com.waterbilling.demo.repository.WaterMeterReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterMeterReadingService {

    @Autowired
    private WaterMeterReadingRepository waterMeterReadingRepository;

    public WaterMeterReading create(WaterMeterReading reading) {
        reading.setWaterUsage(reading.getCurrentReading().subtract(reading.getPreviousReading()));
        return waterMeterReadingRepository.save(reading);
    }

    public WaterMeterReading findById(Integer id) {
        return waterMeterReadingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reading not found"));
    }

    public WaterMeterReading update(Integer id, WaterMeterReading reading) {
        WaterMeterReading existing = findById(id);
        existing.setWaterMeter(reading.getWaterMeter());
        existing.setDateRecorded(reading.getDateRecorded());
        existing.setPreviousReading(reading.getPreviousReading());
        existing.setCurrentReading(reading.getCurrentReading());
        existing.setWaterUsage(reading.getCurrentReading().subtract(reading.getPreviousReading()));
        existing.setEmployee(reading.getEmployee());
        existing.setInvoice(reading.getInvoice());
        return waterMeterReadingRepository.save(existing);
    }

    public void delete(Integer id) {
        WaterMeterReading reading = findById(id);
        waterMeterReadingRepository.delete(reading);
    }

}
