package com.example.demo.service;


import com.example.demo.model.WaterMeter;
import com.example.demo.repository.WaterMeterRepository;
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
