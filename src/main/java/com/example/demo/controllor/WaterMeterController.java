package com.example.demo.controllor;


import com.example.demo.model.WaterMeter;
import com.example.demo.service.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watermeters")
public class WaterMeterController {

    @Autowired
    private WaterMeterService waterMeterService;

    @PostMapping
    public WaterMeter createWaterMeter(@RequestBody WaterMeter waterMeter) {
        return waterMeterService.saveWaterMeter(waterMeter);
    }
}
