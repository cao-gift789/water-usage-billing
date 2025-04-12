package com.waterbilling.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waterbilling.demo.dto.response.ListWaterUsageResponse;
import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.service.WaterMeterService;

@Controller
@RequestMapping("/api/user")
public class WaterUsageController {
	@Autowired
	private WaterMeterService waterMeterService;
	@GetMapping(value="/{id}")
	public List<ListWaterUsageResponse> find(@PathVariable("id") Integer id) {
		return waterMeterService.find(id);
	}
}
