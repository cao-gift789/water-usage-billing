package com.waterbilling.demo.service;

import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.repository.WaterMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterMeterService {

    @Autowired
    private WaterMeterRepository waterMeterRepository;

    public WaterMeter create(WaterMeter waterMeter) {

        return waterMeterRepository.save(waterMeter);
    }

    public WaterMeter findById(Integer id) {
        return waterMeterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WaterMeter not found"));
    }

    public WaterMeter update(Integer id, WaterMeter waterMeter) {
        WaterMeter existing = findById(id);
        existing.setSerialNumber(waterMeter.getSerialNumber());
        existing.setFacility(waterMeter.getFacility());
        existing.setInstallationDate(waterMeter.getInstallationDate());
        existing.setIsActive(waterMeter.getIsActive());
        return waterMeterRepository.save(existing);
    }

    public void delete(Integer id) {
        WaterMeter waterMeter = findById(id);
        waterMeterRepository.delete(waterMeter);
    }
}
