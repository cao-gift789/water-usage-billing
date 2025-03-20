package com.waterbilling.demo.service;

import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.repository.WaterMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterMeterService {

    @Autowired
    private WaterMeterRepository waterMeterRepository;

    public WaterMeter saveWaterMeter(WaterMeter waterMeter) {
        return waterMeterRepository.save(waterMeter);
    }
}
