package com.waterbilling.demo.controller;

import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.service.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watermeters")
public class WaterMeterController {

    @Autowired
    private WaterMeterService waterMeterService;

}
